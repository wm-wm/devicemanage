package com.example.yuan;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class GetSqlSession{
    private static SqlSessionFactory sqlSessionFactory;  //SqlSessionFactory最好作为静态对象，不要多次创建
    static{
        try{
            InputStream inputStream = Resources.getResourceAsStream("application.yml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(false);
        //IDEA默认参数为false，即不自动提交事务,建议设置为false,在最后使用commit提交事务，否则有可能破数据库完整性
    }
}
