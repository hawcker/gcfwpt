package com.gcfwpt.designonline.utils;
import java.util.HashMap;
import java.util.Map;

public class Activity_object {
	/**
	 * 在两个activity之间传递数据无非是用Bundle 和Intent.putExtra的重载方法
	 * 不过Bundle貌似可以传递对象，前提是对象必须是可序列化的，序列化却会降低性能，传递
	 * 的时候要把这个对象序列化，取对象的时候还要进行反序列化
	 * 这个类是个单例模式，要通过Activity_object.getActivity_object()拿到一个Activity_object对象，得到
	 * Activity_object后就可以放入对象了，别忘了用完后调用 remove方法或cleanUpActivity_object方法，好让GC回收
	 * 示例：在一个Activity里使用如下代码放入对象
	 * Activity_object activity_object = Activity_object.getActivity_object();
	 * Date date = new Date();
	 * activity_object.put("date", date);
	 * 在另外一个Activity里拿到对象
	 * Activity_object activity_object = Activity_object.getActivity_object();
	 * Date date = (Date)activity_object.get("date");
	 */
	private Map<Object,Object> map;

	private static Activity_object activity_object;

	//Attention here, DO NOT USE keyword 'new' to create this object.
	//Instead, use getSession method.
	private Activity_object(){
		map = new HashMap<Object, Object>();
	}

	public static Activity_object getSession(){

		if(activity_object == null){
			activity_object = new Activity_object();
			return activity_object;
		}else{
			return activity_object;
		}
	}

	public void put(Object key, Object value){

		map.put(key, value);
	}
	public Object get(Object key){

		return map.get(key);
	}
	public void cleanUpActivity_object(){
		map.clear();
	}
	public void remove(Object key){
		map.remove(key);
	}
}
