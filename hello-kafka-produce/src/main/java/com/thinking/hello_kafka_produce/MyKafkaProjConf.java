package com.thinking.hello_kafka_produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyKafkaProjConf {
    @Bean
    @Qualifier("myKafkaFact")
    public ProducerFactory getKafkaFactory() {
        System.out.println("----------getFactory");
        Map<String, String> config = new HashMap();
        config.put("bootstrap.servers", "kafka_1.thinking.com:9092,kafka_2.thinking.com:9092,kafka_3.thinking.com:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    @Qualifier("myKafkaTmp")
    public KafkaTemplate getKafkaTemplate(@Autowired @Qualifier("myKafkaFact") ProducerFactory factory, @Autowired MyProducerListener listener) {
        KafkaTemplate template = new KafkaTemplate(factory, true);
        template.setDefaultTopic("defTestTopic");
        template.setProducerListener(listener);
        return template;
    }
}
