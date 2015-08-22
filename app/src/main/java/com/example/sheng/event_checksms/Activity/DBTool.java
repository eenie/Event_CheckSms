package com.example.sheng.event_checksms.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBTool
{
	public static String databasePath;
	private SQLiteDatabase database;




	public SQLiteDatabase openDatabase()
	{

		if (database == null || !database.isOpen())
		{
			database = SQLiteDatabase.openDatabase(databasePath, null,
					SQLiteDatabase.OPEN_READWRITE);

		}

		return database;
	}


	public void closeDatabase()
	{
		if (database != null || database.isOpen())
		{
			database.close();
		}
	}

}
