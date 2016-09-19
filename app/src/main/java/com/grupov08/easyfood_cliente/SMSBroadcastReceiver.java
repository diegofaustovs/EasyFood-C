package com.grupov08.easyfood_cliente;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.grupov08.easyfood_cliente.mundo.EasyFood;

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

                if (smsBody.startsWith("*EF:P:"))
                {
                    smsBody = smsBody.replace("*EF:P:","");
                    String smsIn[] = smsBody.split("__");
                    EasyFood.getInstancia().setTelefono_domiciliario(smsIn[0]);
                    EasyFood.getInstancia().setLatitud((smsIn[2].split(" "))[0]);
                    EasyFood.getInstancia().setLongitud((smsIn[2].split(" "))[1]);
                    EasyFood.getInstancia().setUbicacion_descr(smsIn[1]);
                    Toast.makeText(context, EasyFood.getInstancia().getTelefono_domiciliario(), Toast.LENGTH_LONG).show();
                }

                else if (smsBody.startsWith("*EF:U:"))
                {
                    smsBody = smsBody.replace("*EF:U:","");
                    String smsIn[] = smsBody.split("__");
                    smsMessageStr = "El domiciliario se encuentra en " +smsIn[0] + ".\nCon coordenadas: " + smsIn[1];
                    EasyFood.getInstancia().setLatitud((smsIn[1].split(" "))[0]);
                    EasyFood.getInstancia().setLongitud((smsIn[1].split(" "))[1]);
                    EasyFood.getInstancia().setUbicacion_descr(smsIn[0]);
                    Toast.makeText(context, EasyFood.getInstancia().getUbicacion_descr(), Toast.LENGTH_LONG).show();
                }
            }
            Toast.makeText(context, EasyFood.getInstancia().getTelefono_domiciliario(), Toast.LENGTH_LONG).show();

        }
    }
}
