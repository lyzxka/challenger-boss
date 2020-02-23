package com.rancii.service.impl;

import com.rancii.entity.ChKnowledge;
import com.rancii.dao.ChKnowledgeDao;
import com.rancii.service.ChKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 知识资料 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChKnowledgeServiceImpl extends ServiceImpl<ChKnowledgeDao, ChKnowledge> implements ChKnowledgeService {

}
