package com.rancii.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.rancii.config.QcloudSmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zhanglei
 * @Date: 2018/8/21 16:35
 * @Description: 腾讯发送短信util
 */
public class QcloudSms {

    private static final Logger LOGGER = LoggerFactory.getLogger(QcloudSms.class);

    /**
     * 发送单条短信
     */
    public static String singleSms(String context,String phone,QcloudSmsConfig config){
        try {
            SmsSingleSender ssender = new SmsSingleSender(Integer.valueOf(config.getAppid()), config.getAppkey());
            SmsSingleSenderResult result = ssender.send(0, "86", phone,
                    "【"+ config.getSign()+"】"+context, "", "");
           return "["+result.result+"]"+result.errMsg;
        } catch (Exception e) {
            LOGGER.error("发送短信异常：",e);
            return e.getMessage();
        }
    }

}
