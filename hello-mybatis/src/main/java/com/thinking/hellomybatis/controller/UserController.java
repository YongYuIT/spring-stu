package com.thinking.hellomybatis.controller;

import com.thinking.hellomybatis.mapper.UserMapper;
import com.thinking.hellomybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserMapper mapper;

    @GetMapping("/{id}")
    public User findUser(@PathVariable Long id){
        User user= mapper.selectByPrimaryKey(id);
        if(null==user){
            return null;
        }
        return user;
    }
}
