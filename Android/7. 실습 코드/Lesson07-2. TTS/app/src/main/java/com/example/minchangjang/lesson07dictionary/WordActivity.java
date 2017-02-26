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

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Intent intent = getIntent();
        String englishWord = intent.getStringExtra("english");

        MockData mockData = new MockData();
        final MockData.Words word = mockData.getWord(englishWord);

        TextView english = (TextView) findViewById(R.id.tv_english);
        TextView korean = (TextView) findViewById(R.id.tv_korean);
        TextView type = (TextView) findViewById(R.id.tv_type);

        english.setText(word.getEnglishWord());
        korean.setText(word.getKoreanWord());
        type.setText(word.getType());

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.KOREAN);
            }
        });
        tts.setSpeechRate(0.7f); // 스피치 속도 조절 1.0f 가 정상 속도
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(word.getEnglishWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(word.getKoreanWord(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    @Override
    protected void onDestroy() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

}
