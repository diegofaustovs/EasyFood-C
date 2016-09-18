package com.grupov08.easyfood_cliente.mundo;

import java.util.ArrayList;

/**
 * Created by concol on 18/09/2016.
 */
public class Local {

    private String nombre;
    private String direccion;
    private ArrayList<Producto> productos;
    private String[] coordenadas;
    private String descripcion;

    public Local(String nombre, String direccion, ArrayList<Producto> productos, String[] coordenadas, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.productos = productos;
        this.coordenadas = coordenadas;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void agregarProducto(Producto p)
    {
        productos.add(p);
    }

    public String toString()
    {
        return nombre + "\n Dirección:" + direccion;
    }
}
