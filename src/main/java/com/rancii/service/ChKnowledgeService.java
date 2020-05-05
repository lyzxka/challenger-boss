package com.rancii.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rancii.entity.VO.KnowledgeVO;

import java.util.Map;

/**
 * <p>
 * 知识资料 服务类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChKnowledgeService extends IService<ChKnowledge> {

    Page<KnowledgeVO> selectKnowledgeForPage(Map map,Page page);
}
