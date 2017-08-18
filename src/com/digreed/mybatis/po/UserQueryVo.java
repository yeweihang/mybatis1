package com.digreed.mybatis.po;

import java.util.List;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class UserQueryVo {
    private UserCustom userCustom;
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }
    //可以包装其他查询条件
}