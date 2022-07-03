package com.example.yuan.dao;


import com.example.yuan.pojo.SuperVisor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SuperVisorDao {
    //根据id和password查询
    SuperVisor selectSuperVisor(Integer sid,String spw);
}
