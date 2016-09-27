package com.gcfwpt.designonline.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gcfwpt.designonline.MApplication;
import com.gcfwpt.designonline.other.SystemBarTintManager;
import com.gcfwpt.designonline.utils.ScreenUtil;
import com.lzy.okhttputils.OkHttpUtils;


/**
 * 所有activity的父类
 *  Activity 安卓的界面窗口 类似PC软件的界面窗口
 * Activity 有生命周期 每个窗口从创建到销毁分别执行不同的方法
 * 创建窗口：Oncrate() -->  Onstart() --> OnResume()
 * 暂停窗口：Onpause() 暂停窗口 比如有电话打来了  界面还显示此窗口 不过被打来的电话打扰了
 * 停止窗口：看不见窗口 但是并没有销毁
 * 销毁窗口：OnDestory()
 */
public abstract class BaseActivity extends AppCompatActivity{

	public static final String TAG = BaseActivity.class.getSimpleName();

	/**
	 * 获取屏幕宽度高度
	 */
	protected int mScreenWidth;
	protected int mScreenHeight;
	protected float mDensity;
	protected int mDensityDpi;

	// 沉浸模式
	protected SystemBarTintManager mTintManager;
	private Context mContext;
	protected ScreenUtil screenUtil;
	private Intent intent;

	/**
	 * onCreate首次加载Activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		intent = new Intent();
		// 设置全局竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 添加所有activity到Stack栈内存中
		MApplication.getInstance().addActivity(this);
		// 获取显示屏分辨率
		screenUtil = new ScreenUtil(this);
		mScreenWidth = screenUtil.getScreenWidthPix();
		mScreenHeight = screenUtil.getScreenHeightPix();
		mDensity = screenUtil.getScreenDensity();
		// 获取像素密度
		mDensityDpi = screenUtil.getScreenDensityDpi();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//根据 Tag 取消请求
		OkHttpUtils.getInstance().cancelTag(this);
	}

	/**
	 * 暂停窗口
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}




	/**
	 * 窗口创建成功
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
	}

	/**
	 * 窗口创建成功后能够获取到交点时候执行此方法
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/**
	 * 停止窗口 如打电话来 但是窗口没有被关闭 手机屏幕还能看到此窗口
	 */
	@Override
	protected void onStop() {
		super.onStop();
	}


	/**跳转界面 **/
	protected void startActivity(Class<?> cls) {
		intent.setClass(this, cls);
		startActivity(intent);
	}

	/**跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/**跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * 按手机返回键执行此方法
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		MApplication.getInstance().killActivity(this.getClass());
	}

}
