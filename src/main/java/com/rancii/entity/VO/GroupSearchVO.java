package com.rancii.entity.VO;

import com.rancii.entity.ChGroupSearch;

/**
 * auther: zzxka
 * date: 2020/4/23
 * description:
 */
public class GroupSearchVO extends ChGroupSearch {
    private String matchName;
    private String userName;
    private String userIcon;
    private String categoryName;
    private String userPhone;

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
