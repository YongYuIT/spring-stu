package com.thinking.hello_kafka_produce;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class MyProducerListener implements ProducerListener {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        System.out.println("onSuccess");
    }

    @Override
    public void onSuccess(String topic, Integer partition, Object key, Object value, RecordMetadata recordMetadata) {
        System.out.println("onSuccess-->" + topic);
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        System.out.println("onError");
    }

    @Override
    public void onError(String topic, Integer partition, Object key, Object value, Exception exception) {
        System.out.println("onError" + topic);
    }
}
