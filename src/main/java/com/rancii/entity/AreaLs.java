package com.rancii.entity;


import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Auther: lmk
 * @Date: 2018/11/2 0002 14:56
 * @Description: 城市区域
 */
public class AreaLs {
    /**
     * 区域id
     */
    @TableField("area_id")
    private String areaId;
    /**
     * 区域名称
     */
    @TableField("area_name")
    private String areaName;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
