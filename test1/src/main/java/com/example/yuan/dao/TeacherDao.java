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
    public int UpdateTeacher(Integer tid,String tname,String tmail,String tpw,Integer lid);

    //删除老师信息
    public int DeleteTeacher(Integer tid);


    //计数
    Integer teacherAccount();

    @Select("SELECT tid,tname,lid,tpw FROM teacher WHERE teacher.tid=#{tid};")
    public Teacher findTeacher(@Param("tid")int tid);

    @Select("SELECT lname FROM laboratory WHERE lid=#{lid};")
    public String findLabName(@Param("lid")int lid);

    //添加教师信息(教师注册)
    public int InsertTeacher(Integer tid,String tname,String tmail,Integer lid,String tpw);
}
