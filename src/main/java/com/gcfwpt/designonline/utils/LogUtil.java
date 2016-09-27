package com.gcfwpt.designonline.utils;

import com.gcfwpt.designonline.MApplication;

import android.util.Log;

/**
 * 处理日志类  根据是否发布原则
 *
 */
public class LogUtil {
   public static void i(String tag,Object msg){
	   // 未发布
	   if (MApplication.isRelease){
		   return;
	   }
	   Log.i(tag, String.valueOf(msg));
   }
}
