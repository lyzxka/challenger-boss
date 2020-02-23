package com.rancii.service.impl;

import com.rancii.entity.ChGroupSearch;
import com.rancii.dao.ChGroupSearchDao;
import com.rancii.service.ChGroupSearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 组队请求 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChGroupSearchServiceImpl extends ServiceImpl<ChGroupSearchDao, ChGroupSearch> implements ChGroupSearchService {

}
