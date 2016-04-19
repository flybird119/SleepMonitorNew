package com.Clock;

import java.util.Calendar;

import com.example.sleepmonitor.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class ClockAlarm extends Activity{
	
	private Button btnSetClock = null;
	private AlarmManager alarmManager = null;
	Calendar calendar = Calendar.getInstance();
	final int DIALOG_TIME = 0;  //设置对话框ID
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock_layout);
		
		alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		btnSetClock = (Button) findViewById(R.id.btnSetClock);
		btnSetClock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_TIME); //显示时间选择对话框
				
			}
		});
	}
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_TIME:
			dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
                public void onTimeSet(TimePicker timePicker, int hourOfDay,int minute) {
                    Calendar c=Calendar.getInstance();//获取日期对象    
                    c.setTimeInMillis(System.currentTimeMillis());        //设置Calendar对象
                    c.set(Calendar.HOUR, hourOfDay);        //设置闹钟小时数
                    c.set(Calendar.MINUTE, minute);            //设置闹钟的分钟数
                    c.set(Calendar.SECOND, 0);                //设置闹钟的秒数
                    c.set(Calendar.MILLISECOND, 0);            //设置闹钟的毫秒数
                    Intent intent = new Intent(ClockAlarm.this, AlarmReceiver.class);    //创建Intent对象
                    PendingIntent pi = PendingIntent.getBroadcast(ClockAlarm.this, 0, intent, 0);    //创建PendingIntent
                    alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);        //设置闹钟
                    //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);        //设置闹钟，当前时间就唤醒
                    Toast.makeText(ClockAlarm.this, "闹钟设置成功", Toast.LENGTH_LONG).show();//提示用户
                }
            }, 
            calendar.get(Calendar.HOUR_OF_DAY), 
            calendar.get(Calendar.MINUTE),
            false);
		break;
		}
		return dialog;
	}
	
}
