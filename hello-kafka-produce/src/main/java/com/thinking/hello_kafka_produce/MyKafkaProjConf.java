package com.thinking.hello_kafka_produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyKafkaProjConf {

    private static final String defTopic = "defTestTopic";
    private static final String kafkaCluster = "kafka_1.thinking.com:9092,kafka_2.thinking.com:9092,kafka_3.thinking.com:9092";
    private static final String serializer = "org.apache.kafka.common.serialization.StringSerializer";
    private static final String deserializer = "org.apache.kafka.common.serialization.StringDeserializer";

    @Bean
    @Qualifier("myKafkaPrdFact")
    public ProducerFactory getKafkaPrdFactory() {
        System.out.println("----------getKafkaPrdFactory");
        Map<String, String> config = new HashMap();
        config.put("bootstrap.servers", kafkaCluster);
        config.put("key.serializer", serializer);
        config.put("value.serializer", serializer);
        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    @Qualifier("myKafkaCsmFact")
    public ConsumerFactory getKafkaCusFactory() {
        Map<String, String> config = new HashMap();
        config.put("bootstrap.servers", kafkaCluster);
        config.put("group.id", "fuckCsmId");
        config.put("enable.auto.commit", "true");
        config.put("auto.commit.interval.ms", "1000");
        config.put("key.deserializer", deserializer);
        config.put("value.deserializer", deserializer);
        return new DefaultKafkaConsumerFactory(config);
    }

    @Bean
    @Qualifier("myKafkaTmp")
    public KafkaTemplate getKafkaTemplate(@Autowired @Qualifier("myKafkaPrdFact") ProducerFactory factory, @Autowired MyProducerListener listener) {
        KafkaTemplate template = new KafkaTemplate(factory, true);
        template.setDefaultTopic(defTopic);
        template.setProducerListener(listener);
        return template;
    }

    @Bean
    public ConcurrentMessageListenerContainer getMessageContainer(@Autowired @Qualifier("myKafkaCsmFact") ConsumerFactory factory, @Autowired MyConsumerListener listener) {
        System.out.println("----------ConcurrentMessageListenerContainer");
        ContainerProperties properties = new ContainerProperties(defTopic);
        properties.setMessageListener(listener);
        ConcurrentMessageListenerContainer container = new ConcurrentMessageListenerContainer(factory, properties);
        container.setConcurrency(3);
        container.start();
        return container;
    }
}
