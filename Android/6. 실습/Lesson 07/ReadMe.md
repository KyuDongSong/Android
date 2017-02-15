### dao 패키지를 만들고 그 안에 MockData.java 파일을 만듭니다. 아래의 내용을 MockData.java에 복사 붙혀넣기 해주세요.
<pre>
import java.util.HashMap;
import java.util.Map;

public class MockData {

    private Map&lt;String, Words&gt; dict;

    public MockData() {
        dict = new HashMap&lt;String, Words&gt;();
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
</pre>

### activity_main.xml 에 아래 내용을 채워 넣으세요.
<pre>
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="com.example.minchangjang.lesson07dictionary.MainActivity"&gt;

    &lt;ScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"&gt;
        &lt;LinearLayout
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="deserve" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="survive" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="survival" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="create" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="creation" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="describe" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="blame" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="compare" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="examine" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="examination" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="select" /&gt;

            &lt;View
                android:layout_width="match_parent"
                android:layout_height="1dp"
                android:background="#333"/&gt;

            &lt;TextView
                android:padding="10dp"
                android:onClick="viewWord"
                android:textSize="20sp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="selection" /&gt;

        &lt;/LinearLayout&gt;
    &lt;/ScrollView&gt;
&lt;/LinearLayout&gt;
</pre>
