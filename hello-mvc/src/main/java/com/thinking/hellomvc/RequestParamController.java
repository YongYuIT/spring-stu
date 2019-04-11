package com.thinking.hellomvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/requestparam")
public class RequestParamController {

    @PostMapping("/test")
    @ResponseBody
    public Map getPatams(@RequestParam String name,
                         @RequestParam int id,
                         @RequestParam(defaultValue = "100") int age,
                         @RequestParam(required = false) Character gender) {
        Map params = new HashMap();
        params.put("des", "hello");
        params.put("name", name);
        params.put("id", id);
        params.put("age", age);
        params.put("gender", gender);
        return params;
    }

    @PostMapping("test-conv")
    @ResponseBody
    public UserDTO testConv(@RequestParam UserDTO UserDTO) {
        return UserDTO;
    }

}
