package com.thinking.hello.AnnotationConfigApplicationContext_001;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class User {
    @Value("2")
    private Long id;
    @Value("name_22222")
    private String userName;
    @Value("bbbbbb")
    private String note;

}
