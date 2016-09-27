package com.gcfwpt.designonline.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class ScreenUtil {
	/**
	 * 屏幕类
	 * 必须先new 出来ScreenUtil
	 */
	public int mScreenWidth;
	public int mScreenHeight;
	public float mDensity;
	public int mDensityDpi;
	private DisplayMetrics metric;
	
	public ScreenUtil(Context context){
		if (metric == null) {
			metric = new DisplayMetrics();
		}
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		// 获取屏幕密度
		mDensity = metric.density;
		// 获取像素密度
		mDensityDpi = metric.densityDpi;
	}

	/**
	 * 获得屏幕宽度
	 */
	public int getScreenWidthPix() {
		return mScreenWidth;
	}

	/**
	 * 获得屏幕高度
	 */
	public int getScreenHeightPix() {
		return mScreenHeight;
	}
	
	/**
	 * 获得屏幕密度
	 */
	public float getScreenDensity() {
		return mDensity;
	}
	
	/**
	 * 获得像素密度
	 */
	public int getScreenDensityDpi() {
		return mDensityDpi;
	}

	/**
	 * 计算状态栏高度
	 */
	public static int getStatusBarHeight(Context context){
		Class c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
		}
		return statusBarHeight;
	}
}
