package com.example.sheng.event_checksms.Utile;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sheng.event_checksms.Activity.DBTool;
import com.example.sheng.event_checksms.ApplicationBase.AIMA_mobile_infos;

import java.util.ArrayList;

/**
 * Created by sheng on 2015/8/21.
 */
public class Utils {

    public static ArrayList<AIMA_mobile_infos> getFood() {
        DBTool dbTool = new DBTool();
        SQLiteDatabase database = dbTool.openDatabase();
        ArrayList<AIMA_mobile_infos> aima_mobile_infoses = new ArrayList<AIMA_mobile_infos>();
        Cursor cursor = database.query("sms_history", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            AIMA_mobile_infos aima_mobile_infos = new AIMA_mobile_infos();

            aima_mobile_infos.setPid(cursor.getString(1));
            aima_mobile_infos.setRecnum(cursor.getString(2));
            aima_mobile_infos.setContext(cursor.getString(3));
            aima_mobile_infos.setTimeout(cursor.getString(4));
            aima_mobile_infoses.add(aima_mobile_infos);
        }
        dbTool.closeDatabase();
        return aima_mobile_infoses;
    }


    public static long addSMS(AIMA_mobile_infos aima_mobile_infos) {
        DBTool dbTool = new DBTool();
        SQLiteDatabase database = dbTool.openDatabase();
        ContentValues values = new ContentValues();
        values.put("pid", aima_mobile_infos.getPid());
        values.put("mobile", aima_mobile_infos.getRecnum());
        values.put("context", aima_mobile_infos.getContext());
        values.put("time", System.currentTimeMillis());
        return database.insert("sms_history", null, values);
    }
}
