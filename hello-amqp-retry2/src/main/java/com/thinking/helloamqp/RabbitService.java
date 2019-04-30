package com.thinking.helloamqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RabbitService {

    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RetryTemplate retryTemplate;

    @Autowired
    private SimpleRabbitListenerContainerFactory factory;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(RabbitListener);
        factory.setAdviceChain(RetryInterceptorBuilder
                .stateless()
                .recoverer(new MessageRecoverer() {
                    @Override
                    public void recover(Message message, Throwable cause) {
                        System.out.println("-----------------cannot handler this error-->" + cause.getCause().getClass().getName());
                        RabbitRecv.MyExp exp = (RabbitRecv.MyExp) cause.getCause();
                        //由于绑定了死信队列，所以消费端回绝消息的时候，消息会进入死信队列
                        try {
                            exp.channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                        } catch (Throwable e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
                .retryOperations(retryTemplate)
                .build());
    }

    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend(msgQueueName, msg);
    }

    public void sendUser(User user) {
        rabbitTemplate.convertAndSend(userQueueName, user);
    }


    private RabbitTemplate.ConfirmCallback RabbitListener = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            if (b) {
                System.out.println("MsgRabbitListener------------------消息被RabbitMQ正常接收");
            } else {
                System.out.println("MsgRabbitListener------------------RabbitMQ消息接收异常-->" + s);
            }
        }
    };
}
