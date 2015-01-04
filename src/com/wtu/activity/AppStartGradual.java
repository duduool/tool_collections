package com.wtu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AppStartGradual extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		final View view = View.inflate(this, R.layout.app_splash, null);
		setContentView(view);

		// 渐变展示启动屏,这里通过动画来设置了开启应用程序的界面
		AlphaAnimation aa = new AlphaAnimation(1.5f, 1.5f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		//给动画添加监听方法
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});
	}

	/**
	 * 跳转到主角面的方法
	 */
	private void redirectTo() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}