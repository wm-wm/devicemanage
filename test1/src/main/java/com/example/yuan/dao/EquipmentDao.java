package com.example.yuan.dao;

import com.example.yuan.pojo.Category;
import com.example.yuan.pojo.Equipment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentDao {
    //查询所有仪器信息
    public List<Equipment> SelectAllEquipment();

    //添加仪器信息
    public int InsertEquipment(String ename,Integer cid);

    //删除仪器
    public int DeleteEquipment(int eid);

    //根据eid查询仪器
    public Equipment SelectEquipmentByEid(int eid);

    //修改仪器信息
    public int UpdateEquipment(Integer eid,String ename,Integer cid);


    @Select("SELECT eid,ename,category.cid,cname FROM equipment,category where equipment.cid=(SELECT cid FROM category WHERE lid=#{lid}) AND equipment.cid=category.cid")
    public List<Equipment>findallEquip(@Param("lid")int lid);
    //搜索查询
    @Select("SELECT eid,ename,category.cid,cname FROM equipment,category where equipment.cid=(SELECT cid FROM category WHERE lid=#{lid}) AND ename like #{ename} AND equipment.cid=category.cid")
    public List<Equipment>findallEquipByEname(@Param("lid")int lid,@Param("ename")String ename);

    @Update("UPDATE equipment SET ename=#{ename},cid=#{cid} WHERE eid=#{eid}")
    public void changeEquipment(@Param("ename")String ename,@Param("cid")int cid,@Param("eid")int eid);

    @Delete("DELETE FROM equipment WHERE eid=#{eid}")
    public void deleteEquipment(@Param("eid")int eid);

    @Insert("INSERT INTO equipment(eid,ename,cid) VALUES(#{eid},#{ename},#{cid})")
    public void insertNewEquipment(@Param("eid")int eid,@Param("ename")String ename,@Param("cid")int cid);

    @Select("SELECT * FROM category WHERE lid=#{lid}")
    public List<Category>findallCategory(@Param("lid")int lid);


    List<Equipment> equipmentAll();
    //根据Eid检索设备
    Equipment equipmentOfEid(Integer eid);
    //展示已预约设备
    Equipment equipmentReserved(Integer flag);
    //展示未预约设备
    Equipment equipmentUnreserved();
    //根据mid检索设备
    Equipment equipmentOfMid(Integer Mid);
    //更新flag字段
    void updatFlag(Integer Eid,Integer Flag);
}
