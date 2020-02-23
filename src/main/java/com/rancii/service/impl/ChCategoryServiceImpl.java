package com.rancii.service.impl;

import com.rancii.entity.ChCategory;
import com.rancii.dao.ChCategoryDao;
import com.rancii.service.ChCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 门类 服务实现类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChCategoryServiceImpl extends ServiceImpl<ChCategoryDao, ChCategory> implements ChCategoryService {

}
