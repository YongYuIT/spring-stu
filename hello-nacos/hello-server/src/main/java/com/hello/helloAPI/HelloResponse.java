package com.hello.helloAPI;

import lombok.Data;

@Data
public class HelloResponse {
    int respCode = 200;
    String respMsg;
}
