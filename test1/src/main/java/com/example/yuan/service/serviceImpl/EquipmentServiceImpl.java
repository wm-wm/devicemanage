package com.example.yuan.service.serviceImpl;

import com.example.yuan.dao.EquipmentDao;
import com.example.yuan.pojo.Category;
import com.example.yuan.pojo.Equipment;
import com.example.yuan.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService
{
    @Autowired
    private EquipmentDao equipmentDao;
    //查询仪器信息
    public List<Equipment> SelectAllEquipment()
    {
        return equipmentDao.SelectAllEquipment();
    }

    //添加仪器信息
    public boolean InsertEquipment(String ename,Integer cid)
    {
        int i = equipmentDao.InsertEquipment(ename,cid);
        if(i>0){return true;}
        else {return false;}
    }
    //删除仪器
    public boolean DeleteEquipment(int eid)
    {
        int i = equipmentDao.DeleteEquipment(eid);
        if (i>0){return true;}
        else {return false;}
    }
    //根据eid查询仪器
    public Equipment SelectEquipmentByEid(int eid)
    {
        return equipmentDao.SelectEquipmentByEid(eid);
    }
    //修改仪器信息
    public boolean UpdateEquipment(Integer eid,String ename,Integer cid)
    {
        int i = equipmentDao.UpdateEquipment(eid,ename,cid);
        if(i>0){return true;}
        else {return false;}
    }


    @Override
    public List<Equipment> findallEquip(int lid) {
        return equipmentDao.findallEquip(lid);
    }

    @Override
    public void changeEquipment(String ename, int cid, int eid) {
        equipmentDao.changeEquipment(ename,cid,eid);
    }

    @Override
    public void deleteEquipment(int eid) {
        equipmentDao.deleteEquipment(eid);
    }

    @Override
    public void insertNewEquipment(int eid, String ename, int cid) {
        equipmentDao.insertNewEquipment(eid,ename,cid);
    }

    @Override
    public List<Category> findallCategory(int lid) {
        return equipmentDao.findallCategory(lid);
    }

    @Override
    public List<Equipment> findallEquipByEname(int lid, String ename) {
        ename='%'+ename+'%';
        return equipmentDao.findallEquipByEname(lid, ename);
    }


    @Override
    public List<Equipment> equipmentAll(){
        List<Equipment> e=equipmentDao.equipmentAll();
        return e;
    }
    //根据Eid检索设备
    @Override
    public Equipment equipmentOfEid(Integer eid){
        return equipmentDao.equipmentOfEid(eid);
    }
    //展示已预约设备或者未预约设备
    @Override
    public Equipment equipmentReserved(Integer flag){
        Equipment e=equipmentDao.equipmentReserved(flag);
        return e;
    }
    @Override

    //根据mid检索设备
    public Equipment equipmentOfMid(Integer Mid){
        Equipment e=equipmentDao.equipmentOfMid(Mid);
        return e;
    }

    @Override
    public  void updatFlag(Integer Eid,Integer Flag){
        equipmentDao.updatFlag(Eid,Flag);
    }
}
