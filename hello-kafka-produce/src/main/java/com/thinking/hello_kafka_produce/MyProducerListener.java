package com.thinking.hello_kafka_produce;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class MyProducerListener implements ProducerListener<String, String> {
    @Override
    public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
        System.out.println("onSuccess");
    }

    @Override
    public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
        System.out.println("onSuccess-->" + topic);
    }

    @Override
    public void onError(ProducerRecord<String, String> producerRecord, Exception exception) {
        System.out.println("onError");
    }

    @Override
    public void onError(String topic, Integer partition, String key, String value, Exception exception) {
        System.out.println("onError" + topic);
    }
}
