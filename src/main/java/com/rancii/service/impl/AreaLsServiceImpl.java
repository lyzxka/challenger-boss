package com.rancii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rancii.dao.AreaLsDao;
import com.rancii.entity.AreaLs;
import com.rancii.service.AreaLsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: Administrator
 * @Date: 2018/11/2 0002 14:44
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaLsServiceImpl extends ServiceImpl<AreaLsDao,AreaLs> implements AreaLsService {
}
