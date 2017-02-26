package com.example.minchangjang.lesson07dictionary.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minchangjang on 2017. 2. 18..
 */

public class DictionaryDao extends MyDictionaryOpenHelper {

    public DictionaryDao(Context context) {
        super(context);
    }

    public void addNewWord(Words newWords) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DICT_ENGLISH, newWords.getEnglishWord());
        values.put(DICT_KOREAN, newWords.getKoreanWord());
        values.put(DICT_WORD_TYPE, newWords.getType());
        values.put(DICT_IMPORTANCE, newWords.getImportance());
        db.insert(DICT, null, values);

    }

    public List<Words> getAllWords() {

        SQLiteDatabase db = getReadableDatabase();
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor cursor = db.query(
                DICT, // table
                null, // columns, null 일 경우는 *
                null, // selection, where 조건을 작성함 (WHERE 키워드는 제외)
                null, // selectionArgs, where arguments. ? 에 해당되는 파라미터를 작성함 배열
                null, // group by
                null, // having
                null // order by
        );
        // SELECT * FROM DICTIONARY;

        ArrayList<Words> wordList = new ArrayList<Words>();
        Words word = null;

        while ( cursor.moveToNext() ) {
            word = new Words(
                    cursor.getString(cursor.getColumnIndex(DICT_ENGLISH)),
                    cursor.getString(cursor.getColumnIndex(DICT_KOREAN)),
                    cursor.getString(cursor.getColumnIndex(DICT_WORD_TYPE)),
                    cursor.getInt(cursor.getColumnIndex(DICT_IMPORTANCE))
            );
            wordList.add(word);
        }

        return wordList;

    }

    public Words getWord(String english) {

        SQLiteDatabase db = getReadableDatabase();
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        Cursor cursor = db.query(
                DICT, // table
                null, // columns, null 일 경우는 *
                DICT_ENGLISH + " = ?", // selection, where 조건을 작성함 (WHERE 키워드는 제외)
                new String[]{english}, // selectionArgs, where arguments. ? 에 해당되는 파라미터를 작성함 배열
                null, // group by
                null, // having
                null // order by
        );
        // SELECT * FROM DICTIONARY WHERE ENGLISH = ?

        Words word = null;

        if ( cursor.moveToNext() ) {
            word = new Words(
                    cursor.getString(cursor.getColumnIndex(DICT_ENGLISH)),
                    cursor.getString(cursor.getColumnIndex(DICT_KOREAN)),
                    cursor.getString(cursor.getColumnIndex(DICT_WORD_TYPE)),
                    cursor.getInt(cursor.getColumnIndex(DICT_IMPORTANCE))
            );
        }

        return word;

    }
}
