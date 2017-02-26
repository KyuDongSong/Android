package com.example.minchangjang.lesson03myloginform;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText email = (EditText) findViewById(R.id.et_email);
        final EditText name = (EditText) findViewById(R.id.et_name);
        final EditText password = (EditText) findViewById(R.id.et_password);

        Button regist = (Button) findViewById(R.id.btn_regist);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = String.format("이메일 : %s, 이름 : %s, 비밀번호 : %s",
                        email.getText().toString(), name.getText().toString(), password.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("회원가입 안내");
                builder.setMessage(message);
                builder.setPositiveButton("확인", null);
                builder.create().show();

            }
        });

    }
}
