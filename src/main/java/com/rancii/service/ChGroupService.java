package com.rancii.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rancii.entity.VO.GroupVO;

import java.util.Map;

/**
 * <p>
 * 队伍 服务类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChGroupService extends IService<ChGroup> {
    Page<GroupVO> selectGroupForPage(Map map, Page page);
}
