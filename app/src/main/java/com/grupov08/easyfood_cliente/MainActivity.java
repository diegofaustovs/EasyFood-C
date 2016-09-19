package com.grupov08.easyfood_cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Toast.makeText(MainActivity.this, "Btn Ubicar Domiciliario", Toast.LENGTH_SHORT).show();
    }

    public void iniciarSesion (View v)
    {
        Toast.makeText(MainActivity.this, "Btn Iniciar Sesión", Toast.LENGTH_SHORT).show();
    }
}
