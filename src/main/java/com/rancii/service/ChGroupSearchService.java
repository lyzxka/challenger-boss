package com.rancii.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChGroupSearch;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rancii.entity.VO.GroupSearchVO;

import java.util.Map;

/**
 * <p>
 * 组队请求 服务类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChGroupSearchService extends IService<ChGroupSearch> {
    Page<GroupSearchVO> selectGroupSearchForPage(Map map, Page page);
}
