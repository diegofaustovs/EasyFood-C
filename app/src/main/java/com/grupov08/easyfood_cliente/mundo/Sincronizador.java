package com.grupov08.easyfood_cliente.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by wrt6000V1 on 19/09/2016.
 */
public class Sincronizador extends Thread
{

    private String url;
    private int puerto;

    public Sincronizador(String url, int puerto)

    {
        this.url = url;
        this.puerto= puerto;
        System.out.println("Thread Creado");
    }

    public void run()
    {
        try
        {
            ArrayList locales = new ArrayList<Local>();
            Socket socketUsuario = new Socket(url, puerto);
            PrintWriter salida = new PrintWriter(socketUsuario.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketUsuario.getInputStream()));

            salida.println("Hola Server");
            String mensajeRecibido = "";
            Local loc = null;
            System.out.println("Esperando al servidor");

            String[] mensajeFragmentado = null;
            mensajeRecibido = entrada.readLine();
            System.out.println(mensajeRecibido);
            String nombre = "";
            String[] coordenadas = new String[2];
            String direccion = "";
            String descripcion = "";

            while (!mensajeRecibido.contains("*_*_*fin de transmision*_*_*")) {
                mensajeRecibido = entrada.readLine();
                mensajeFragmentado = mensajeRecibido.split(":");
                if (mensajeFragmentado[0].trim().equalsIgnoreCase("producto")) {
                    Producto p = new Producto(mensajeFragmentado[3].trim(), mensajeFragmentado[1].trim(), Integer.parseInt(mensajeFragmentado[2].trim()), null);
                    loc.agregarProducto(p);
                    System.out.println("Creó productos");
                } else if (mensajeFragmentado[0].trim().equalsIgnoreCase("nombre"))
                    nombre = mensajeFragmentado[1].trim();
                else if (mensajeFragmentado[0].trim().equalsIgnoreCase("coordenadas")) {
                    ArrayList<Producto> productos = new ArrayList<Producto>();
                    coordenadas[0] = "";
                    coordenadas[1] = mensajeFragmentado[1].trim();
                    loc = new Local(nombre, direccion, productos, coordenadas, descripcion);
                    System.out.println("Creó local");
                } else if (mensajeFragmentado[0].trim().equalsIgnoreCase("descripcion"))
                    descripcion = mensajeFragmentado[1].trim();
                else if (mensajeFragmentado[0].trim().equalsIgnoreCase("direccion"))
                    direccion = mensajeFragmentado[1].trim();
                else if ((mensajeFragmentado[0].trim().equals("*_*_*Nuevo Restaurante*_*_*") || mensajeFragmentado[0].trim().equals("*_*_*fin de transmision*_*_*")) && loc != null) {
                    nombre = "";
                    coordenadas[1] = "";
                    direccion = "";
                    EasyFood ea = EasyFood.getInstancia();
                    ea.agregarLocal(loc);
                    loc = null;
                }

            }
            salida.close();
            entrada.close();
        }
        catch (IOException e)
        {

        }
    }

}
