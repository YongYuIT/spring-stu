package com.thinking.hellomvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/requestparam")
public class RequestParamController {

    @InitBinder
    public void FuckInit(WebDataBinder binder) {
        binder.setValidator(new MyValidator());
    }

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
    public UserDTO testConv(@Valid @RequestParam UserDTO UserDTO) {
        return UserDTO;
    }

}
