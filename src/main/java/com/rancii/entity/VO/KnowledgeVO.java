package com.rancii.entity.VO;

import com.rancii.entity.ChKnowledge;

public class KnowledgeVO extends ChKnowledge {
    private String userName;
    private String userPhone;
    private String categoryName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
