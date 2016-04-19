package com.Register;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	static String name = "user.db";
	static int dbVersion = 3;
	
	public DatabaseHelper (Context context) {
		super(context, name, null, dbVersion);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table user(id integer primary key autoincrement," +
				"username varchar(20),password varchar(20),name varchar(10)," +
				"sex varchar(5),age integer,phone varchar(13))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
