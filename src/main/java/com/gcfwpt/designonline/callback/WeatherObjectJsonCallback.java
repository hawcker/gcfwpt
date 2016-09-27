package com.gcfwpt.designonline.callback;

import android.text.TextUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.Response;


/**
 * 此app中天气返回数据解析接口
 */
public abstract class WeatherObjectJsonCallback<T> extends EncryptCallback<T> {

    private Class<T> clazz;
    private Type type;

    public WeatherObjectJsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public WeatherObjectJsonCallback(Type type) {
        this.type = type;
    }

    //该方法是子线程处理，不能做ui相关的工作
    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        String responseData = response.body().string();
        if (TextUtils.isEmpty(responseData)) return null;

        /**
         * 一般来说，服务器返回的响应码都包含 code，msg，data 三部分，在此根据自己的业务需要完成相应的逻辑判断
         * 以下只是一个示例，具体业务具体实现
         */
        JSONObject jsonObject = new JSONObject(responseData);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONObject data = result.getJSONObject("data");
        JSONObject realtime = data.getJSONObject("realtime");
        String areaname = realtime.getString("city_name");
        JSONObject weather = realtime.getJSONObject("weather");
        JSONObject wind = realtime.getJSONObject("wind");
        String weathericon;
        if(Integer.parseInt(weather.getString("img")) < 10){
            weathericon = "http://ys-mobile.tjaide.com/images/weather/0" + weather.getString("img") + ".png";
        }else {
            weathericon = "http://ys-mobile.tjaide.com/images/weather/" + weather.getString("img") + ".png";
        }
        String temperature = weather.getString("temperature");
        String weathername = weather.getString("info");
        String wind_direction = wind.getString("direct");
        String wind_scale = wind.getString("power");

        //final int code = jsonObject.optInt("code", 0);
        //String data = jsonObject.optString("data", "");
        //如果要更新UI，需要使用handler，可以如下方式实现，也可以自己写handler
        if (clazz == String.class) return (T)(areaname + "," + weathericon + "," + temperature + "," + weathername + "," + wind_direction + "," + wind_scale);
        return null;
    }
}
