package com.gcfwpt.designonline.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtils {
	public JsonUtils() {
	}

	/**
	 * Gson - 根据json字符串和class返回List<T>
	 * 
	 * @param json
	 * @return
	 */
	public static <T> List<T> toListByGson(String json, Class<T> classOfT) {

		// Gson g = new Gson();
		Gson g = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		List<JsonObject> jsonObjs = g.fromJson(json,new TypeToken<List<JsonObject>>(){}.getType());
		ArrayList<T> listOfT = new ArrayList<T>();
		for (JsonObject jsonObj : jsonObjs) {
			listOfT.add(new Gson().fromJson(jsonObj, classOfT));
		}
		return listOfT;
	}

	/**
	 * Gson - 根据json字符串和class返回Object
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> Object toObjectByGson(String json, Class<T> classOfT) {
		Gson g = new Gson();
		return g.fromJson(json, classOfT);
	}

	/**
	 * FastJson - 根据json字符串和class返回List<T>
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> List<T> toListByFastJson(String json, Class<T> classOfT) {
		return JSON.parseArray(json, classOfT);
	}

	/**
	 * FastJson - 根据json字符串和class返回Object
	 * 
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> Object toObjectByFastJson(String json, Class<T> classOfT) {
		return JSON.parseObject(json, classOfT);
	}

	/**
	 * Gson - 根据list生成json字符串
	 * 
	 * @param list
	 * @return
	 */
	public static <T> String fromListByGson(List<T> list) {
		// Gson g = new Gson();
		// Gson g = new
		// GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		Gson g = new GsonBuilder().disableHtmlEscaping().create();
		String json = g.toJson(list);
		return json;
	}

	public static <T> String fromObjectByGson(Object o) {
		// Gson g = new Gson();
		// 使用GsonBuilder创建Gson对象，disableHtmlEscaping可以避免将'转换成\u0027这样的问题
		Gson g = new GsonBuilder().disableHtmlEscaping().create();
		String json = g.toJson(o);
		return json;
	}

	public static <T> String fromListByFastJson(List<T> list) {
		// 使用 SerializerFeature.DisableCircularReferenceDetect
		// 禁止循环使用，如果不添加，会出现"$ref"这种东西
		String json = JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		// String json = JSON.toJSONString(list);
		return json;
	}

	public static <T> String fromObjectByFastJson(Object o) {
		String json = JSON.toJSONString(o,SerializerFeature.DisableCircularReferenceDetect);
		return json;
	}


	/*
	 * 直接使用此方法将json型file文件转为List<Map<String, String>>形式
	 */
	public static List<Map<String, String>> setDataByJson(Context context,String filePath, String fileName) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		try {
			String json = FileUtil.jsonFileToString(context, filePath, fileName);
			JSONArray array = new JSONArray(json);
			int len = array.length();
			Map<String, String> map;
			for (int i = 0; i < len; i++) {
				JSONObject object = array.getJSONObject(i);
				map = new HashMap<String, String>();
				Iterator it = object.keys();
				while (it.hasNext()) {
					String name = (String) it.next();
					map.put(name, object.getString(name));
				}
				data.add(map);
			}
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return data;
	}

	/*
	 * 直接根据服务器端json转为List<Map<String, String>>形式
	 */
	public static List<Map<String, String>> setDataByJson(Context context,String json) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		try {
			JSONArray array = new JSONArray(json);
			int len = array.length();
			Map<String, String> map;
			for (int i = 0; i < len; i++) {
				JSONObject object = array.getJSONObject(i);
				map = new HashMap<String, String>();
				Iterator it = object.keys();
				while (it.hasNext()) {
					String name = (String) it.next();
					map.put(name, object.getString(name));
				}
				data.add(map);
			}
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		return data;
	}
}
