package com;

import com.AskHelp.SystemHelp;
import com.Clock.ClockAlarm;
import com.Clock.DeskClockMainActivity;
import com.FileManager.FileManagerActivity;
import com.Music.MainActivity;
import com.Music.MusicPlayer;
import com.Sleep.SleepStart;
import com.Alarm.*;
import com.example.sleepmonitor.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/*
 * 功能：将系统的各个功能模块添加在TabHost选项卡种，实现在个选项卡
 * 之间手动切换显示不同模块的内容
 * 
 */
public class MainTabHost extends TabActivity {
	private TabHost tabHost;
	private RadioGroup radioGroup;
	
	//Tab选项卡的图标数组
	private int[] tabIconViewArray = {
			R.drawable.tab_icon1, R.drawable.tab_icon2, R.drawable.tab_icon3,
		    R.drawable.tab_icon4, R.drawable.tab_icon5
	};
	
	//Tab选项卡的文字数组
	private String[] tabNameTextArray = {
			"资料", "睡眠", "音乐", "闹铃", "帮助"
	};
	
	//Tab选项卡中的内容数组
	private Class[] tabContentClassArray = {
			FileManagerActivity.class,SleepStart.class,
			MainActivity.class,com.Alarm.MainActivity.class,
			SystemHelp.class,
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab_host);
		
		//为每一个选项卡设置按钮，图标，文字和内容，将tab按钮添加进Tab选项卡中
		tabHost = getTabHost();
		for (int i = 0; i < tabContentClassArray.length; i++) {
		    TabSpec tabSpec = tabHost.newTabSpec(tabNameTextArray[i])
			    .setIndicator(tabNameTextArray[i])
			    .setContent(getTabItemIntent(i));
		    tabHost.addTab(tabSpec);
		}
		initData();
	}
	
	//对选项卡上的每个按钮进行监听，以实现模块的切换
	private void initData() {
		radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.RadioButton0:
				    tabHost.setCurrentTabByTag(tabNameTextArray[0]);
				    break;
				case R.id.RadioButton1:
				    tabHost.setCurrentTabByTag(tabNameTextArray[1]);
				    break;
				case R.id.RadioButton2:
				    tabHost.setCurrentTabByTag(tabNameTextArray[2]);
				    break;
				case R.id.RadioButton3:
				    tabHost.setCurrentTabByTag(tabNameTextArray[3]);
				    break;
				case R.id.RadioButton4:
				    tabHost.setCurrentTabByTag(tabNameTextArray[4]);
				    break;
				}	
			}
		});
		((RadioButton)radioGroup.getChildAt(0)).toggle();
		
	}
	
	private Intent getTabItemIntent(int index) {
		Intent intent = new Intent(MainTabHost.this, tabContentClassArray[index]);
		intent.putExtra("swapUsername", getBundleData());
		return intent;
	}
	
	//获取启动该MainTabHostActivity的Intent，传入Intent携带的username数据
	private String getBundleData() {
		Intent intent = getIntent();
		String username = intent.getStringExtra("user_name");
		return username;
	}
}
