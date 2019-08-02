package com.thinking.hello_kafka_produce;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MyConsumerListener implements MessageListener<String, String> {
    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        System.out.println("############onMessage1-->" + data.key() + "-->" + data.value());
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
        System.out.println("############onMessage2-->" + data.key() + "-->" + data.value());
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
        System.out.println("############onMessage3-->" + data.key() + "-->" + data.value());
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        System.out.println("############onMessage4-->" + data.key() + "-->" + data.value());
    }
}
