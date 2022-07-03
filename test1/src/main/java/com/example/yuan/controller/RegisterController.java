package com.example.yuan.controller;


import com.example.yuan.dao.TeacherDao;
import com.example.yuan.service.MemberService;
import com.example.yuan.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private MemberService memberService;
    @RequestMapping("/toTeacherRegister")
    public String toTeacherRegister(){
        return "TeacherRegister";
    }
    @RequestMapping("/TeacherRegister")
    public String TeacherRegister(Model model, String tname, String tmail, Integer lid, String tpw)
    {
        Integer tid=teacherService.teacherAccount()+1;
        model.addAttribute("teacherRegister",teacherService.InsertTeacher(tid,tname,tmail,lid,tpw));
        return "TeacherLogin1";
    }
    @RequestMapping("/toMemberRegister")
    public String toMemberRegister(){
        return "MemberRegister";
    }
    @RequestMapping("/memberRegister")
    public String memberRegister(Model model,String Mname,String Mmail,String Mpw){


        Integer Mid=memberService.selectAccount()+1;
        memberService.addMember(Mid,Mname,Mmail,Mpw);
        return "MemberLogin1";
    }
}
