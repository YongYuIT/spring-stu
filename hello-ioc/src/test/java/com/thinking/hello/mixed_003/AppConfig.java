package com.thinking.hello.mixed_003;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    @Bean("my_test_client")
    public Client getFuckClient(){
        Client clien=new Client();
        clien.setClientName("name_4444");
        clien.setId(4L);
        clien.setNote("dddddd");
        return clien;
    }
}
