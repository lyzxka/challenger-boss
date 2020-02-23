package com.rancii.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

/**
 * @program: pay-collectpay
 * @description: shiro标签
 * @author: ZhangLei
 * @create: 2018-09-20 11:55
 **/
@Component
public class ShiroTagsFreeMarkerCfg{

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }

}