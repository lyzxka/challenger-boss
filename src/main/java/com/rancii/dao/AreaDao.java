package com.rancii.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rancii.entity.Area;

import java.util.List;

/**
 * @Author: zhanglei
 * @Date: 2018/8/20 14:33
 * @Description:
 */
public interface AreaDao extends BaseMapper<Area> {

    /**
     * 获取所有的省份
     * @return
     */
    List<Area> selectAllProvince();

    /**
     * 获取市
     * @return
     */
    List<Area> selectCityByParentId(String id);

    /**
     * 获取市
     * @return
     */
    Area selectAreaById(String id);

}
