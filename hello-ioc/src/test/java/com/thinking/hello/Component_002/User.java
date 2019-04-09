package com.thinking.hello.Component_002;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("my_test_user")
public class User {
    @Value("3")
    private Long id;
    @Value("name_33333")
    private String userName;
    @Value("cccccc")
    private String note;

}
