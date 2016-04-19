package com;

import com.example.sleepmonitor.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 
 * 应用程序启动类：显示欢迎界面并跳转到主页面
 * 
 * @author Administrator
 *
 */

public class WelcomeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.welcome, null);
		setContentView(view);
		

				AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
				aa.setDuration(1000);
				view.startAnimation(aa);
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
	
	
	private void redirectTo() {
		Intent intent  = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

}
