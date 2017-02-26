package com.example.minchangjang.lesson07dictionary;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private Words selectWord = null;

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

        //TODO 추가
        registerForContextMenu(wordList);
        wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewWord(view.findViewById(R.id.tv_word));
            }
        });

    }

    public void viewWord(View v) {
        String english = ((TextView) v).getText().toString();

        Intent intent = new Intent(this, WordActivity.class);
        intent.putExtra("english", english);
        startActivity(intent);
    }

    //TODO 추가
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.lv_word_list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

            String word = ((TextView)info.targetView.findViewById(R.id.tv_word)).getText().toString();
            selectWord = dictionaryDao.getWord(word);
            getMenuInflater().inflate(R.menu.menu_context, menu);
        }
    }

    //TODO 추가
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.menu_delete_word ) {

        }
        else if ( item.getItemId() ==  R.id.menu_pass_to_friend ) {
            //단말기에 내장되어 있는 연락처앱을 호출한다.
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            //호출 후, 연락처앱에서 전달되는 결과물을 받기 위해 startActivityForResult로 실행한다.
            startActivityForResult(intent, 1002);
        }

        return super.onContextItemSelected(item);
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
        //TODO 추가
        else if ( requestCode == 1002 && resultCode == RESULT_OK ) {
            Cursor cursor = getContentResolver().query(data.getData(),
                    new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER },
                    null, null, null);

            cursor.moveToFirst();
            //이름획득
            String receiveName = cursor.getString(0);
            //전화번호 획득
            String receivePhone = cursor.getString(1);
            cursor.close();

            //Toast.makeText(this, String.format("%s, %s", receiveName, receivePhone), Toast.LENGTH_SHORT).show();
            String message = String.format("%s::%s::%s:%d",
                    selectWord.getEnglishWord(), selectWord.getKoreanWord(), selectWord.getType(), selectWord.getImportance());
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(receivePhone, null, message, null, null);
            Toast.makeText(this, "단어를 공유했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO 모두 수정
    private void checkSMSReceivedPermission() {

        final String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS};


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*
             * 해당 App이 특정 권한을 가지고 있는지 검사함.
             * 리턴결과는 PackageManager.PERMISSION_DENIED 와 PackageManager.PERMISSION_GRANTED로 나눠짐.
             * PackageManager.PERMISSION_DENIED : 권한이 없음
             * PackageManager.PERMISSION_GRANTED : 권한이 있음.
             */
            int receiveSmsCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
            int sendSmsCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            int readContactsCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

            // 요청한 권한이 없을 경우
            if (receiveSmsCheck == PERMISSION_DENIED || readContactsCheck == PERMISSION_DENIED || sendSmsCheck == PERMISSION_DENIED) {

                /*
                 * 권한을 취득할 때 사용자로부터 확인을 받아야 하는지 확인
                 * Marshmellow 버젼 이상부터 사용가능함.
                 */
                boolean isReceiveSmsPermissionDeny = this.shouldShowRequestPermissionRationale(Manifest.permission.RECEIVE_SMS);
                boolean isReadContactsPermissionDeny = this.shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS);
                boolean isSendSmsPermissionDeny = this.shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS);

                if (isReceiveSmsPermissionDeny || isReadContactsPermissionDeny || isSendSmsPermissionDeny) {
                    // 사용자가 권한을 Deny 한 경우.
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("권한 요청")
                            .setMessage("이 Application은 주소록과 SMS 사용합니다.")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        MainActivity.this.requestPermissions(permissions, 10001);
                                    }
                                }
                            })
                            .create().show();
                }
                else {
                    // 처음 권한을 요청 할 경우.
                    this.requestPermissions(permissions, 10001);
                }

            }
        }



    }
}
