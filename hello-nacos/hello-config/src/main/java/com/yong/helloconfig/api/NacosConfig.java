package com.yong.helloconfig.api;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class NacosConfig {
    @NacosValue(value = "${dbconfig.ip}", autoRefreshed = true)
    private String ip;
    @NacosValue(value = "${dbconfig.port}", autoRefreshed = true)
    private int port;
    @NacosValue(value = "${dbconfig.username}", autoRefreshed = true)
    private String username;
    @NacosValue(value = "${dbconfig.password}", autoRefreshed = true)
    private String password;

}
