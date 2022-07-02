package com.example.yuan.controller;

import com.example.yuan.pojo.*;
import com.example.yuan.service.ApplyService;
import com.example.yuan.service.EquipmentService;
import com.example.yuan.service.TeacherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/equip")
public class EquipmentControlss<Courier> {
    @Autowired
    public TeacherService teacherService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ApplyService applyService;

    public Teacher usersTeacher;


    @RequestMapping(value="teacherData")
    @ResponseBody
    public String TeacherDataGet(int tid) throws JsonProcessingException {
        Teacher teacher;
        teacher=teacherService.findUser(tid);
        usersTeacher=teacher;
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(teacher);
        return json;
    }

    @RequestMapping(value = "data")
    @ResponseBody
    public String data_get(int page,int limit,String ename)throws Exception{
        List<Equipment> temp=null;
        int counts;
        System.out.println(ename);
        if(ename!=null&&(!ename.isEmpty())) {
            //查找代码
            temp=equipmentService.findallEquipByEname(usersTeacher.getLid(),ename);
        }
        else temp=equipmentService.findallEquip(usersTeacher.getLid());
        //分页代码
        counts=temp.size();
        int min_page=(page-1)*limit;
        int max_page=(page*limit>counts)?counts:page*limit;
        temp=temp.subList(min_page,max_page);
        String jack="";
        //初始json获得
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(temp);
        //格式适应
        Datagetsjson tempjson=new Datagetsjson();
        tempjson.setData(json);
        tempjson.setCount(counts);
        ObjectMapper objectMapper2 = new ObjectMapper();
        json = objectMapper2.writeValueAsString(tempjson);
        json=json.replace("\\","");
        json=json.replace("\"[","[");
        json=json.replace("]\"","]");
        return json;
    }

    @RequestMapping(value="page")
    public String page_get(Model model,int tid)throws Exception{
        Teacher teacher=usersTeacher;
        if(tid!=0)teacher=teacherService.findUser(tid);
        usersTeacher=teacher;

        //themleaf 输入信息
        model.addAttribute("tname",teacher.getTname());
        model.addAttribute("tid",teacher.getTid());

        return "AdministerGudie/InstrumentPage";
    }

    @RequestMapping(value="update")
    @ResponseBody
    public void update(int eid,int cid,String ename,String changes)throws Exception{
        equipmentService.changeEquipment(ename, cid, eid);
    }

    @RequestMapping(value="delete")
    @ResponseBody
    public void delete(int eid)throws Exception{
        System.out.println(eid);
        equipmentService.deleteEquipment(eid);
    }

    @RequestMapping(value="insert")
    public String insert(int eid,String ename,int cid,Model model)throws Exception{
        equipmentService.insertNewEquipment(eid,ename,cid);
        return add(model);
    }

    @RequestMapping(value="add")
    public String add(Model model)throws Exception{
        //themleaf 输入信息
        model.addAttribute("tname",usersTeacher.getTname());
        model.addAttribute("tid",usersTeacher.getTid());
        return "AdministerGudie/InstrumentAdd";
    }

    @RequestMapping(value = "CategoryData")
    @ResponseBody
    public String categoryData() throws JsonProcessingException {
        List<Category>categoryList;
        categoryList = equipmentService.findallCategory(usersTeacher.getLid());
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(categoryList);
        return json;
    }

    //申请控制代码

    @RequestMapping(value = "dataApply")
    @ResponseBody
    public String data_getA(int page,int limit)throws Exception{
        List<Apply> temp=null;
        int counts;
        temp=applyService.findall(usersTeacher.getLid());
        //flag转换
        counts=temp.size();
        for(Apply apply:temp){
            if(apply.getFlag()>0)apply.setFlagss("审批通过");
            else apply.setFlagss("未审批通过");
        }
        //分页代码
        int min_page=(page-1)*limit;
        int max_page=(page*limit>counts)?counts:page*limit;
        temp=temp.subList(min_page,max_page);
        String jack="";
        //初始json获得
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(temp);
        //格式适应
        Datagetsjson tempjson=new Datagetsjson();
        tempjson.setData(json);
        tempjson.setCount(counts);
        ObjectMapper objectMapper2 = new ObjectMapper();
        json = objectMapper2.writeValueAsString(tempjson);
        json=json.replace("\\","");
        json=json.replace("\"[","[");
        json=json.replace("]\"","]");
        return json;
    }

    @RequestMapping(value="pageApply")
    public String page_getA(Model model,int tid)throws Exception{
        Teacher teacher=usersTeacher;
        if(tid!=0)teacher=teacherService.findUser(tid);
        usersTeacher=teacher;

        //themleaf 输入信息
        model.addAttribute("tname",teacher.getTname());
        model.addAttribute("tid",teacher.getTid());

        return "AdministerGudie/AppointMentPage";
    }

    @RequestMapping(value="updateApply")
    @ResponseBody
    public void updateA(String aevaluate,int aid,String changes)throws Exception{
        applyService.aevaluateChange(aevaluate,aid);
    }

    @RequestMapping(value="updateFlag")
    public void updateFlag(int flag,int aid)throws Exception{
        applyService.approve(flag, aid);
    }

    @RequestMapping(value="teacher")
    public String Teacher(Model model,int tid)throws Exception{
        Teacher teacher=usersTeacher;
        if(tid!=0)teacher=teacherService.findUser(tid);
        usersTeacher=teacher;

        //themleaf 输入信息
        model.addAttribute("tname",teacher.getTname());
        model.addAttribute("tid",teacher.getTid());
        model.addAttribute("lname",teacher.getLibName());

        return "AdministerGudie/Teacher";
    }
}
