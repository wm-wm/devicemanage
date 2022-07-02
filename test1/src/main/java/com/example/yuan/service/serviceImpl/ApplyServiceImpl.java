package com.example.yuan.service.serviceImpl;


import com.example.yuan.dao.ApplyDao;
import com.example.yuan.pojo.Apply;
import com.example.yuan.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyDao applyDao;
    //查询所有预约信息
    public List<Apply> SelectAllApply()
    {
        return applyDao.SelectAllApply();
    }


    @Override
    public void approve(int flag, int aid) {
        applyDao.approve(flag,aid);
    }

    @Override
    public List<Apply> findall(int lid) {
        return applyDao.findall(lid);
    }

    @Override
    public void aevaluateChange(String aevaluate, int aid) {
        applyDao.aevaluateChange(aevaluate, aid);
    }


    @Override
    public Integer ApplyCount(){
        return applyDao.ApplyCount();
    }
    //根据mid查询eid
    @Override
    public List<Apply> SelectByMid(Integer Mid){
        return applyDao.SelectByMid(Mid);
    }
    //addApply
    @Override
    public  void AddApply(Apply apply){
        applyDao.AddApply(apply);
    }
}
