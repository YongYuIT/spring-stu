package com.thinking.helloamqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitConfig {
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Value("${rabbitmq.queue.dead}")
    private String deadQueueName;

    @Value("${rabbitmq.exchange.dead}")
    private String deadExchangeName;

    @Value("${rabbitmq.routingkey.dead}")
    private String deadRoutingKey;

    //在rabbitmq.queue.msg上设置死信队列
    @Bean
    public Queue createMsgQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", deadExchangeName);
        args.put("x-dead-letter-routing-key", deadRoutingKey);
        return new Queue(msgQueueName, true, false, false, args);//durable，消息是否持久化
    }

    @Bean
    public Queue createUserQueue() {
        return new Queue(userQueueName, true);
    }


    @Bean
    public RetryTemplate createRetryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.registerListener(new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
                System.out.println("----------------开始重试");
                //是否接纳重试
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
                //在这里进行重試失敗的後續處理
                System.out.println("----------------结束重试-->" + throwable.getCause().getClass().getName());
                RabbitRecv.MyExp exp = (RabbitRecv.MyExp) throwable.getCause();
                //由于绑定了死信队列，所以消费端回绝消息的时候，消息会进入死信队列
                try {
                    exp.channel.basicReject(exp.msg.getMessageProperties().getDeliveryTag(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
                System.out.println("----------------正在重试-->" + retryContext.getRetryCount());
            }
        });
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(5 * 1000);
        backOffPolicy.setMultiplier(1);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5);


        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }

    //死信队列，可以看到与普通队列无异
    @Bean
    public Queue deadQueue() {
        return new Queue(deadQueueName, true);
    }

    //死信交换机，可以看到与普通交换机无异
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(deadExchangeName);
    }

    //绑定死信队列和死信交换机
    @Bean
    public Binding bindingDeadExchange(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(deadRoutingKey);
    }
}
