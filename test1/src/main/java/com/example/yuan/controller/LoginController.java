package com.example.yuan.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/MainLogin")
    public String MainLogin()
    {
        return "MainLogin";
    }
    @GetMapping("/AdminLogin")
    public String AdminLogin()
    {
        return "AdminLogin";
    }
    @GetMapping("/TeacherLogin1")
    public String TeacherLogin1()
    {
        return "TeacherLogin1";
    }
    @GetMapping("/MemberLogin1")
    public String MemberLogin1()
    {
        return "MemberLogin1";
    }
}
