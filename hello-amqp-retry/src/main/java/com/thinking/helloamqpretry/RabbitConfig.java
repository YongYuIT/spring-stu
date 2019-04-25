package com.thinking.helloamqpretry;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Bean
    public Queue createMsgQueue() {
        return new Queue(msgQueueName, true);//durable，消息是否持久化
    }

    @Bean
    public Queue createUserQueue() {
        return new Queue(userQueueName, true);
    }

}
