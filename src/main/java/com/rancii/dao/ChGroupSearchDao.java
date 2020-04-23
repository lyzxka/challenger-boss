package com.rancii.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChGroupSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rancii.entity.VO.GroupSearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组队请求 Mapper 接口
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChGroupSearchDao extends BaseMapper<ChGroupSearch> {
    List<GroupSearchVO> selectGroupSearchForPage(@Param("params") Map map, Page page);
}
