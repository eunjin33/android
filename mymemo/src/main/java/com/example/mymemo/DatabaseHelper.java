package com.example.mymemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "hr.db";
    public static int VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    //앱 설치하면 한 번만 호출되는 메소드 onCreate(테이블이 없으면 만들어라)
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate 호출됨");
        String sql = "create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)";
        db.execSQL(sql);
    }

    //기본 버전이랑 비교해서 onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
