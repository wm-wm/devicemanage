package com.example.yuan.service;

import com.example.yuan.pojo.Member;

import java.util.List;

public interface MemberService {

    //查询所有用户
    public List<Member> SelectAllMember();

    //删除用户
    public boolean DeleteMember(Integer mid);


    Member memberLogin(Member member);
}
