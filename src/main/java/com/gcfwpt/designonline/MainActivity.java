package com.gcfwpt.designonline;

import android.content.Intent;
import android.os.Bundle;

import com.gcfwpt.designonline.activity.HomeActivity;
import com.gcfwpt.designonline.base.BaseActivity;
import com.gcfwpt.designonline.utils.ExceptionUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Mapplication应用类执行完成后加载此界面，两秒后跳转到主页Homeactivity
 */
public class MainActivity extends BaseActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			//mTintManager.setStatusBarAlpha(0.0f);
			setContentView(R.layout.activity_main);
			Timer timer = new Timer();
			TimerTask task = new MyTimerTask();
			timer.schedule(task, 1600);
		} catch (Exception e) {
			ExceptionUtil.handleException(e);
		}
	}

	/**
	 * 执行一个新任务
	 */
	private final class MyTimerTask extends TimerTask {

		@Override
		public void run() {
			intent();
		}
	}

	private void intent() {
		// 初始化数据
		Intent intent_to = new Intent();
		intent_to.setClass(MainActivity.this, HomeActivity.class);
		startActivity(intent_to);
		MApplication.getInstance().killActivity(MainActivity.class);
	}
}