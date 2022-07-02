package com.example.yuan.service;

import com.example.yuan.pojo.Apply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyService {
    //查询所有预约信息
    public List<Apply> SelectAllApply();



    public void approve(@Param("flag")int flag, @Param("aid")int aid);
    public List<Apply> findall(@Param("lid")int lid);
    public void aevaluateChange(@Param("aevaluate")String aevaluate,@Param("aid")int aid);


    Integer ApplyCount();
    //根据mid查询eid
    List<Apply> SelectByMid(Integer Mid);
    //addApply
    void AddApply(Apply apply);
}
