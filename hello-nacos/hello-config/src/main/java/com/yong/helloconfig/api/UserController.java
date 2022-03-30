package com.yong.helloconfig.api;

import com.yong.helloconfig.mapper.CustomerMapper;
import com.yong.helloconfig.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private NacosConfig nacosConfig;

    @GetMapping("/getall")
    public List<Customer> getAll() {
        return customerMapper.selectAll();
    }

    @GetMapping("/getConfig")
    public NacosConfig getConfig() {
        return nacosConfig;
    }

}
