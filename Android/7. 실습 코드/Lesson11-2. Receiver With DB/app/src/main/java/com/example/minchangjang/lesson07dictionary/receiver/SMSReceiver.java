package com.example.minchangjang.lesson07dictionary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.minchangjang.lesson07dictionary.dao.DictionaryDao;
import com.example.minchangjang.lesson07dictionary.dao.Words;

public class SMSReceiver extends BroadcastReceiver {
    private DictionaryDao dictionaryDao;

    public SMSReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            Log.d("SMS_MESSAGE", "dao: " + dictionaryDao);

            dictionaryDao = new DictionaryDao(context);

            // Log.d("SMS", "메시지가 도착했습니다.");  
            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[]) bundle.get("pdus");
            SmsMessage[] smsMessage = new SmsMessage[messages.length];
            Log.d("SMS_MESSAGE", "message length : " + smsMessage.length);

            String[] split = null;
            Words word = null;

            for (int i = 0; i < smsMessage.length; i++) {
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);
                Log.d("SMS_MESSAGE", "onReceive: " + smsMessage[i].getMessageBody());

                split = smsMessage[i].getMessageBody().split("::");
                Log.d("SMS_MESSAGE", "onReceive: " + split[0]);
                Log.d("SMS_MESSAGE", "onReceive: " + split[1]);
                Log.d("SMS_MESSAGE", "onReceive: " + split[2]);
                Log.d("SMS_MESSAGE", "onReceive: " + split[3]);

                word = new Words(split[0], split[1], split[2], Integer.parseInt(split[3]));
                dictionaryDao.addNewWord(word);
            }

        }
    }
}
