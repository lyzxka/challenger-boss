package com.rancii.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChMatch;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rancii.entity.VO.MatchVO;

import java.util.Map;

/**
 * <p>
 * 比赛 服务类
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
public interface ChMatchService extends IService<ChMatch> {
    Page<MatchVO>selectMatchForPage(Map map, Page page);
}
