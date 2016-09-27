package com.gcfwpt.designonline;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.baoyz.treasure.Treasure;
import com.gcfwpt.designonline.config.Constants;
import com.gcfwpt.designonline.sharedpreference.MySharedPreferences;
import com.gcfwpt.designonline.utils.DiskUtil;
import com.gcfwpt.designonline.utils.ExceptionUtil;
import com.gcfwpt.designonline.utils.LogUtil;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.Iterator;
import java.util.Stack;

import cn.jpush.android.api.JPushInterface;

/**
 * app应用类，加载apk首先执行此类
 */
public class MApplication extends Application {
	/**
	 * release=true 表示已经发布
	 */
	public static final boolean isRelease = false;
	public static MApplication instance;
	public static Stack<Activity> mActivityStack;
	public static MySharedPreferences sp;
	private static DiskUtil diskUtil;

	// 全局 是否登陆 
	private PersistentCookieStore persistentCookieStore;

	@Override
	public void onCreate() {
		super.onCreate();
		try {
			diskUtil = new DiskUtil(this);
			//CrashReport.initCrashReport(getApplicationContext(), "900012398", false);

			//初始化日志
			Logger
					.init(Constants.APPNAME)
					.setMethodCount(3)
					.hideThreadInfo()
					.setLogLevel(LogLevel.NONE);
			instance = this;
			// 监听网络状态服务
			sp = Treasure.get(this, MySharedPreferences.class);

			// 极光推送
			JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
			JPushInterface.init(this);     		// 初始化 JPush
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
		//必须调用初始化
		OkHttpUtils.init(this);
		//以下都不是必须的，根据需要自行选择
		persistentCookieStore = new PersistentCookieStore();
		OkHttpUtils.getInstance()//
				.debug("OkHttpUtils")                                              //是否打开调试
				.setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的连接超时时间
				.setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)                  //全局的读取超时时间
				.setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)               //全局的写入超时时间
				.setCacheMode(null);
				//.setCookieStore(new MemoryCookieStore())                           //cookie使用内存缓存（app退出后，cookie消失）
//				.setCookieStore(persistentCookieStore);
	}


	/**
	 * isFirstStart
	 * 判断是否第一次启动App
	 */
	public static boolean isFirstStart(Context context) {
		if (sp.getIs_first_start()) {// 第一次
			sp.setIs_first_start(false);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 单一实例
	 */
	public static MApplication getInstance() {
		if (instance == null) {
			instance = new MApplication();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (mActivityStack == null) {
			mActivityStack = new Stack<Activity>();
		}
		mActivityStack.add(activity);
		LogUtil.i("mt","activity:"+mActivityStack.toString());
	}

	/**
	 * 获取栈顶Activity（堆栈中最后一个压入的）
	 */
	public Activity getTopActivity() {
		Activity activity = mActivityStack.lastElement();
		return activity;
	}

	/**
	 * 结束栈顶Activity（堆栈中最后一个压入的）
	 */
	public void killTopActivity() {
		Activity activity = mActivityStack.lastElement();
		killActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void killActivity(Activity activity) {
		if (activity != null) {
			mActivityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束所有指定类名的Activity
	 */
	public void killActivity(Class<?> cls) {
		Iterator<Activity> iterator = mActivityStack.iterator();
		while (iterator.hasNext()){
			Activity activity = iterator.next();
			if (activity.getClass().equals(cls)) {
				iterator.remove();
				killActivity(activity);
			}
		}
//		for (Activity activity : mActivityStack) {
//			if (activity.getClass().equals(cls)) {
//				killActivity(activity);
//			}
//		}
	}

	/**
	 * 结束所有Activity
	 */
	public void killAllActivity() {
		for (int i = 0, size = mActivityStack.size(); i < size; i++) {
			if (null != mActivityStack.get(i)) {
				mActivityStack.get(i).finish();
			}
		}
		mActivityStack.clear();
	}

	/**
	 * 退出应用程序
	 */
	public void AppExit(Context context) {
		try {
			killAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
	}

}
