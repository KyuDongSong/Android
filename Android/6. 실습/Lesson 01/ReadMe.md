### activity_main.xml
<pre>
&lt;?xml version="1.0" encoding="utf-8"?&gt;
LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.minchangjang.myapplication.MainActivity"
    android:orientation="vertical"&gt;

    &lt;ImageView
        android:layout_marginTop="30dp"
        android:src="@drawable/youtube"
        android:layout_width="match_parent"
        android:layout_height="150dp" /&gt;

    &lt;TextView
        android:layout_marginTop="30dp"
        android:layout_gravity="center"
        android:textSize="30sp"
        android:text="Youtube"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/textView" /&gt;

    &lt;Button
        android:layout_marginTop="30dp"
        android:text="Go, Youtube!"
        android:textAllCaps="false"
        android:layout_gravity="center"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

&lt;/LinearLayout&gt;
</pre>

### attribute
Attribute Name|설명|값
---|---|---
android:layout_height|View의 높이를 설정합니다.<br/>(모든 View의 공통 속성)|wrap_content, match_parent
android:layout_width|View의 너비를 설정합니다.<br/>(모든 View의 공통 속성)|wrap_content, match_parent
android:layout_marginTop|View 상단의 여백을 설정합니다.<br/>(모든 View의 공통 속성)|숫자dp
android:layout_marginBottom|View 하단의 여백을 설정합니다.<br/>(모든 View의 공통 속성)|숫자dp
android:layout_marginLeft|View 좌단의 여백을 설정합니다.<br/>(모든 View의 공통 속성)|숫자dp
android:layout_marginRight|View 우단의 여백을 설정합니다.<br/>(모든 View의 공통 속성)|숫자dp
android:layout_gravity|View를 정렬합니다.|center_vertical, center_horizonal<br/>center, left, right, top, bottom
android:background|배경색을 지정합니다.<br/>(모든 View의 공통 속성)|16진수 RGB값
android:text|텍스트를 지정합니다.|값
android:textSize|텍스트의 크기를 지정합니다.|숫자sp
android:textColor|텍스트의 색깔을 지정합니다.|16진수 RGB값
android:textStyle|텍스트의 스타일을 지정합니다.|bold, italic, normal
