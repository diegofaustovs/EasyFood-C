package com.grupov08.easyfood_cliente;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.grupov08.easyfood_cliente.mundo.EasyFood;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int MY_PERMISSIONS_REQUEST = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.RECEIVE_SMS},
                    MY_PERMISSIONS_REQUEST);
        }

    }

    public void realizarPedido (View v)
    {
        Intent i = new Intent(this, RealizarPedidoActivity.class);
        startActivity(i);
    }

    public void pedidosAnteriores (View v)
    {
        Intent i = new Intent(this, PedidosActivity.class);
        startActivity(i);
    }

    public void ubicarDomiciliario(View v)
    {
        SmsManager.getDefault().sendTextMessage(EasyFood.getInstancia().getTelefono_domiciliario(),null,"*EF:U:Location",null,null);
    }

    public void iniciarSesion (View v)
    {
        Toast.makeText(MainActivity.this, "Btn Iniciar Sesión", Toast.LENGTH_SHORT).show();
    }

    public void sincronizar(View v)
    {
        String url = ((EditText)findViewById(R.id.url_server)).getText().toString();
        String puerto = ((EditText)findViewById(R.id.puerto_server)).getText().toString();
        int puertoint = Integer.parseInt(puerto);
        EasyFood easyFood = EasyFood.getInstancia();
        try
        {
            easyFood.sincronizar(url,puertoint);
        }
        catch (IOException e)
        {
           Toast.makeText(MainActivity.this, "Servidor fuera de servicio", Toast.LENGTH_SHORT).show();
        }

    }

}
