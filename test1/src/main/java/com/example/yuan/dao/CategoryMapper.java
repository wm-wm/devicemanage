package com.example.yuan.dao;

import com.example.yuan.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    //根据cid检索种类
    Category categoryByCid(Integer Cid);
}
