package com.example.yuan.dao;


import com.example.yuan.pojo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberDao {
    //查询所有用户
    public List<Member> SelectAllMember();
    Member memberLogin(Member member);

    //根据姓名查询用户


    //修改用户信息

    //删除用户
    public int DeleteMember(Integer mid);

}
