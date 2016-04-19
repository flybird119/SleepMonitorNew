package com;

import com.FileManager.FileManagerActivity;
import com.Register.RegisterActivity;
import com.Register.UserDAO;
import com.example.sleepmonitor.R;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity {
	/**
	 *用户登录界面，用户输入的信息与注册填写的信息进行对比下发登陆的权限
	 * 
	 * @author Administrator
	 *
	 */
	
	ImageButton loginButton;
	Button registerButton;
	EditText login_username, login_password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginnew_layout);
		login_username = (EditText) findViewById(R.id.login_username);
		login_password = (EditText) findViewById(R.id.login_password);
		
		loginButton = (ImageButton) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = login_username.getText().toString();
				String password = login_password.getText().toString();
				UserDAO uDao = new UserDAO(LoginActivity.this);
				
				boolean flag = uDao.login(username, password);
				if (flag)
				{
					Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
					String data = username;
					
					Intent intent = new Intent(LoginActivity.this, MainTabHost.class);
					intent.putExtra("user_name", data);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		registerButton = (Button) findViewById(R.id.registerButton);
		registerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			}
		});
	}
	
}
