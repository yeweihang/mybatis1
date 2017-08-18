package com.digreed.mybatis.dao;

import com.digreed.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2017/8/12 0012.
 */
public class UserDaoimpl implements UserDao {
    //需要向dao实现类中注入SqlSessionFactory
    //可以通过构造方法注入
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoimpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(int id) throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserById", id);
        //释放资源
        sqlSession.close();
        return user;

    }

    @Override
    public void insertUser(User user) throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("test.insertUSer",user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    @Override
    public void deleteUser(int id) throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("test.deleteUser",id);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
