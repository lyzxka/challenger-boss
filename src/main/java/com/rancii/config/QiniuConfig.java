/**
 * @ClassName QiniuConfig
 * @Description TODO
 * @Author Administrator
 * @Date 2018/8/20 0020 16:12
 * @Version 1.0
 **/
package com.rancii.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QiniuConfig {
    private static final Logger log = LoggerFactory.getLogger(QiniuConfig.class);


    @Value("${qiniu.path}")
    private  String path;
    @Value("${qiniu.qiniuAccess}")
    private  String qiniuAccess;
    @Value("${qiniu.qiniuKey}")
    private  String qiniuKey;
    @Value("${qiniu.bucketName}")
    private  String bucketName;

    private static Map<String,String> map = System.getenv();


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQiniuAccess() {
        return qiniuAccess;
    }

    public void setQiniuAccess(String qiniuAccess) {
        this.qiniuAccess = qiniuAccess;
    }

    public String getQiniuKey() {
        return qiniuKey;
    }

    public void setQiniuKey(String qiniuKey) {
        this.qiniuKey = qiniuKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }





    public Map<String,Object> getQiniu(){
      Map returnMap = new HashMap();
        if(null != map.get("path")&&
                null != map.get("qiniuAccess")&&
                null != map.get("qiniuKey")&&
                null != map.get("bucketName")){
            log.info("获取到配置文件路径:path="+map.get("path"));
            log.info("获取到配置文件配置:qiniuAccess="+map.get("qiniuAccess"));
            log.info("获取到配置文件配置:qiniuKey="+map.get("qiniuKey"));
            log.info("获取到配置文件配置:bucketName="+map.get("bucketName"));

            returnMap.put("bucketName", map.get("bucketName"));
            returnMap.put("path", map.get("path"));
            returnMap.put( "qiniuAccess", map.get("qiniuAccess"));
            returnMap.put( "qiniuKey",map.get("qiniuKey"));
            return returnMap;
        }
        log.info("获取到配置文件路径:path="+getPath());
        log.info("获取到配置文件配置:qiniuAccess="+getQiniuAccess());
        log.info("获取到配置文件配置:qiniuKey="+getQiniuKey());
        log.info("获取到配置文件配置:bucketName="+getBucketName());
        returnMap.put("bucketName", getBucketName());
        returnMap.put("path", getPath());
        returnMap.put( "qiniuAccess",getQiniuAccess());
        returnMap.put( "qiniuKey",getQiniuKey());
        return returnMap;
    }
}
