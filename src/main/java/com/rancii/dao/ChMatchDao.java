package com.rancii.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChMatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rancii.entity.VO.MatchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 比赛 Mapper 接口
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChMatchDao extends BaseMapper<ChMatch> {

    List<MatchVO>selectMatchForPage(@Param("param") Map map, Page page);
}
