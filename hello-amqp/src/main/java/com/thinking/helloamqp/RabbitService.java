package com.thinking.helloamqp;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        rabbitTemplate.setConfirmCallback(MsgRabbitListener);
        rabbitTemplate.convertAndSend(msgQueueName, msg);
    }

    public void sendUser(User user) {
        rabbitTemplate.setConfirmCallback(UserRabbitListener);
        rabbitTemplate.convertAndSend(userQueueName, user);
    }


    private RabbitTemplate.ConfirmCallback MsgRabbitListener = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            if (b) {
                System.out.println("MsgRabbitListener------------------消息被成功消费");
            } else {
                System.out.println("MsgRabbitListener------------------消息消费失败-->" + s);
            }

        }
    };

    private RabbitTemplate.ConfirmCallback UserRabbitListener = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {
            if (b) {
                System.out.println("UserRabbitListener------------------消息被成功消费");
            } else {
                System.out.println("UserRabbitListener------------------消息消费失败-->" + s);
            }
        }
    };
}
