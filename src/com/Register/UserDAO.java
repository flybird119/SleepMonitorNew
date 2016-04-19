package com.Register;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {

	private DatabaseHelper dbHelper;
	
	public UserDAO (Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	
	//登录时按输入的username，password查询user数据表的数据进行鉴权（是否是合法用户）
	public boolean login(String username, String password) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from user where username=? and password=?";
		
		Cursor cursor = sdb.rawQuery(sql, new String[] { username, password });
		
		if (cursor.moveToFirst() == true) {
			cursor.close();
			return true;
		}
		return false;
	}
	
	//注册用户输入的信息存入user表
	public boolean register(User user) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "insert into user(username,password,name,sex,age,phone) values(?,?,?,?,?,?)";
		Object obj[] = { user.getUsername(), user.getPassword(),
				user.getName(), user.getSex(), user.getAge(), user.getPhone() };
		sdb.execSQL(sql, obj);
		return true;
	}
	
	//根据用户名搜索并输出
	public String[] readDisplay(String username) {
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select * from user where username=?";
		Cursor cursor = sdb.rawQuery(sql, new String[] {username});
		cursor.moveToFirst();
		String baseInfo[] = {
				cursor.getString(1), cursor.getString(2),cursor.getString(3), 
				cursor.getString(4), cursor.getString(5),cursor.getString(6)
		};
		return baseInfo;
	}
	
	
}
