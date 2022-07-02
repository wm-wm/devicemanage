package com.example.yuan.dao;

import com.example.yuan.pojo.Laboratory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LaboratoryMapper {
    //根据lid检索
    Laboratory laboratoryByLid(Integer Lid);
}
