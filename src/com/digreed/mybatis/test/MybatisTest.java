package com.digreed.mybatis.test;

import com.digreed.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class MybatisTest {

    //根据用户id查询用户信息
    @Test
    public void findUserByIdTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //通过话工厂得到SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println(user);
        //关闭会话
        sqlSession.close();
    }

    //根据用户名模糊查询用户信息
    @Test
    public void findUserByNameTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //通过会话工厂得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("test.findUserByName", "小明");
        System.out.println(userList);
        //关闭会话
        sqlSession.close();
    }

    //添加用户
    @Test
    public void insertUserTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //创建会话工厂，传入mybatis的配置文件信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //通过会话工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //插入用户
        User user = new User();
        user.setUsername("叶小叶");
        user.setSex("1");
        user.setAddress("安徽宿州");
        user.setBirthday(new Date());
        sqlSession.update("test.insertUser",user);
        System.out.println("-----"+user);

        //提交事务
        sqlSession.commit();
        //关闭会话
        sqlSession.close();
    }

    //删除用户
    @Test
    public void deleteUserTest() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //得到会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql
        sqlSession.delete("test.deleteUser", 27);
        //提交事务
        sqlSession.commit();
        //关闭事务
        sqlSession.close();
    }

    //根据用户id更新数据
    @Test
    public void updateUser() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        //得到会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //得到sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(28);
        user.setUsername("叶新");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("安徽黄山");
        sqlSession.update("test.updateUser",user);

        //提交事务
        sqlSession.commit();
        //关闭事务
        sqlSession.close();
    }

}

