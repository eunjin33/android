package com.example.mydiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME = "memo.db";
    public static int VERSION = 1;

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists diary("
                + " id integer PRIMARY KEY autoincrement, "
                + " title text, "
                + " content text,"
                + " img text,"
                + " time text)";
        db.execSQL(sql);
        sql = "insert into diary (title,content,time) values ('t1', 'content1', '2021-12-02')";
        db.execSQL(sql);
        sql = "insert into diary (title,content,time) values ('t2', 'content2', '2021-12-04')";
        db.execSQL(sql);
        sql = "insert into diary (title,content,time) values ('t3', 'content3', '2021-12-05')";
        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS noteData");
        }
    }
}
