package com.thinking.helloamqp;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitRecv {


    @RabbitListener(queues = "${rabbitmq.queue.msg}")
    public void recMsg(Message msg, Channel channel) throws Exception {
        System.out.println("-----------------recMsg-->" + new String(msg.getBody()));
        MyExp exp = new MyExp("fuck you");
        exp.channel = channel;
        throw exp;
    }

    @RabbitListener(queues = "${rabbitmq.queue.user}")
    public void recUser(User user) {
        System.out.println("-----------------recMsg-->" + JSON.toJSONString(user));
    }

    public static class MyExp extends Exception {
        public MyExp(String str) {
            super(str);
        }

        public Channel channel;
    }
}
