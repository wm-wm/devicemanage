package com.example.yuan.service;

import com.example.yuan.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
    //查询老师信息
    public List<Teacher> SelectAllTeacher();

    //删除老师信息
    public boolean DeleteTeacher(Integer tid);

    //修改老师信息
    public boolean UpdateTeacher(Integer tid,String tname,String tmail,String tpw,String lname);


    public Teacher findTeacher(@Param("tid")int tid);
    public String findLabName(@Param("lid")int lid);
    public Teacher findUser(int tid);


    Integer teacherAccount();
    //添加教师信息(教师注册)
    public boolean InsertTeacher(Integer tid,String tname,String tmail,Integer lid,String tpw);
}
