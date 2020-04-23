package com.rancii.entity.VO;

import com.rancii.entity.ChGroup;

/**
 * auther: zzxka
 * date: 2020/4/24
 * description:
 */
public class GroupVO extends ChGroup {
    private String userPhone;
    private String userName;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
