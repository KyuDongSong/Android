package com.example.minchangjang.lesson07dictionary;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Words> adapter = null;
    private DictionaryDao dictionaryDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkSMSReceivedPermission();

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

    private void checkSMSReceivedPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*
             * 해당 App이 특정 권한을 가지고 있는지 검사함.
             * 리턴결과는 PackageManager.PERMISSION_DENIED 와 PackageManager.PERMISSION_GRANTED로 나눠짐.
             * PackageManager.PERMISSION_DENIED : 권한이 없음
             * PackageManager.PERMISSION_GRANTED : 권한이 있음.
             */
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

            // 요청한 권한이 없을 경우
            if (permissionCheck == PERMISSION_DENIED) {

                /*
                 * 권한을 취득할 때 사용자로부터 확인을 받아야 하는지 확인
                 * Marshmellow 버젼 이상부터 사용가능함.
                 */
                boolean isPermissionDeny = this.shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS);
                if (isPermissionDeny) {
                    // 사용자가 권한을 Deny 한 경우.
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("권한 요청")
                            .setMessage("이 Application은 사용자의 SMS로 단어장을 추가하는 앱입니다. 계속하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        MainActivity.this.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 10001);
                                    }
                                }
                            })
                            .create().show();
                }
                else {
                    // 처음 권한을 요청 할 경우.
                    this.requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 10001);
                }

            }
        }



    }
}
