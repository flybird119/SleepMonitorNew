package com.Music;

import java.io.File;
import java.io.IOException;
import java.lang.Math;

import com.example.sleepmonitor.R;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	// /storage/extSdCard/My Music
		public static final int FILE_RESULT_CODE = 1;
		private Button bt_shang;
		private Button bt_xia;
		private Button bt_zan;
		private ListView lv_list;
		final MediaPlayer mediaPlayer = new MediaPlayer();
		private boolean isstop = false; // 暂停 播放
		private int currIndex = 0;
		File[] filelist;
		private int mode; //播放模式 1  顺序播放 2 随机播放
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			Button button = (Button) findViewById(R.id.btnselect);

			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this,
							MyFileManager.class);
					startActivityForResult(intent, FILE_RESULT_CODE);
				}
			});

			lv_list = (ListView) findViewById(R.id.listView1);
			bt_zan = (Button) findViewById(R.id.zan);
			bt_shang = (Button) findViewById(R.id.shang); // 上一首
			bt_xia = (Button) findViewById(R.id.xia); // 下一首

			bt_zan.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					mediaPlayer.pause();// 暂停播放

					if (isstop) {
						bt_zan.setText("开始");
						mediaPlayer.start();//开始播放
						isstop = false;
					} else {
						bt_zan.setText("暂停");
						isstop = true;
					}
				}

			});
			bt_shang.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					pree();
					
				}

			});
			bt_xia.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					next();
				}

			});

		}
		
		/**
		 * 上一首
		 */
		public void pree(){
			int n_index = currIndex;
			n_index--;
			if(filelist == null){
				return;
			}
			if (n_index < 0) {
				n_index = filelist.length - 1;
			}
			currIndex = n_index;
			play(filelist[n_index].getPath());
		}
		
		/**
		 * 下一首
		 * @param path
		 */
		public void next(){
			int n_index = currIndex;
			n_index++;
			if(filelist == null){
				return;
			}

			if (n_index >= filelist.length) {
				n_index = 0;
			}
			n_index = (int) (System.currentTimeMillis() % filelist.length);
			currIndex = n_index;
			play(filelist[n_index].getPath());
		}

		public void setLiveView(String path) {
			filelist = getList(path);
			String[] data = new String[filelist.length];

			for (int i = 0; i <= filelist.length - 1; i++) {
				data[i] = filelist[i].getPath();
			}

			// 绑定ListView和ArrayAdapter
			lv_list.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, data));

			// 添加点击
			lv_list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View v, int arg2,
						long arg3) {
					setTitle(filelist[arg2].getName());
					currIndex = arg2;
					play(filelist[arg2].getPath());
				}

			});

		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			if (FILE_RESULT_CODE == requestCode) {
				Bundle bundle = null;
				if (data != null && (bundle = data.getExtras()) != null) {
					// bundle.getString("file");
					// setTitle(bundle.getString("file"));
					setLiveView(bundle.getString("file"));
				}
			}
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

		public File[] getList(String filepath) {
			File file = new File(filepath);

			File[] filelist = file.listFiles();

			return filelist;

		}

		public void play(String filepath) {
			mediaPlayer.reset();
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.reset();// 重置为初始状态
			}
			try {
				mediaPlayer.setDataSource(filepath);// "/storage/extSdCard/My Music"
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// mediaPlayer.release();
			mediaPlayer.start();// 开始或恢复播放
			bt_zan.setText("开始");
			isstop = false;
			// mediaPlayer.pause();//暂停播放
			// mediaPlayer.start();//恢复播放
			// mediaPlayer.stop();//停止播放
			// mediaPlayer.release();//释放资源
			mediaPlayer
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {// 播出完毕事件
						@Override
						public void onCompletion(MediaPlayer arg0) {
//							mediaPlayer.release();
							if(mode == 1){ // 顺序播放
								next();
							}
							else if(mode == 2){ //随机播放
//							double ran =  Math.random();// filelist.length;
//						ran = ran * filelist.length;
//							int ran = Time.SECOND % filelist.length;
								next();
							}
							else{
								next();
							}
						}
					});
			mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {// 错误处理事件
						@Override
						public boolean onError(MediaPlayer player, int arg1,
								int arg2) {
							mediaPlayer.release();
							return false;
						}
					});
		}
	}
