package com.rancii.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rancii.entity.VO.GroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 队伍 Mapper 接口
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChGroupDao extends BaseMapper<ChGroup> {
    List<GroupVO> selectGroupForPage(@Param("params") Map map, Page page);
}
