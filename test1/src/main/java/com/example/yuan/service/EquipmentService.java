package com.example.yuan.service;

import com.example.yuan.pojo.Category;
import com.example.yuan.pojo.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipmentService {
    //查询所有仪器
    public List<Equipment> SelectAllEquipment();
    //添加仪器信息
    public boolean InsertEquipment(String ename,Integer cid);
    //删除仪器
    public boolean DeleteEquipment(int eid);
    //根据eid查询仪器
    public Equipment SelectEquipmentByEid(int eid);
    //修改仪器信息
    public boolean UpdateEquipment(Integer eid,String ename,String cname);


    public List<Equipment> findallEquip(@Param("lid")int lid);
    public void changeEquipment(@Param("ename")String ename,@Param("cid")int cid,@Param("eid")int eid);
    public void deleteEquipment(@Param("eid")int eid);
    public void insertNewEquipment(@Param("eid")int eid,@Param("ename")String ename,@Param("cid")int cid);
    public List<Category>findallCategory(@Param("lid")int lid);
    public List<Equipment>findallEquipByEname(@Param("lid")int lid,@Param("ename")String ename);

    List<Equipment> equipmentAll();
    //根据Eid检索设备
    Equipment equipmentOfEid(Integer eid);
    //展示已预约设备或者未预约的设备
    Equipment equipmentReserved(Integer flag);
    //根据mid检索设备
    Equipment equipmentOfMid(Integer Mid);
    //更新flag字段
    void updatFlag(Integer Eid,Integer Flag);

}
