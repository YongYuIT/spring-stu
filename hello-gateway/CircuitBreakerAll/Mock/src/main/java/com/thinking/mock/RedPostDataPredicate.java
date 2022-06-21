package com.thinking.mock;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class RedPostDataPredicate implements Predicate {
    @Override
    public boolean test(Object o) {
        System.out.println("RedPostDataPredicate read post body-->" + JSON.toJSONString(o));
        return true;
    }
}