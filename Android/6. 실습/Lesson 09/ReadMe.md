<pre>
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:padding="16dp"
    android:layout_height="match_parent"&gt;

    &lt;TextView
        android:textSize="10sp"
        android:text="English"
        android:textColor="#99FF3333"
        android:textStyle="bold"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

    &lt;EditText
        android:id="@+id/et_english"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" /&gt;

    &lt;TextView
        android:textSize="10sp"
        android:text="Korean"
        android:textColor="#99FF3333"
        android:textStyle="bold"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

    &lt;EditText
        android:id="@+id/et_korean"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" /&gt;

    &lt;TextView
        android:textSize="10sp"
        android:text="Type"
        android:textColor="#99FF3333"
        android:textStyle="bold"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

    &lt;Spinner
        android:id="@+id/spn_type"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" /&gt;

    &lt;TextView
        android:textSize="10sp"
        android:text="Importance"
        android:textColor="#99FF3333"
        android:textStyle="bold"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

    &lt;SeekBar
        android:id="@+id/sb_importance"
        android:max="5"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:progress="1" /&gt;

    &lt;Button
        android:id="@+id/btn_done"
        android:text="OK"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" /&gt;

&lt;/LinearLayout&gt;
</pre>
