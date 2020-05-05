package com.rancii.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rancii.entity.VO.KnowledgeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识资料 Mapper 接口
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChKnowledgeDao extends BaseMapper<ChKnowledge> {
    List<KnowledgeVO> selectKnowledgeForPage(@Param("param") Map map, Page page);
}
