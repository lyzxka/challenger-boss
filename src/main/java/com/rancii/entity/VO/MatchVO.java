package com.rancii.entity.VO;

import com.rancii.entity.ChMatch;

public class MatchVO extends ChMatch {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
