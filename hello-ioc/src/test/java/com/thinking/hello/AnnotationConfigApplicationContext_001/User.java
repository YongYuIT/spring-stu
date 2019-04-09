package com.thinking.hello.AnnotationConfigApplicationContext_001;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class User {
    @Value("1")
    private Long id;
    @Value("name_111")
    private String userName;
    @Value("aaa")
    private String note;

}
