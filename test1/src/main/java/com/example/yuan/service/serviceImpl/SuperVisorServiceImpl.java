package com.example.yuan.service.serviceImpl;

import com.example.yuan.dao.SuperVisorDao;
import com.example.yuan.pojo.SuperVisor;
import com.example.yuan.service.SuperVisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperVisorServiceImpl implements SuperVisorService {
    @Autowired
    SuperVisorDao superVisorDao;
    //根据id和password查询
    @Override
    public SuperVisor selectSuperVisor(Integer sid, String spw){
        return superVisorDao.selectSuperVisor(sid,spw);
    }
}
