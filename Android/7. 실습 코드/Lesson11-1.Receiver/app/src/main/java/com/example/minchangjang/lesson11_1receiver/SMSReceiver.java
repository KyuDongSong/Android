package com.example.minchangjang.lesson11_1receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ( intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") ) {
            // Log.d("SMS", "메시지가 도착했습니다.");

            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[]) bundle.get("pdus");
            SmsMessage[] smsMessage = new SmsMessage[messages.length];

            Log.d("SMS_MESSAGE", "message length : " + smsMessage.length);

            for ( int i = 0; i < smsMessage.length; i++ ) {

                smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);

                Log.d("SMS_MESSAGE", "onReceive: " + smsMessage[i].getMessageBody());
            }
        }
    }
}
