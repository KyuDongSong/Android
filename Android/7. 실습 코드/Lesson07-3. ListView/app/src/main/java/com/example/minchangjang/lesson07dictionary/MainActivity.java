package com.example.minchangjang.lesson07dictionary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minchangjang.lesson07dictionary.dao.MockData;

import static com.example.minchangjang.lesson07dictionary.dao.MockData.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MockData mockData = new MockData();

        ListView wordList = (ListView) findViewById(R.id.lv_word_list);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.item_word, R.id.tv_word, mockData.getAllWords());
        wordList.setAdapter(adapter);
    }

    public void viewWord(View v) {
        String english = ((TextView) v).getText().toString();

        Intent intent = new Intent(this, WordActivity.class);
        intent.putExtra("english", english);
        startActivity(intent);
    }

}
