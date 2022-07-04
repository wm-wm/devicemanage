package com.example.yuan.controller;


import com.example.yuan.pojo.Teacher;
import com.example.yuan.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/TeacherInformationSelect")
    public String TeacherInformationSelect(Model model)
    {
        List<Teacher> teacher = teacherService.SelectAllTeacher();
        model.addAttribute("teacher",teacher);
        return "TeacherInformationSelect";
    }
    @GetMapping("/deleteTeacher/{tid}")
    public String TeacherInformationDelete(@PathVariable("tid") Integer tid, RedirectAttributes attributes)
    {
        boolean b = teacherService.DeleteTeacher(tid);
        if(b==true)
        {
            attributes.addFlashAttribute("message","删除成功!");
            return "redirect:/TeacherInformationSelect";
        }
        else
        {
            attributes.addFlashAttribute("message","删除失败!");
            return "redirect:/TeacherInformationSelect";
        }
    }
    @RequestMapping("/TeacherInformationModify")
    public String TeacherInformationModify(Integer tid,String tname,String tmail,String tpw,Integer lid,Model model)
    {
        model.addAttribute("teacher",teacherService.UpdateTeacher(tid,tname,tmail,tpw,lid));
        return "/TeacherInformationModify";
    }
}
