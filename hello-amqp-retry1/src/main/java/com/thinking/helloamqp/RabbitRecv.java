package com.thinking.helloamqp;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitRecv {

    private int msgRetryTimes = 0;

    @RabbitListener(queues = "${rabbitmq.queue.msg}")
    public void recMsg(Message msg, Channel channel) throws Exception {
        System.out.println("-----------------recMsg-->" + new String(msg.getBody()));
        msgRetryTimes += 1;
        if (msgRetryTimes < 5) {
            throw new Exception("fuck you");
        } else {
            //明确拒绝，这里可进一步优化，使消息被拒绝后进入死信队列
            channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
            msgRetryTimes = 0;
            System.out.println("消息消費失敗");
        }
    }

    @RabbitListener(queues = "${rabbitmq.queue.user}")
    public void recUser(User user) {
        System.out.println("-----------------recMsg-->" + JSON.toJSONString(user));
    }
}
