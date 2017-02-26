package com.example.minchangjang.lesson06intentwithextra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        Intent intent = getIntent();
        int numberOne = intent.getIntExtra("firstNumber", 0);
        int numberTwo = intent.getIntExtra("secondNumber", 0);

        int resultNumber = numberOne + numberTwo;

        TextView result = (TextView) findViewById(R.id.tv_result);
        result.setText(resultNumber + ""); // TextView는 문자만 입력할 수 있습니다.

    }
}
