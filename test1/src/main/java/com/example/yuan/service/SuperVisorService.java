package com.example.yuan.service;

import com.example.yuan.pojo.SuperVisor;

public interface SuperVisorService {
    //根据id和password查询
    SuperVisor selectSuperVisor(Integer sid, String spw);
}
