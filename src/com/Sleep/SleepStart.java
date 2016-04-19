package com.Sleep;

import java.util.List;

import com.example.sleepmonitor.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class SleepStart extends Activity {

	
	//private double data[] = new double[] { 30, 40, 60, 80, 60, 50, 40 };
	ImageButton btnStartData;
	ImageButton btnSleepData;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleep_manage_layout);
		btnStartData = (ImageButton) findViewById(R.id.btnStartSleepNew);
		btnStartData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SleepStart.this, BTClient.class);
				startActivity(intent);
				
			}
		});
		
		btnSleepData = (ImageButton) findViewById(R.id.btnSleepDataNew);
		btnSleepData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(SleepStart.this, SleepMonitorData.class);
				startActivity(intent);
			}
		});
		
		
		
	}
	
}
