package com.example.yuan.service.serviceImpl;


import com.example.yuan.dao.LaboratoryMapper;
import com.example.yuan.pojo.Laboratory;
import com.example.yuan.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {
    @Autowired
    private LaboratoryMapper laboratoryMapper;
    @Override
    public Laboratory laboratoryByLid(Integer Lid){
        return laboratoryMapper.laboratoryByLid(Lid);
    }
}
