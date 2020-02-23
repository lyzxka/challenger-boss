package com.rancii.service.impl;

import com.rancii.entity.ChGroup;
import com.rancii.dao.ChGroupDao;
import com.rancii.service.ChGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 队伍 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChGroupServiceImpl extends ServiceImpl<ChGroupDao, ChGroup> implements ChGroupService {

}
