package com.FileManager;

import com.Register.UserDAO;
import com.example.sleepmonitor.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/*
 * 资料管理档案模块，按照登陆的用户读取基本信息
 * 
 */
public class FileManagerActivity extends Activity {

	//基本信息
	private EditText file_username, file_password, file_name, file_age,
			file_phone;
	private RadioGroup file_sex;
	private RadioButton file_male, file_female;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_manager_layout);
		excuteBaseInfo();
	}
	
	private void excuteBaseInfo() {
		
		//初始化界面
		file_username = (EditText) findViewById(R.id.file_username);
		file_password = (EditText) findViewById(R.id.file_password);
		file_name = (EditText) findViewById(R.id.file_name);
		file_sex = (RadioGroup) findViewById(R.id.file_sex);
		file_male = (RadioButton) findViewById(R.id.file_male);
		file_female = (RadioButton) findViewById(R.id.file_female);
		file_age = (EditText) findViewById(R.id.file_age);
		file_phone = (EditText) findViewById(R.id.file_phone);
		
		//按用户名读取一条记录，以字符串数组形式保存在useBaseInfo中
		UserDAO uDao = new UserDAO(FileManagerActivity.this);
		String[] userBaseInfo = uDao.readDisplay(getLoginBundleData());
		
		//显示基本信息
		file_username.setText(userBaseInfo[0]);
		file_password.setText(userBaseInfo[1]);
		file_name.setText(userBaseInfo[2]);
		if (userBaseInfo[3].equals("男")) {
			file_male.setChecked(true);
		} else {
			file_female.setChecked(true);
		}
		file_age.setText(userBaseInfo[4]);
		file_phone.setText(userBaseInfo[5]); 
	}
	
	//获得Login登录用户名
	private String getLoginBundleData() {
		Intent intent = getIntent();
		String login_username = intent.getStringExtra("swapUsername");
		return login_username;
		
	}
}
