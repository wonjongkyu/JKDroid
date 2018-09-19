package org.skidang.question;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() 메소드 호출됨");

        // 인텐트 안에 들어 있는 SMS 메시지를 파싱합니다.
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null && messages.length > 0){
            // SMS 발신 번호 확인
            String sender = messages[0].getOriginatingAddress();
            Log.i(TAG, "SMS sender : " + sender);

            // SMS 메시지 확인
            String contents = messages[0].getMessageBody().toString();

            Log.i(TAG, "SMS contents : " + contents);
            // SMS 수신 시간 확인
            Date receivedData = new Date(messages[0].getTimestampMillis());

            Log.i(TAG, "SMS received date : " + receivedData.toString());
        }

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i=0; i < smsCount; i++){
            // PDU 포맷으로 되어 있는 메시지를 복원합니다.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // API 23 이상
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            }else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }
}
