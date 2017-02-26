package com.example.minchangjang.lesson15musicplayer;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by minchangjang on 2017. 2. 23..
 */

public class FindAllMP3 {

    public static List<File> mp3Path;

    public static void findAllMP3() {
        mp3Path = new ArrayList<File>();

        String sdPath = "";
        String ext = Environment.getExternalStorageState();

        /**
         * 외장 메모리가 있을 경우 외장 메모리의 주소를
         * 없을 경우 내장 메모리의 주소를 가져옴
         */
        if(ext.equals(Environment.MEDIA_MOUNTED)) {
            sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            Log.d("MP3", "findAllMP3: 1 " + sdPath);
        }
        else {
            sdPath = Environment.MEDIA_UNMOUNTED;
            Log.d("MP3", "findAllMP3: 2" + sdPath);
        }

        findMP3("/storage");

    }

    private static void findMP3(String path) {

        File f = new File(path);
        try {
            List<String> files = Arrays.asList(f.list());
            for (String file : files) {
                File eachFile = new File(f.getAbsolutePath() + File.separator + file);

                if (eachFile.isDirectory()) {
                    Log.d("FILE", "findMP3: " + eachFile.getAbsolutePath());
                    findMP3(eachFile.getAbsolutePath());
                } else {
                    if (file.toLowerCase().endsWith(".mp3")) {

                        mp3Path.add(eachFile);
                    }
                }

            }
        }
        catch (Exception e) {}

    }
}
