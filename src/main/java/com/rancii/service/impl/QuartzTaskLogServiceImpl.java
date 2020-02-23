package com.rancii.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rancii.entity.QuartzTaskLog;
import com.rancii.dao.QuartzTaskLogDao;
import com.rancii.service.QuartzTaskLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 任务执行日志 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2018-01-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QuartzTaskLogServiceImpl extends ServiceImpl<QuartzTaskLogDao, QuartzTaskLog> implements QuartzTaskLogService {

}
