package com.rancii.upload;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;

/**
 * Created by hantw on 2018/2/8.
 * todo: 七牛云上传
 */
public class QiniuUtil implements UploadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUtil.class);

    @Override
    public String upload(MultipartFile file) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
        String domainOfBucket = "http://devtools.qiniu.com";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(file.getInputStream(),key,upToken,null, null);
            if (response.isOK()){
                String address = URLEncoder.encode(response.address, "utf-8");
                String finalUrl = String.format("%s/%s", domainOfBucket, address);
                return finalUrl;
            }else{
                LOGGER.error("上传文件失败："+response.error);
            }
        } catch (Exception ex) {
            LOGGER.error("上传文件异常：",ex);
        }
        return null;
    }

    @Override
    public Boolean delet(String path) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            Response delete = bucketManager.delete(bucket, path);
            if (delete.isOK()){
                return true;
            }else{
                LOGGER.error("删除文件失败："+delete.error);
                return false;
            }
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            LOGGER.error(ex.response.toString());
        }
        return false;
    }

    @Override
    public String uploadNetFile(String url) {
        return null;
    }

    @Override
    public String uploadLocalImg(String localPath) {
        return null;
    }

    @Override
    public String uploadBase64(String base64) {
        return null;
    }
}
