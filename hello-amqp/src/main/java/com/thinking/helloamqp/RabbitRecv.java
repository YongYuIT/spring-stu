package com.thinking.helloamqp;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitRecv {
    @RabbitListener(queues = "${rabbitmq.queue.msg}")
    public void recMsg(String msg) {
        System.out.println("-----------------recMsg-->" + msg);
    }

    @RabbitListener(queues = "${rabbitmq.queue.user}")
    public void recUser(User user) {
        System.out.println("-----------------recMsg-->" + JSON.toJSONString(user));
    }
}
