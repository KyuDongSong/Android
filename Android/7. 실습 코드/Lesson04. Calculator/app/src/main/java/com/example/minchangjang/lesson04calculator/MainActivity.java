package com.example.minchangjang.lesson04calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText firstNumber = (EditText) findViewById(R.id.et_first_number);
        final EditText secondNumber = (EditText) findViewById(R.id.et_second_number);
        final TextView result = (TextView) findViewById(R.id.tv_result);
        Button resultButton = (Button) findViewById(R.id.btn_calc_result);

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = Integer.parseInt(firstNumber.getText().toString());
                int number2 = Integer.parseInt(secondNumber.getText().toString());
                int resultNumber = number1 + number2;
                result.setText("Result is... " + resultNumber);
            }
        });


    }
}
