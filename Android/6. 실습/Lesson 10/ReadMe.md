### MyDictionaryOpenHelper.java
<pre>
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
</pre>

### DictionaryDao
<pre>
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

    public List&lt;Words&gt; getAllWords() {

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

        ArrayList&lt;Words&gt; wordList = new ArrayList&lt;Words&gt;();
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
</pre>
