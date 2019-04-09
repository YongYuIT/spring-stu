package com.thinking.hello.mixed_003;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Client {
    @Value("5")
    private Long id;
    @Value("client_555555")
    private String clientName;
    @Value("eeeeee")
    private String note;
}
