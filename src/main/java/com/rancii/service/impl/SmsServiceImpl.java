package com.rancii.service.impl;

import com.rancii.config.QcloudSmsConfig;
import com.rancii.service.SmsService;
import com.rancii.util.QcloudSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zhanglei
 * @Date: 2018/8/21 17:09
 * @Description:
 */
@Service
public class SmsServiceImpl implements SmsService{

    @Autowired
    QcloudSmsConfig config;

    public String sendSms(String code,String phone){
        if (Boolean.valueOf(config.isOnOff())){
            return QcloudSms.singleSms("您的短信验证码是："+code,phone,config);
        }else{
            return "短信开关为关闭状态，短信验证码:"+code;
        }
    }

}
