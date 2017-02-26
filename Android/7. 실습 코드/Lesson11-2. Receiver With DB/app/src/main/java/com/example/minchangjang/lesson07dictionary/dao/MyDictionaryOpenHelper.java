package com.example.minchangjang.lesson07dictionary.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by minchangjang on 2017. 2. 18..
 */

public class MyDictionaryOpenHelper extends SQLiteOpenHelper {

    public final String DICT = "DICTIONARY";
    public final String DICT_ENGLISH = "ENGLISH";
    public final String DICT_KOREAN = "KOREAN";
    public final String DICT_WORD_TYPE = "WORD_TYPE";
    public final String DICT_IMPORTANCE = "IMPORTANCE";

    private static final String DB_NAME = "DICTIONARY";
    private static final int DB_VERSION = 1;

    public MyDictionaryOpenHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }

    private MyDictionaryOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer tableCreateQuery = new StringBuffer();
        tableCreateQuery.append(" CREATE TABLE DICTIONARY ( ");
        tableCreateQuery.append("     ENGLISH TEXT PRIMARY KEY NOT NULL, ");
        tableCreateQuery.append("     KOREAN TEXT NOT NULL, ");
        tableCreateQuery.append("     WORD_TYPE TEXT NOT NULL, ");
        tableCreateQuery.append("     IMPORTANCE INT NOT NULL ");
        tableCreateQuery.append(" ) ");

        db.execSQL(tableCreateQuery.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuffer tableUpdateQuery = new StringBuffer();
        tableUpdateQuery.append(" DROP TABLE DICTIONARY ");

        db.execSQL(tableUpdateQuery.toString());
        onCreate(db);
    }

}
