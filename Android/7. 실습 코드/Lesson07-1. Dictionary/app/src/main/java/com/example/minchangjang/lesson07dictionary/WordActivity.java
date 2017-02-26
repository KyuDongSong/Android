package com.example.minchangjang.lesson07dictionary;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.minchangjang.lesson07dictionary.dao.MockData;

import java.util.Locale;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Intent intent = getIntent();
        String englishWord = intent.getStringExtra("english");

        MockData mockData = new MockData();
        MockData.Words word = mockData.getWord(englishWord);

        TextView english = (TextView) findViewById(R.id.tv_english);
        TextView korean = (TextView) findViewById(R.id.tv_korean);
        TextView type = (TextView) findViewById(R.id.tv_type);

        english.setText(word.getEnglishWord());
        korean.setText(word.getKoreanWord());
        type.setText(word.getType());

    }

}
