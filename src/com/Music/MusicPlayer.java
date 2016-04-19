package com.Music;

import java.io.File;

import com.example.sleepmonitor.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MusicPlayer extends Activity implements OnClickListener {

	private Button play;
	private Button stop;
	private Button pause;
	
	private MediaPlayer mediaPlayer = new MediaPlayer();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_laout);
		
		play = (Button) findViewById(R.id.play);
		stop = (Button) findViewById(R.id.stop);
		pause = (Button) findViewById(R.id.pause);
		
		play.setOnClickListener(this);
		stop.setOnClickListener(this);
		pause.setOnClickListener(this);
		
		iniMediaPlayer();
	}
	
	private void iniMediaPlayer() {
		try {
			File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
			mediaPlayer.setDataSource(file.getPath());
			mediaPlayer.prepare(); //mediaplayer进入准备状态
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			if (!mediaPlayer.isPlaying()) { 
				mediaPlayer.start(); // 开始播放
			}
			break;
		case R.id.pause:
			if (mediaPlayer.isPlaying()) { 
				mediaPlayer.pause(); //暂停播放
			}
			break;
		case R.id.stop:
			if (mediaPlayer.isPlaying()) { 
				mediaPlayer.reset(); //停止播放
			}
		default:
			break;
			
		}
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}
	
}
