package com.example.minchangjang.lesson15musicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private SeekBar musicDuration;
    private Button play;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Intent intent = getIntent();
        String musicPath = intent.getStringExtra("music");

        mediaPlayer = MediaPlayer.create(this, Uri.parse(musicPath));
        musicDuration = (SeekBar) findViewById(R.id.sb_music_progress);
        musicDuration.setMax(mediaPlayer.getDuration());
        musicDuration.setProgress(0);

        musicDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        play = (Button) findViewById(R.id.btn_play);
        stop = (Button) findViewById(R.id.btn_stop);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            while (mediaPlayer.isPlaying()) {
                                musicDuration.setProgress(mediaPlayer.getCurrentPosition());
                                try {
                                    Thread.sleep(1000L);
                                } catch (InterruptedException e) {
                                }
                            }

                        }
                    }).start();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

    }
}
