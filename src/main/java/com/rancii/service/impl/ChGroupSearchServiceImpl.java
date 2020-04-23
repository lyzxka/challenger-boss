package com.rancii.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChGroupSearch;
import com.rancii.dao.ChGroupSearchDao;
import com.rancii.entity.VO.GroupSearchVO;
import com.rancii.service.ChGroupSearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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

    @Override
    public Page<GroupSearchVO> selectGroupSearchForPage(Map map, Page page) {
        page.setRecords(baseMapper.selectGroupSearchForPage(map,page));
        return page;
    }
}
