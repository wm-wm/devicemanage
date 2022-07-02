package com.example.yuan.service.serviceImpl;


import com.example.yuan.dao.CategoryMapper;
import com.example.yuan.pojo.Category;
import com.example.yuan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Category categoryByCid(Integer Cid){
        return categoryMapper.categoryByCid(Cid);
    }
}
