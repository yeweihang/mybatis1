package com.digreed.mybatis.dao;

import com.digreed.mybatis.po.User;

import java.io.IOException;

/**
 * Created by Administrator on 2017/8/12 0012.
 */
public interface UserDao {
    //根据用户id查询用户信息
    public User findUserById(int id) throws IOException;
    //添加用户
    public void insertUser(User user) throws IOException;
    //删除用户信息
    public void deleteUser(int id) throws  IOException;
}
