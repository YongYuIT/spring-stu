package com.thinking.hellomvc;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class MyConvert implements Converter<String,JSONObject> {

    @Override
    public JSONObject convert(String basse64Str) {
        String str= new String(Base64.getDecoder().decode(basse64Str));
        return JSONObject.parseObject(str);
    }
}
