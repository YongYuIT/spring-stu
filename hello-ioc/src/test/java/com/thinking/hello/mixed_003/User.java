package com.thinking.hello.mixed_003;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component("my_test_user")
public class User {
    @Value("6")
    private Long id;
    @Value("name_666666")
    private String userName;
    @Value("ffffff")
    private String note;

}
