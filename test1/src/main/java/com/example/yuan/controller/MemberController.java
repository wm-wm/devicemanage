package com.example.yuan.controller;


import com.example.yuan.pojo.Member;
import com.example.yuan.pojo.Teacher;
import com.example.yuan.service.MemberService;
import com.example.yuan.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private TeacherService teacherService;


    @RequestMapping ("MemberInformationModify")
    public String MemberInformationModify(Model model,Integer mid,String mname,String mmail,String mpw)
    {
        boolean b = memberService.memberModify(mid,mname,mmail,mpw);
        if(b == true)
        {
            model.addAttribute("memberModify",memberService.memberModify(mid,mname,mmail,mpw));
        }
        return "MemberInformationModify";
    }


    @GetMapping("/MemberInformationSelect")
    public String MemberInformationSelect(Model model)
    {
        List<Member> member = memberService.SelectAllMember();
        model.addAttribute("member",member);
        return "MemberInformationSelect";
    }
    @GetMapping("/deleteMember/{mid}")
    public String MemberInformationDelete(@PathVariable("mid") Integer mid, RedirectAttributes attributes)
    {
        boolean b = memberService.DeleteMember(mid);
        if (b == true)
        {
            attributes.addFlashAttribute("message","删除成功!");
            return "redirect:/MemberInformationSelect";
        }
        else
        {
            attributes.addFlashAttribute("message","删除失败!");
            return "redirect:/MemberInformationSelect";
        }
    }
    @RequestMapping("/toMember")
    public String memberLogin(){
        return "memberLogin";
    }

    @RequestMapping("/toSupervisor")
    public String supervisorLogin(){
        return "MainMenu";

    }
    @RequestMapping("/toMemberMain")
    public String memberMain(Integer Mid,String Mpw, Model model, HttpSession session) throws Exception {
        Member member=new Member();
        member.setMid(Mid);
        member.setMpw(Mpw);
        Member member1=memberService.memberLogin(member);
        //以下为2022/6/28 16:15 3.0修改内容
        //目标：定位到相同tid
        Teacher teacher;
        teacher=teacherService.findUser(Mid);

        if(member1 != null){
            //用户信息
            Integer Mid1=member1.getMid();
            model.addAttribute("id",Mid1);
            session.setAttribute("id",Mid1);

            String Mname1=member1.getMname();
            model.addAttribute("name",Mname1);
            session.setAttribute("name",Mname1);

            String Mmail1=member1.getMmail();
            model.addAttribute("mail",Mmail1);
            session.setAttribute("mail",Mmail1);

            model.addAttribute("member",member);
            session.setAttribute("member",member1);
            return "memberMain";
        }else
        if(teacher!=null&&Mpw.equalsIgnoreCase(teacher.getTpw())){
            model.addAttribute("tname",teacher.getTname());
            model.addAttribute("tid",teacher.getTid());
            model.addAttribute("lname",teacher.getLibName());

            return "AdministerGudie/Teacher";
        }
        else {
            model.addAttribute("msg", "您输入的账号或密码有误，请重新输入!");
            return "memberLogin";
        }
    }
}
