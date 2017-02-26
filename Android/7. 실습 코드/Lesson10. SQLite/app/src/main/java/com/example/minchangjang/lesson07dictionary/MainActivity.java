package com.example.minchangjang.lesson07dictionary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minchangjang.lesson07dictionary.dao.DictionaryDao;
import com.example.minchangjang.lesson07dictionary.dao.Words;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Words> adapter = null;
    private DictionaryDao dictionaryDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dictionaryDao = new DictionaryDao(this);

        ListView wordList = (ListView) findViewById(R.id.lv_word_list);

        adapter = new ArrayAdapter<Words>(
                this, R.layout.item_word, dictionaryDao.getAllWords()){

            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if ( convertView == null ) {
                    LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                    convertView = inflater.inflate(R.layout.item_word, parent, false);
                }

                TextView word = (TextView) convertView.findViewById(R.id.tv_word);
                RatingBar importance = (RatingBar) convertView.findViewById(R.id.rb_importance);

                Words words = getItem(position);
                word.setText(words.getEnglishWord());
                importance.setRating(words.getImportance());

                return convertView;
            }

        };

        wordList.setAdapter(adapter);
    }

    public void viewWord(View v) {
        String english = ((TextView) v).getText().toString();

        Intent intent = new Intent(this, WordActivity.class);
        intent.putExtra("english", english);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.menu_add_word ) {
            //Toast.makeText(this, "단어를 추가합니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NewWordActivity.class);
            startActivityForResult(intent, 1000);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == 1000 && resultCode == RESULT_OK ) {
            Words newWord = (Words) data.getSerializableExtra("word");
            Log.d("DICT", "전달된 영어 : " +  newWord.getEnglishWord());
            Log.d("DICT", "전달된 한글 : " +  newWord.getKoreanWord());
            Log.d("DICT", "전달된 타입 : " +  newWord.getType());
            Log.d("DICT", "전달된 중요도 : " +  newWord.getImportance());

            dictionaryDao.addNewWord(newWord); // mockData에 새로 추가된 단어를 넣음
            adapter.add(newWord); // ListView.ArrayAdapter에 새로 추가된 단어를 넣음

        }
    }
}
