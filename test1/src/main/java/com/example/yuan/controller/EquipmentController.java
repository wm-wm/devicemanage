package com.example.yuan.controller;


import com.example.yuan.pojo.*;
import com.example.yuan.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LaboratoryService laboratoryService;
    @GetMapping("/MainMenu")
    public String MainMenu(Model model)
    {
        return "MainMenu";
    }
    @GetMapping("/EquipmentInformationSelect")
    public String EquipmentInformationSelect(Model model)
    {
        List<Equipment> equipment = equipmentService.SelectAllEquipment();
        model.addAttribute("equipmentSelect",equipment);
        return "EquipmentInformationSelect";
    }
    @GetMapping("/EquipmentInformationInsert")
    public String EquipmentInformationInsert(Model model,Equipment equipment)
    {
        List<Equipment> equipment1 = equipmentService.SelectAllEquipment();
        model.addAttribute("equipmentSelect",equipment1);
        return "EquipmentInformationInsert";
    }
    @GetMapping("/delete/{eid}")
    public String EquipmentInformationDelete(@PathVariable("eid") int eid, RedirectAttributes attributes)
    {
        boolean b = equipmentService.DeleteEquipment(eid);
        if (b == true)
        {
            attributes.addFlashAttribute("message","删除仪器成功");
            return "redirect:/EquipmentInformationSelect";
        }else
        {
            attributes.addFlashAttribute("message","删除仪器失败");
            return "redirect:/EquipmentInformationSelect";
        }
    }
    @RequestMapping ("/EquipmentInformationModify")
    public String SelectEquipmentByEid(Model model,Integer eid,String ename,String cname)
    {
        model.addAttribute("equipmentUpdate", equipmentService.UpdateEquipment(eid,ename,cname));
        return "EquipmentInformationModify";
    }
    @RequestMapping("/EquipmentInformationInsert")
    public String EquipmentInformationInsert(Model model,String ename,Integer cid)
    {

        model.addAttribute("equipmentInsert", equipmentService.InsertEquipment(ename,cid));
        return "EquipmentInformationInsert";
    }
    /*
    @RequestMapping("/equip")
    public class EquipmentControl<Courier> {
        @Autowired
        private TeacherService teacherService;

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

        @RequestMapping(value="/teacher")
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


     */

    //浏览所有设备
    @RequestMapping("/toAllOfEquipment")
    public String AllOfEquipment(Model model, HttpSession session){
        Member member = (Member) session.getAttribute("member");
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
            Integer Flag=equipment.get(i).getFlag();
            if(Flag==0)
                msg="可预约";
            else if(Flag==1)
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

    //已经预约的设备

    //可以预约的设备
    @RequestMapping("toequipmentUnexpected")
    public String equipmentUnexpected(Model model,HttpSession session){

        return "equipmentUnexpected";
    }

    //跳转到预约界面
    @RequestMapping("/toRequests")
    public String  toRequest(Member member, EquipmentPlus equipmentPlus,Model model,HttpSession session){
        //传请求得默认值，member.mid,equipmentPlus.eid
        //自动生成aid，flag
        //用户输入astart aend acomment aevaluate
        model.addAttribute("mid",member.getMid());
        model.addAttribute("eid",equipmentPlus.getEid());
        model.addAttribute("name",session.getAttribute("name"));
        Integer flag=0;
        Integer aid=applyService.ApplyCount();
        Apply apply=new Apply();
        apply.setAid(aid);
        apply.setEid(equipmentPlus.getEid());
        apply.setFlag(flag);
        apply.setMid(member.getMid());
        return "AllOfEquipment";
    }
}
