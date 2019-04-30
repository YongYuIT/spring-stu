package com.thinking.helloamqp;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(RabbitListener);
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
