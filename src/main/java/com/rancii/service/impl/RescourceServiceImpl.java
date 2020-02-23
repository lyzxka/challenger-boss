package com.rancii.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rancii.entity.Rescource;
import com.rancii.dao.RescourceDao;
import com.rancii.service.RescourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统资源 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2018-01-14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RescourceServiceImpl extends ServiceImpl<RescourceDao, Rescource> implements RescourceService {

    @Override
    public int getCountByHash(String hash) {
        QueryWrapper<Rescource> wrapper = new QueryWrapper<>();
        wrapper.eq("hash",hash);
        return baseMapper.selectCount(wrapper);
    }

    @Override
    public Rescource getRescourceByHash(String hash) {
        QueryWrapper<Rescource> wrapper = new QueryWrapper<>();
        wrapper.eq("hash",hash);
        return baseMapper.selectOne(wrapper);
    }
}
