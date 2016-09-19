package com.grupov08.easyfood_cliente;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.grupov08.easyfood_cliente.mundo.EasyFood;
import com.grupov08.easyfood_cliente.mundo.Local;
import com.grupov08.easyfood_cliente.mundo.Producto;

import java.util.ArrayList;

public class RealizarPedidoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,AdapterView.OnItemClickListener {

    private ArrayList<Producto> productos;
    private Spinner sp_local;
    private Spinner sp_producto;
    private ListView lista_pedido;

    private EasyFood easyFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_pedido);

        productos = new ArrayList<>();

        easyFood = EasyFood.getInstancia();

        sp_local = (Spinner) findViewById(R.id.spinner_Local_pedido);
        sp_producto = (Spinner) findViewById(R.id.spinner_producto_pedido);
        lista_pedido = (ListView)findViewById(R.id.lista_pedido);

        sp_local.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, easyFood.getLocales()));

        sp_local.setOnItemSelectedListener(this);
        lista_pedido.setOnItemClickListener(this);
    }

    public void solicitarPedido(View v)
    {
        try
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lista_pedido.getAdapter().getCount(); i++) {
                sb.append(((Producto)lista_pedido.getAdapter().getItem(i)).getNombre());
                sb.append("_");
            }

            SmsManager.getDefault().sendTextMessage("5556",null,"*EF:P:"+ ((Local)sp_local.getSelectedItem()).getNombre() +
                    "**" + sb.toString(),null,null);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void agregarProducto(View v)
    {
        productos.add((Producto)((Spinner) findViewById(R.id.spinner_producto_pedido)).getSelectedItem());
        lista_pedido.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, productos));
        sp_local.setEnabled(false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sp_producto.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, ((Local)parent.getItemAtPosition(position)).getProductos()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Do Nothing
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((ArrayAdapter)parent.getAdapter()).remove(parent.getItemAtPosition(position));
        sp_local.setClickable(false);
    }
}
