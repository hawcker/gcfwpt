package com.gcfwpt.designonline.callback;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Response;


/**
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 */
public abstract class ObjectJsonCallback<T> extends EncryptCallback<T> {

    private Class<T> clazz;
    private Type type;

    public ObjectJsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public ObjectJsonCallback(Type type) {
        this.type = type;
    }

    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String data = response.body().string();
        Log.i("okhttp",data);
        String responseData = new String(data.getBytes(),"utf-8");
        Log.i("okhttp",responseData);
        if (TextUtils.isEmpty(responseData)) return null;

        /**
         * 一般来说，服务器返回的响应码都包含 code，msg，data 三部分，在此根据自己的业务需要完成相应的逻辑判断
         * 以下只是一个示例，具体业务具体实现
         */
        JSONObject jsonObject = new JSONObject(responseData);
        String username = jsonObject.getString("data");
        String code = jsonObject.getString("code");
        if (code.equals("1") && clazz == String.class) return (T) username;
        //String data = jsonObject.optString("data", "");
//        switch (code) {
//            case 1:
//                /**
//                 * code = 0 代表成功，默认实现了Gson解析成相应的实体Bean返回，可以自己替换成fastjson等
//                 * 对于返回参数，先支持 String，然后优先支持class类型的字节码，最后支持type类型的参数
//                 */
//                //if (clazz == String.class) return (T) data;
//                if (clazz == String.class) return (T) username;
//               /// if (clazz != null) return new Gson().fromJson(data, clazz);
//                //if (type != null) return new Gson().fromJson(data, type);
//                break;
//            case 104:
//                //比如：用户授权信息无效，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
//                throw new IllegalStateException("用户授权信息无效");
//            case 105:
//                //比如：用户收取信息已过期，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
//                throw new IllegalStateException("用户收取信息已过期");
//            case 106:
//                //比如：用户账户被禁用，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
//                throw new IllegalStateException("用户账户被禁用");
//            case 300:
//                //比如：其他乱七八糟的等，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
//                throw new IllegalStateException("其他乱七八糟的等");
//            default:
//                throw new IllegalStateException("错误代码：" + code + "，错误信息：" + username);
//        }
//        //如果要更新UI，需要使用handler，可以如下方式实现，也可以自己写handler
//        OkHttpUtils.getInstance().getDelivery().post(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(OkHttpUtils.getContext(), "错误代码：" + code + "，错误信息：" + username, Toast.LENGTH_SHORT).show();
//            }
//        });
        throw new IllegalStateException("错误代码：" + code + "，错误信息：" + username);
    }
}
