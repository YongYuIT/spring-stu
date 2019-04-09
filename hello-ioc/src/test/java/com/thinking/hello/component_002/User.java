package com.thinking.hello.component_002;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("my_test_user")
public class User {
    @Value("1")
    private Long id;
    @Value("name_222")
    private String userName;
    @Value("aaa")
    private String note;

}
