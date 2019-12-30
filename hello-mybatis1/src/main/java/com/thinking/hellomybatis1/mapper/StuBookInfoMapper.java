package com.thinking.hellomybatis1.mapper;

import com.thinking.hellomybatis1.model.StuBookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuBookInfoMapper {
    List<StuBookInfo> listAll(@Param("startIndex") int startIndex, @Param("dataCount") int dataCount);
}
