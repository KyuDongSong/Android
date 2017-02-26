package com.example.minchangjang.lesson07dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.minchangjang.lesson07dictionary.dao.MockData;

import java.util.ArrayList;

public class NewWordActivity extends AppCompatActivity {

    private String selectedTypeString;
    private int settedImportance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        final EditText english = (EditText) findViewById(R.id.et_english);
        final EditText korean = (EditText) findViewById(R.id.et_korean);

        final ArrayList<String> types = new ArrayList<>();
        types.add("동사");
        types.add("명사");
        types.add("형용사");

        Spinner type = (Spinner) findViewById(R.id.spn_type);
        type.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, types));
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTypeString = types.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTypeString = types.get(0);
            }
        });

        SeekBar importance = (SeekBar) findViewById(R.id.sb_importance);
        importance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                settedImportance = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        Button done = (Button) findViewById(R.id.btn_done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String englishWord = english.getText().toString();
                String koreanString = korean.getText().toString();

                MockData.Words words = new MockData.Words(englishWord, koreanString, selectedTypeString, settedImportance);
                Log.d("DICT", "English : " + englishWord);
                Log.d("DICT", "Korean : " + koreanString);
                Log.d("DICT", "Word Type : " + selectedTypeString);
                Log.d("DICT", "Importance : " + settedImportance);
            }
        });

    }
}
