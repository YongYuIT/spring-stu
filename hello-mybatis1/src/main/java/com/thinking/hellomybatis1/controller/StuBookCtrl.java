package com.thinking.hellomybatis1.controller;

import com.thinking.hellomybatis1.mapper.StuBookInfoMapper;
import com.thinking.hellomybatis1.model.StuBookDTO;
import com.thinking.hellomybatis1.model.StuBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/StuBookAPI")
public class StuBookCtrl {

    @Autowired
    private StuBookInfoMapper stuBookInfoMapper;

    @PostMapping("test-conv")
    @ResponseBody
    public StuBookVo getBaseInfos(@RequestBody StuBookDTO dto) {
        StuBookVo vo = new StuBookVo();
        int startindex = (dto.getPageIndex() - 1) * dto.getPageSize();
        List stuInfos = stuBookInfoMapper.listAll(startindex, dto.getPageSize());
        if (stuInfos == null || stuInfos.size() < 1) {
            vo.setMsg("error");
            vo.setStatus(500);
            vo.setInfos(null);
        } else {
            vo.setMsg("success");
            vo.setStatus(200);
            vo.setInfos(stuInfos);
        }
        return vo;
    }
}
