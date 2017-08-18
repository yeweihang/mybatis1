package com.digreed.mybatis.test;

import com.digreed.mybatis.mapper.UserMapper;
import com.digreed.mybatis.po.User;
import com.digreed.mybatis.po.UserCustom;
import com.digreed.mybatis.po.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/12 0012.
 */
public class MapperTest {
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
    public void testFindUserById() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(29);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testFindUserByName()throws IOException{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findUserByName("小明");
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testInsertUser() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("王小二");
        user.setSex("1");
        user.setAddress("安徽黄山");
        user.setBirthday(new Date());
        mapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteUser() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(30);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateUser() throws IOException{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(29);
        user.setUsername("叶斯");
        user.setSex("2");
        user.setBirthday(new Date());
        user.setAddress("安徽黄山");
        mapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

//    @Test
//    public void testFindUserList() throws IOException {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        UserQueryVo userQueryVo = new UserQueryVo();
//        UserCustom userCustom = new UserCustom();
////        由于这里使用动态sql，当不设置值时，条件不会拼接到sql中
////        userCustom.setSex("1");
//        userCustom.setUsername("小明");
//        userQueryVo.setUserCustom(userCustom);
//        List<UserCustom> list = mapper.findUserList(userQueryVo);
//        System.out.println(list);
//    }
    @Test
    public void testFindUserList() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        //添加ids
        List<Integer> ids = new ArrayList<>();
        ids.add(25);
        ids.add(16);
        ids.add(10);
//        由于这里使用动态sql，当不设置值时，条件不会拼接到sql中
//        userCustom.setSex("1");
        userCustom.setUsername("小明");
        userQueryVo.setIds(ids);
        userQueryVo.setUserCustom(userCustom);
        List<UserCustom> list = mapper.findUserList(userQueryVo);
        System.out.println(list);
    }

    @Test
    public void testFindUserByIdResultMap() throws IOException{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserByIdResultMap(29);
        System.out.println(user);
    }

}
