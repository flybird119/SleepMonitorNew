package com.Clock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class AlarmActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		new AlertDialog.Builder(AlarmActivity.this).
			setTitle("闹钟").              //设置标题
			setMessage("该起床了！").		  //设置内容
			//设置按钮
			setPositiveButton("知道了", new OnClickListener() { 
				@Override
				public void onClick(DialogInterface dialog, int which) {
					AlarmActivity.this.finish();
					
				}
			}).create().show();
	}
}
