package com.rancii.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: zhanglei
 * @Date: 2018/8/21 17:01
 * @Description:
 */
@Component
public class QcloudSmsConfig {

    @Value("${sms.appid}")
    private  String appid;
    @Value("${sms.appkey}")
    private  String appkey;
    @Value("${sms.sign}")
    private  String sign;
    @Value("${sms.on-off}")
    private boolean onOff;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }
}
