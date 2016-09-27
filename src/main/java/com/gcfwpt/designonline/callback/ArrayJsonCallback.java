package com.gcfwpt.designonline.callback;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * 此类是开源框架OkhttpUtils3.0的网络数据返回请求接口
 * 用于默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 */
public abstract class ArrayJsonCallback<T> extends EncryptCallback<T> {

    private Class<T> clazz;
    private Type type;

    public ArrayJsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public ArrayJsonCallback(Type type) {
        this.type = type;
    }

    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String responseData = response.body().string();
        if (TextUtils.isEmpty(responseData)) return null;

        if (clazz != null) return new Gson().fromJson(responseData, clazz);
        if (type != null) return new Gson().fromJson(responseData, type);
        return null;
    }
}
