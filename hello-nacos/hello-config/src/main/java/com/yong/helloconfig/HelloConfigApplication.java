package com.yong.helloconfig;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yong.helloconfig.mapper")
@NacosPropertySource(dataId = "d-hello-config-svc-dev", groupId = "g-dev", autoRefreshed = true)
public class HelloConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloConfigApplication.class, args);
    }

}