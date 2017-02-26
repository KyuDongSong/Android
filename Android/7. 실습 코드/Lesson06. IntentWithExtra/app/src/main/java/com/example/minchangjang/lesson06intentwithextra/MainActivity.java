package com.example.minchangjang.lesson06intentwithextra;

import android.content.Intent;
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

        final EditText firstNumber = (EditText) findViewById(R.id.et_first_number);
        final EditText secondNumber = (EditText) findViewById(R.id.et_second_number);

        Button resultBtn = (Button) findViewById(R.id.btn_result);
        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberOne = Integer.parseInt(firstNumber.getText().toString());
                int numberTwo = Integer.parseInt(secondNumber.getText().toString());

                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                intent.putExtra("firstNumber", numberOne);
                intent.putExtra("secondNumber", numberTwo);
                startActivity(intent);
            }
        });

    }
}
