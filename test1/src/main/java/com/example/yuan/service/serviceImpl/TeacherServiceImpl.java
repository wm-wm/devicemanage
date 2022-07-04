package com.example.yuan.service.serviceImpl;


import com.example.yuan.dao.TeacherDao;
import com.example.yuan.pojo.Teacher;
import com.example.yuan.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {


    String teacherTable="teacher";
    String labTable="laboratory";
    @Autowired
    private TeacherDao teacherDao;
    //查询老师信息
    public List<Teacher> SelectAllTeacher()
    {
        return teacherDao.SelectAllTeacher();
    }
    //删除老师信息
    public boolean DeleteTeacher(Integer tid)
    {
        int i = teacherDao.DeleteTeacher(tid);
        if(i>0)
        {
            return true;
        }
        else {return false;}
    }
    //修改老师信息
    public boolean UpdateTeacher(Integer tid,String tname,String tmail,String tpw,Integer lid)
    {
        int i = teacherDao.UpdateTeacher(tid,tname,tmail,tpw,lid);
        if(i>0)
        {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public Teacher findTeacher(int tid) {
        return teacherDao.findTeacher(tid);
    }

    @Override
    public String findLabName(int lid) {
        return teacherDao.findLabName(lid);
    }

    public Teacher findUser(int tid){
        Teacher teacher;
        teacher=findTeacher(tid);
        if(teacher!=null)teacher.setLibName(findLabName(teacher.getLid()));
        return teacher;
    }

    //
    @Override
    public Integer teacherAccount(){
        return teacherDao.teacherAccount();
    }

    //添加教师信息(教师注册)
    public boolean InsertTeacher(Integer tid,String tname,String tmail,Integer lid,String tpw)
    {
        int i = teacherDao.InsertTeacher(tid,tname,tmail,lid,tpw);
        if(i>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
