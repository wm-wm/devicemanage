package com.example.yuan.dao;

import com.example.yuan.pojo.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApplyDao {
    //查询所有预约信息
    public List<Apply> SelectAllApply();

    @Select("SELECT * FROM apply WHERE eid=ANY(SELECT eid FROM equipment WHERE cid=ANY(SELECT cid FROM category WHERE lid=#{lid}))")
    public List<Apply> findall(@Param("lid")int lid);

    @Update("UPDATE apply SET flag=#{flag} WHERE aid=#{aid}")
    public void approve(@Param("flag")int flag,@Param("aid")int aid);

    @Update("UPDATE apply SET aevaluate=#{aevaluate} WHERE aid=#{aid}")
    public void aevaluateChange(@Param("aevaluate")String aevaluate,@Param("aid")int aid);

    Integer ApplyCount();
    //根据mid查询eid
    List<Apply> SelectByMid(Integer Mid);
    //addApply
    void AddApply(Apply apply);
}
