package com.example.minchangjang.lesson07dictionary.dao;

import java.util.HashMap;
import java.util.Map;

public class MockData {

    private Map<String, Words> dict;

    public MockData() {
        dict = new HashMap<String, Words>();
        dict.put("deserve", new Words("deserve", "받을 가치가 있다", "동사"));
        dict.put("survive", new Words("survive", "살아남다", "동사"));
        dict.put("survival", new Words("survival", "살아남음, 생존", "명사"));
        dict.put("create", new Words("create", "창조하다", "동사"));
        dict.put("creation", new Words("creation", "창조(물)", "명사"));
        dict.put("describe", new Words("describe", "묘사하다", "동사"));
        dict.put("blame", new Words("blame", "비난하다, 책망하다", "동사"));
        dict.put("compare", new Words("compare", "비교하다, 비유하다, 비교되다", "동사"));
        dict.put("examine", new Words("examine", "시험하다, 검사하다, 진찰하다", "동사"));
        dict.put("examination", new Words("examination", "시험, 조사, 검사", "명사"));
        dict.put("select", new Words("select", "고르다", "동사"));
        dict.put("selection", new Words("selection", "선발, 선택", "명사"));
    }

    public Words getWord(String englishWord) {
        return dict.get(englishWord);
    }

    public class Words {

        private String englishWord;
        private String koreanWord;
        private String type;

        public Words(String englishWord, String koreanWord, String type) {
            this.englishWord = englishWord;
            this.koreanWord = koreanWord;
            this.type = type;
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
    }
}