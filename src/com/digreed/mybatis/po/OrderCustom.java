package com.digreed.mybatis.po;

/**
 * Created by Administrator on 2017/8/13 0013.
 */
public class OrderCustom extends Orders {
/*    添加用户属性
    user.username,
    user.sex,
    user.address*/
    private String username;
    private String sex;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
