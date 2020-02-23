package com.rancii.service.impl;

import com.rancii.entity.ChUser;
import com.rancii.dao.ChUserDao;
import com.rancii.service.ChUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChUserServiceImpl extends ServiceImpl<ChUserDao, ChUser> implements ChUserService {

}
