package com.digreed.mybatis.mapper;

import com.digreed.mybatis.po.User;
import com.digreed.mybatis.po.UserCustom;
import com.digreed.mybatis.po.UserQueryVo;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/8/12 0012.
 */
public interface UserMapper {
    //根据用户id查询用户信息
    public User findUserById(int id) throws IOException;
    public List<User> findUserByName(String username) throws IOException;
    //添加用户
    public void insertUser(User user) throws IOException;
    //删除用户
    public void deleteUser(int id) throws IOException;
    //根据用户id更新用户
    public void updateUser(User user) throws IOException;

    public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws IOException;

//    结果解映射查询
    public User findUserByIdResultMap(int id) throws IOException;
}
