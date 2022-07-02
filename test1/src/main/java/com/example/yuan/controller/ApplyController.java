package com.example.yuan.controller;


import com.example.yuan.pojo.*;
import com.example.yuan.service.*;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.nio.channels.SelectionKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private EquipmentService equipmentService;
    @GetMapping("ApplyInformationSelect")
    public String ApplyInformationSelect(Model model)
    {
        List<Apply> apply = applyService.SelectAllApply();
        model.addAttribute("applySelect",apply);
        return "ApplyInformationSelect";
    }
    @RequestMapping("/toRequest")
    public String toRequest(Integer eid,Model model, HttpSession session){
        //传请求得默认值，member.mid,equipmentPlus.eid
        //自动生成aid，flag
        //用户输入astart aend acomment aevaluate
        Member member= (Member) session.getAttribute("member");
        Equipment equipment= equipmentService.equipmentOfEid(eid);
        model.addAttribute("name",member.getMname());
        model.addAttribute("mid",member.getMid());
        model.addAttribute("eid",equipment.getEid());
        Integer flag=0;//0表示审核中
        Integer aid=applyService.ApplyCount()+1;
        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);
        //前端参数
        Apply apply=new Apply();
        apply.setAid(aid);
        apply.setEid(equipment.getEid());
        apply.setFlag(flag);
        apply.setMid(member.getMid());
        apply.setAstart(nowDay);
        model.addAttribute("apply",apply);
        session.setAttribute("apply",apply);
        return "Request";
    }
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LaboratoryService laboratoryService;
    //提交申请界面
    @RequestMapping("/Request")
    public String Request(String Aend,String Aevaluate,Model model,HttpSession session){
        //传请求得默认值，member.mid,equipmentPlus.eid
        //自动生成aid，flag
        //用户输入astart aend acomment aevaluate
        Apply apply= (Apply) session.getAttribute("apply");
        apply.setAend(Aend);
        apply.setAcomment("");
        apply.setAevaluate(Aevaluate);
        apply.setFlag(1);
        Integer Eid=apply.getEid();
        Integer Flag=1;
        equipmentService.updatFlag(Eid,Flag);
        applyService.AddApply(apply);


        Member member = (Member) session.getAttribute("user");
        List<Equipment> equipment=equipmentService.equipmentAll();
        Integer l=equipment.size();
        List<EquipmentPlus> equipmentPlusList=new ArrayList<EquipmentPlus>(l);
        EquipmentPlus equipmentPlus1=new EquipmentPlus();
        for(int i=0;i<l;i++){
            Integer Cid= equipment.get(i).getCid();
            Category category=categoryService.categoryByCid(Cid);
            Integer lid=category.getLid();
            Laboratory laboratory= laboratoryService.laboratoryByLid(lid);
            String msg=new String();
            Integer F=equipment.get(i).getFlag();
            if(F==0)
                msg="可预约";
            else if(F==1)
                msg="已预约";
            equipmentPlusList.add(equipmentPlus1.setEquipment(equipment.get(i),category,laboratory,msg));
        }
        model.addAttribute("member", member);
        model.addAttribute  ("e",equipmentPlusList);
        model.addAttribute("name",session.getAttribute("name"));
        session.setAttribute("e",equipmentPlusList);
        //设备种类及实验室
        return "AllOfEquipment";
    }

    @Autowired
    private MemberService memberService;
    @RequestMapping("/toApply")
    public String SelectApply(Model model,HttpSession session){
        model.addAttribute("name",session.getAttribute("name"));
        Member member = (Member) session.getAttribute("member");
        Integer mid=member.getMid();
        List<Apply> applies=applyService.SelectByMid(mid);
        Integer length=applies.size();

        for(int i=0;i<length;i++){
            applies.get(i).setMname(member.getMname());
            Equipment equipment=equipmentService.SelectEquipmentByEid(applies.get(i).getEid());
            applies.get(i).setEname(equipment.getEname());
        }
        model.addAttribute("applies",applies);
        return "SelectApply";
    }

    @RequestMapping("/toEvaluate")
    public String toEvaluate(Integer aid,Model model, HttpSession session){
        model.addAttribute("name",session.getAttribute("name"));
        Apply apply=applyService.SelectByAid(aid);
        model.addAttribute("a",apply);
        return "Evaluate";
    }
    @RequestMapping("/evaluate")
    public String evaluate(Integer aid,String Acomment,Model model,HttpSession session){
        applyService.updateApply(aid,Acomment);


        model.addAttribute("name",session.getAttribute("name"));
        Member member = (Member) session.getAttribute("member");
        Integer mid=member.getMid();
        List<Apply> applies=applyService.SelectByMid(mid);
        Integer length=applies.size();

        for(int i=0;i<length;i++){
            applies.get(i).setMname(member.getMname());
            Equipment equipment=equipmentService.SelectEquipmentByEid(applies.get(i).getEid());
            applies.get(i).setEname(equipment.getEname());
        }
        model.addAttribute("applies",applies);
        return "SelectApply";
    }
}
