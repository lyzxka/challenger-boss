package com.rancii.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rancii.entity.Site;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hantw
 * @since 2017-12-30
 */
public interface SiteService extends IService<Site> {

    Site getCurrentSite();

    void updateSite(Site site);
	
}
