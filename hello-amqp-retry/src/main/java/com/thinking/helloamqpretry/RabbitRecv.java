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
        //由于绑定了死信队列，所以消费端回绝消息的时候，消息会进入死信队列
        channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "${rabbitmq.queue.user}")
    public void recUser(Message msg, Channel channel) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(msg.getBody());
        ObjectInputStream ois = new ObjectInputStream(bais);
        User user = (User) ois.readObject();
        System.out.println("-----------------recMsg-->" + JSON.toJSONString(user));
        //没有绑定死信队列
        channel.basicReject(msg.getMessageProperties().getDeliveryTag(), false);
    }
}
