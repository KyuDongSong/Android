### Permission 추가
<pre>
&lt;uses-permission android:name="android.permission.RECEIVE_SMS" /&gt;
&lt;uses-permission android:name="android.permission.SEND_SMS" /&gt;
&lt;uses-permission android:name="android.permission.READ_CONTACTS" /&gt;
</pre>
> Runtime Permission 추가 필요함.

### menu 추가 (menu_context.xml)
<pre>
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;menu xmlns:android="http://schemas.android.com/apk/res/android"&gt;

    &lt;item
        android:id="@+id/menu_delete_word"
        android:title="Delete a word" /&gt;

    &lt;item
        android:id="@+id/menu_pass_to_friend"
        android:title="Send to friend" /&gt;

&lt;/menu&gt;
</pre>

### onCreate 아래에 추가
<pre>
registerForContextMenu(wordList);
      wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView&lt;?&gt; parent, View view, int position, long id) {
              viewWord(view.findViewById(R.id.tv_word));
          }
      });
</pre>

### ContextMenu 추가
<pre>
@Override
public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    if (v.getId()==R.id.lv_word_list) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        String word = ((TextView)info.targetView.findViewById(R.id.tv_word)).getText().toString();
        selectWord = dictionaryDao.getWord(word);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }
}

@Override
public boolean onContextItemSelected(MenuItem item) {

    if ( item.getItemId() == R.id.menu_delete_word ) {

    }
    else if ( item.getItemId() ==  R.id.menu_pass_to_friend ) {
        //단말기에 내장되어 있는 연락처앱을 호출한다.
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        //호출 후, 연락처앱에서 전달되는 결과물을 받기 위해 startActivityForResult로 실행한다.
        startActivityForResult(intent, 1002);
    }

    return super.onContextItemSelected(item);
}
</pre>


### 전화번호 보내기 (onActivityResult)
<pre>
//Toast.makeText(this, String.format("%s, %s", receiveName, receivePhone), Toast.LENGTH_SHORT).show();
String message = String.format("%s::%s::%s:%d",
        selectWord.getEnglishWord(), selectWord.getKoreanWord(), selectWord.getType(), selectWord.getImportance());
SmsManager smsManager = SmsManager.getDefault();
smsManager.sendTextMessage(receivePhone, null, message, null, null);
Toast.makeText(this, "단어를 공유했습니다.", Toast.LENGTH_SHORT).show();
</pre>
