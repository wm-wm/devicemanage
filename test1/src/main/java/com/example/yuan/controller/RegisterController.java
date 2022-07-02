package com.example.yuan.controller;


import com.example.yuan.dao.TeacherDao;
import com.example.yuan.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/TeacherRegister")
    public String TeacherRegister(Model model,Integer tid, String tname, String tmail, Integer lid, String tpw)
    {
        model.addAttribute("teacherRegister",teacherService.InsertTeacher(tid,tname,tmail,lid,tpw));
        return "TeacherRegister";
    }
}
