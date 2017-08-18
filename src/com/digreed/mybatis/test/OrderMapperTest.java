package com.digreed.mybatis.test;

import com.digreed.mybatis.mapper.OrderMapper;
import com.digreed.mybatis.mapper.UserMapper;
import com.digreed.mybatis.po.OrderCustom;
import com.digreed.mybatis.po.Orders;
import com.digreed.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class OrderMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    //    查询订单关联用户信息
    @Test
    public void testFindOrdersUser() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderCustom> ordersUser = mapper.findOrdersUser();
        System.out.println(ordersUser);
        sqlSession.close();
    }

    //查询订单及订单明细的信息
    @Test
    public void testFindOrdersAndOrderDetailResultMap() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findOrdersAndOrderDetailResultMap();
        System.out.println(list);
        sqlSession.close();
    }

    //查询用户及购买信息
    @Test
    public void testFindUserAndItemsResultMap() throws IOException{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<User> list = mapper.findUserAndItemsResultMap();
        System.out.println(list);//使用断点可查看购买信息
        sqlSession.close();
    }

    //查询订单关联用户，用户信息使用延迟加载
    @Test
    public void testFindOrdersUserlazyLoading() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> list = mapper.findOrdersUserlazyLoading();
        //遍历订单列表
        for (Orders orders : list){
            User user = orders.getUser();
            System.out.println(user);
        }
        sqlSession.close();
    }

    //测试一级缓存
    @Test
    public void testCache1() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //第一次发起请求，查询id为1的用户
        User user1 = mapper.findUserById(1);
        System.out.println(user1);
        //如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

        //更新user1的信息
        user1.setUsername("测试用户22");
        mapper.updateUser(user1);
        //执行commit操作去清空缓存
        sqlSession.commit();

        //第二次发起请求，查询id为1的用户
        User user2 = mapper.findUserById(1);
        System.out.println(user2);
        sqlSession.close();
    }

    //二级缓存测试
    @Test
    public void testCache2() throws IOException {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        //创建代理对象
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);
        //第一次发起查询请求
        User user1 = mapper1.findUserById(1);
        System.out.println(user1);
        //这里执行关闭操作，将sqlSession中的数据写的二级缓存区域
        sqlSession1.close();

        //使用sqlSession2执行commit()操作
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user = mapper2.findUserById(1);
        user.setUsername("叶大大");
        mapper2.updateUser(user);
        sqlSession2.commit();
        sqlSession2.close();

        //发起第二次请求
        UserMapper mapper3 = sqlSession3.getMapper(UserMapper.class);
        User user3 = mapper3.findUserById(1);
        sqlSession3.close();
    }


}

