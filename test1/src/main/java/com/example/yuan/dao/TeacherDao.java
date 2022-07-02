package com.example.yuan.dao;


import com.example.yuan.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeacherDao {
    //查询老师信息
    public List<Teacher> SelectAllTeacher();

    //修改老师信息
    public int UpdateTeacher(Integer tid,String tname,String tmail,String tpw,String lname);

    //删除老师信息
    public int DeleteTeacher(Integer tid);

    //

    @Select("SELECT tid,tname,lid,tpw FROM teacher WHERE teacher.tid=#{tid};")
    public Teacher findTeacher(@Param("tid")int tid);

    @Select("SELECT lname FROM laboratory WHERE lid=#{lid};")
    public String findLabName(@Param("lid")int lid);
}
