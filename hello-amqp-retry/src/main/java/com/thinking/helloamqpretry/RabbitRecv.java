package com.thinking.helloamqpretry;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
public class RabbitRecv {
    @RabbitListener(queues = "${rabbitmq.queue.msg}")
    public void recMsg(Message msg, Channel channel) throws IOException {
        System.out.println("-----------------recMsg-->" + new String(msg.getBody()));
        channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
    }

    @RabbitListener(queues = "${rabbitmq.queue.user}")
    public void recUser(Message msg, Channel channel) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(msg.getBody());
        ObjectInputStream ois = new ObjectInputStream(bais);
        User user = (User) ois.readObject();
        System.out.println("-----------------recMsg-->" + JSON.toJSONString(user));
        channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
    }
}
