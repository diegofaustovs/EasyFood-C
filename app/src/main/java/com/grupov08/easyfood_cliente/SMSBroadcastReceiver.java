package com.grupov08.easyfood_cliente;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by concol on 17/09/2016.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get("pdus");
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                if (smsBody.startsWith("*EF:U:"))
                {
                    smsBody = smsBody.replace("*EF:U:","");
                    String smsIn[] = smsBody.split("__");
                    smsMessageStr = "El domiciliario se encuentra en " +smsIn[0] + ".\nCon coordenadas: " + smsIn[1];
                }
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();

        }
    }
}
