package com.thinking.hello_kafka_produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class MyTestController {

    @Autowired
    @Qualifier("myKafkaTmp")
    private KafkaTemplate template;

    @GetMapping("test_env")
    public String testEnv() {
        template.sendDefault("aaa" + System.currentTimeMillis());
        return "success";
    }

}

/*
####配置端口转发(没有用上)
$ sudo apt-get install rinetd
$ sudo gedit /etc/rinetd.conf
0.0.0.0 8888 0.0.0.0 8080
$ rinetd -c /etc/rinetd.conf
*/

/*
####kafka env ref to https://github.com/YongYuIT/Fabric-study/tree/master/hello-kafka
$ docker exec -it kafka_1.thinking.com /bin/bash
# cd /kafka/bin
# ./kafka-topics.sh --create --zookeeper test_1.thinking.com:2181 --replication-factor 1 --partitions 1 --topic defTestTopic
# ./kafka-console-consumer.sh --bootstrap-server kafka_1.thinking.com:9092 --topic defTestTopic --from-beginning
*/

/*
$ docker stop hello-kafka-produce.thinking.com
$ docker rm hello-kafka-produce.thinking.com
$ sudo rm -rf /home/yong/spring-stu/hello-kafka-produce/logs
####maven clean install
$ docker-compose -f /home/yong/spring-stu/hello-kafka-produce/src/main/resources/build-env/docker-compose-spring.yml up -d
$ docker logs hello-kafka-produce.thinking.com
$ docker exec -it hello-kafka-produce.thinking.com /bin/bash
*/