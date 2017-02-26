package com.example.minchangjang.lesson15musicplayer;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class MainActivity extends AppCompatActivity {

    private ListView mp3List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkSMSReceivedPermission();
        FindAllMP3.findAllMP3();

        mp3List = (ListView) findViewById(R.id.lv_mp3_list);
        mp3List.setAdapter(new ArrayAdapter<File>(this, R.layout.item_mp3, R.id.tv_mp3, FindAllMP3.mp3Path));
        mp3List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                intent.putExtra( "music", FindAllMP3.mp3Path.get(position).getAbsolutePath() );
                startActivity(intent);
            }
        });

    }

    private void checkSMSReceivedPermission() {

        final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*
             * 해당 App이 특정 권한을 가지고 있는지 검사함.
             * 리턴결과는 PackageManager.PERMISSION_DENIED 와 PackageManager.PERMISSION_GRANTED로 나눠짐.
             * PackageManager.PERMISSION_DENIED : 권한이 없음
             * PackageManager.PERMISSION_GRANTED : 권한이 있음.
             */
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            // 요청한 권한이 없을 경우
            if (permissionCheck == PERMISSION_DENIED) {

                /*
                 * 권한을 취득할 때 사용자로부터 확인을 받아야 하는지 확인
                 * Marshmellow 버젼 이상부터 사용가능함.
                 */
                boolean isPermissionDeny = this.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (isPermissionDeny) {
                    // 사용자가 권한을 Deny 한 경우.
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("권한 요청")
                            .setMessage("이 Application은 SD카드를 사용합니다.")
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
