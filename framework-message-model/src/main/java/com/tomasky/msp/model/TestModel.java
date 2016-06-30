package com.tomasky.msp.model;

import java.util.List;

/**
 * 测试实体
 * Created by 番茄桑 on 2015/6/12.
 */
public class TestModel {
    private String userName;
    private String password;
    private List<String> authList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthList() {
        return authList;
    }

    public void setAuthList(List<String> authList) {
        this.authList = authList;
    }
}
