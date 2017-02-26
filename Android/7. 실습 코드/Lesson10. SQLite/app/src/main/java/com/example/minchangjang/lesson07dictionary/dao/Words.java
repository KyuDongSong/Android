package com.example.minchangjang.lesson07dictionary.dao;

import java.io.Serializable;

/**
 * Created by minchangjang on 2017. 2. 18..
 */

public class Words implements Serializable {

    private String englishWord;
    private String koreanWord;
    private String type;
    private int importance;

    public Words(String englishWord, String koreanWord, String type, int importance) {
        this.englishWord = englishWord;
        this.koreanWord = koreanWord;
        this.type = type;
        this.importance = importance;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getKoreanWord() {
        return koreanWord;
    }

    public void setKoreanWord(String koreanWord) {
        this.koreanWord = koreanWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}