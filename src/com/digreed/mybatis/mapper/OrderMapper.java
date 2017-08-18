package com.digreed.mybatis.mapper;

import com.digreed.mybatis.po.OrderCustom;
import com.digreed.mybatis.po.Orders;
import com.digreed.mybatis.po.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public interface OrderMapper {
    //查询订单关联用户信息
    public List<OrderCustom> findOrdersUser() throws IOException;
    //查询订单及订单明细的信息
    public List<Orders> findOrdersAndOrderDetailResultMap()throws  IOException;
    //查询用户及购买信息
    public List<User> findUserAndItemsResultMap() throws IOException;
    //延迟加载
    public List<Orders> findOrdersUserlazyLoading() throws IOException;
}
