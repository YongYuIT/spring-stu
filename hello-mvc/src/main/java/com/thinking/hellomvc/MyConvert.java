package com.thinking.hellomvc;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class MyConvert implements Converter<String,UserDTO> {

    @Override
    public UserDTO convert(String base64Str) {
        String userStr= new String(Base64.getDecoder().decode(base64Str));
        return JSONObject.parseObject(userStr,UserDTO.class);
    }
}
