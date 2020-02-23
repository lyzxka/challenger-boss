package com.rancii.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rancii.base.DataEntity;

/**
 * @Author: zhanglei
 * @Date: 2018/8/20 14:28
 * @Description:
 */
@TableName("area_ls")
public class Area extends DataEntity<Area> {

    private static final long serialVersionUID = 1L;


    /**
     * 父id
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * code
     */
    @TableField("area_id")
    private String areaId;
    /**
     * 名称
     */
    @TableField("area_name")
    private String areaName;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

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

    @Override
    public String toString() {
        return "Area{" +
                "parentId='" + parentId + '\'' +
                ", areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
