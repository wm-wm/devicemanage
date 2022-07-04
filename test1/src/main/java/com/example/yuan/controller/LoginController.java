package com.example.yuan.controller;


import com.example.yuan.pojo.Member;
import com.example.yuan.pojo.SuperVisor;
import com.example.yuan.pojo.Teacher;
import com.example.yuan.service.MemberService;
import com.example.yuan.service.SuperVisorService;
import com.example.yuan.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SuperVisorService superVisorService;
    @GetMapping("/toMainLogin")
    public String MainLogin()
    {
        return "MainLogin";
    }
    @GetMapping("/toAdminLogin")
    public String toAdminLogin()
    {
        return "Adminlogin";
    }
    @RequestMapping("/Adminlogin")
    public String AdminLogin(Model model,Integer sid,String spw){
        SuperVisor superVisor=superVisorService.selectSuperVisor(sid,spw);
        if(superVisor!=null){
            model.addAttribute("superVisor",superVisor);
            return "EquipmentInformationSelect";
        }
        return "MainLogin";
    }
    @GetMapping("/toMemberLogin")
    public String toMemberLogin()
    {
        return "MemberLogin1";
    }

    @RequestMapping("/memberLogin")
    public String memberLogin(Integer Mid,String Mpw, Model model, HttpSession session){
        Member member=new Member();
        member.setMid(Mid);
        member.setMpw(Mpw);
        Member member1=memberService.memberLogin(member);
        if(member1 != null){
            //用户信息
            Integer Mid1=member1.getMid();

            String Mname1=member1.getMname();
            model.addAttribute("name",Mname1);
            session.setAttribute("name",Mname1);

            String Mmail1=member1.getMmail();

            model.addAttribute("member",member);
            session.setAttribute("member",member1);
            return "memberMain";
        }
         else {
                model.addAttribute("msg", "您输入的账号或密码有误，请重新输入!");
                return "memberLogin";
            }
    }

    @GetMapping("/toTeacherLogin")
    public String toTeacherLogin1()
    {
        return "TeacherLogin1";
    }
    @RequestMapping("/teacherLogin")
    public String teacherLogin(Model model,String username,String password){
        Teacher teacher;
        teacher=teacherService.findUser(Integer.parseInt(username));
        if(teacher!=null&&password.equalsIgnoreCase(teacher.getTpw())){
            model.addAttribute("tname",teacher.getTname());
            model.addAttribute("tid",teacher.getTid());
            model.addAttribute("lname",teacher.getLibName());
            return "AdministerGudie/Teacher";
        }else return "TeacherLogin1";
    }
}
