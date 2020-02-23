package com.rancii.service.impl;

import com.rancii.entity.ChMatch;
import com.rancii.dao.ChMatchDao;
import com.rancii.service.ChMatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 比赛 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChMatchServiceImpl extends ServiceImpl<ChMatchDao, ChMatch> implements ChMatchService {

}
