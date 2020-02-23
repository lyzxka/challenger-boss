package com.rancii.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rancii.dao.GroupDao;
import com.rancii.entity.Group;
import com.rancii.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hantw
 * @since 2017-10-31
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GroupServiceImpl extends ServiceImpl<GroupDao, Group> implements GroupService {
	
}
