package com.example.yuan.service;

import com.example.yuan.pojo.Member;

import java.util.List;

public interface MemberService {

    //查询所有用户
    public List<Member> SelectAllMember();

    //删除用户
    public boolean DeleteMember(Integer mid);


    Member memberLogin(Member member);

    //数目
    Integer selectAccount();

    //修改用户信息
    public boolean memberModify(Integer mid,String mname,String mmail,String mpw);

    //增加一个用户
    void addMember(Integer mid,String Mname,String Mmail,String mpw);
}
