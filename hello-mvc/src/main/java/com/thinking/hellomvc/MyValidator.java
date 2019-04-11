package com.thinking.hellomvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UserDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o==null){
            errors.rejectValue("",null,"不可为空");
            return;
        }
        UserDTO userDTO=(UserDTO)o;
        if(userDTO.getDenger()!="M" || userDTO.getDenger()!="F")
        {
            errors.rejectValue("",null,"性别错误");
            return;
        }
    }
}
