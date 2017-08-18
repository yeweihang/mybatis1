package com.digreed.mybatis.test;

import com.digreed.mybatis.dao.UserDao;
import com.digreed.mybatis.dao.UserDaoimpl;
import com.digreed.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/12 0012.
 */
public class UserDaoImplTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //得到会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    @Test
    public void findUserByIdTest() throws IOException {
        UserDao userDao = new UserDaoimpl(sqlSessionFactory);
        User user = userDao.findUserById(28);
        System.out.println(user);
    }
}
