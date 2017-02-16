### MockData.java 파일의 내용을 아래처럼 바꿔주세요.
<pre>
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockData {

    private Map&lt;String, Words&gt; dict;

    public MockData() {
        dict = new HashMap&lt;String, Words&gt;();
        dict.put("deserve", new Words("deserve", "받을 가치가 있다", "동사", 3));
        dict.put("survive", new Words("survive", "살아남다", "동사", 4));
        dict.put("survival", new Words("survival", "살아남음, 생존", "명사", 4));
        dict.put("create", new Words("create", "창조하다", "동사", 5));
        dict.put("creation", new Words("creation", "창조(물)", "명사", 5));
        dict.put("describe", new Words("describe", "묘사하다", "동사", 2));
        dict.put("blame", new Words("blame", "비난하다, 책망하다", "동사", 2));
        dict.put("compare", new Words("compare", "비교하다, 비유하다, 비교되다", "동사", 4));
        dict.put("examine", new Words("examin", "시험하다, 검사하다, 진찰하다", "동사", 4));
        dict.put("examination", new Words("examination", "시험, 조사, 검사", "명사", 3));
        dict.put("select", new Words("select", "고르다", "동사", 5));
        dict.put("selection", new Words("selection", "선발, 선택", "명사", 5));
    }

    public Words getWord(String englishWord) {
        return dict.get(englishWord);
    }

    public List&lt;Words&gt; getAllWords() {

        String[] keys = (String[]) dict.keySet().toArray();

        List&lt;Words&gt; words = new ArrayList&lt;Words&gt;();
        for ( String key : keys ) {
            words.add(dict.get(key));
        }

        return words;
    }

    public class Words {

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
}
</pre>
