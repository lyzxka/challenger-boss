package com.rancii.upload;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by hantw on 2018/2/26.
 * todo:
 */
public class UploadHandler implements InvocationHandler {

    private UploadUtil upload;

    public UploadHandler(UploadUtil upload) {
        this.upload = upload;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(upload, args);
        return null;
    }
}
