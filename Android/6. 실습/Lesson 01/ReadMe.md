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
