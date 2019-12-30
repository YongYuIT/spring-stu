package com.thinking.hellomybatis1.model;

import lombok.Data;

import java.util.List;

@Data
public class StuBookVo {
    private String msg;
    private long status;
    private List<StuBookInfo> infos;
}
