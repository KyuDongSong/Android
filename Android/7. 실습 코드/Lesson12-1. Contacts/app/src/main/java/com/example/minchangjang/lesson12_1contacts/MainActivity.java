package com.example.minchangjang.lesson12_1contacts;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkSMSReceivedPermission();

        Button choiceContact = (Button) findViewById(R.id.btn_choice_contact);
        choiceContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //단말기에 내장되어 있는 연락처앱을 호출한다.
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                //호출 후, 연락처앱에서 전달되는 결과물을 받기 위해 startActivityForResult로 실행한다.
                startActivityForResult(intent, 10002);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10002) {
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

            Toast.makeText(this, String.format("%s, %s", receiveName, receivePhone), Toast.LENGTH_SHORT).show();
        }

    }

    private void checkSMSReceivedPermission() {

        final String permission = Manifest.permission.READ_CONTACTS;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*
             * 해당 App이 특정 권한을 가지고 있는지 검사함.
             * 리턴결과는 PackageManager.PERMISSION_DENIED 와 PackageManager.PERMISSION_GRANTED로 나눠짐.
             * PackageManager.PERMISSION_DENIED : 권한이 없음
             * PackageManager.PERMISSION_GRANTED : 권한이 있음.
             */
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);

            // 요청한 권한이 없을 경우
            if (permissionCheck == PERMISSION_DENIED) {

                /*
                 * 권한을 취득할 때 사용자로부터 확인을 받아야 하는지 확인
                 * Marshmellow 버젼 이상부터 사용가능함.
                 */
                boolean isPermissionDeny = this.shouldShowRequestPermissionRationale(permission);
                if (isPermissionDeny) {
                    // 사용자가 권한을 Deny 한 경우.
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("권한 요청")
                            .setMessage("이 Application은 사용자의 SMS로 단어장을 추가하는 앱입니다. 계속하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        MainActivity.this.requestPermissions(new String[]{permission}, 10001);
                                    }
                                }
                            })
                            .create().show();
                }
                else {
                    // 처음 권한을 요청 할 경우.
                    this.requestPermissions(new String[]{permission}, 10001);
                }

            }
        }

    }
}
