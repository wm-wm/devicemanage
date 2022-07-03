package com.example.yuan.service.serviceImpl;


import com.example.yuan.dao.MemberDao;
import com.example.yuan.pojo.Member;
import com.example.yuan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    //查询所有用户
    public List<Member> SelectAllMember()
    {
        return memberDao.SelectAllMember();
    }


    //删除用户
    public boolean DeleteMember(Integer mid)
    {
        int i = memberDao.DeleteMember(mid);
        if (i>0){return true;}
        else {return false;}
    }

    @Override
    public Member memberLogin(Member member){

        Member m=memberDao.memberLogin(member);
        return m;
    }
    //数目
    @Override
    public  Integer selectAccount(){
        return memberDao.selectAccount();
    }

    //修改用户信息
    public boolean memberModify(Integer mid,String mname,String mmail,String mpw)
    {
        int i = memberDao.memberModify(mid,mname,mmail,mpw);
        if(i>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //增加一个用户
    @Override
    public  void addMember(Integer mid,String Mname,String Mmail,String mpw){
        memberDao.addMember(mid,Mname,Mmail,mpw);
    }
}
