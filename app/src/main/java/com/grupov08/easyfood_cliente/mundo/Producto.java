package com.grupov08.easyfood_cliente.mundo;

import android.media.Image;

/**
 * Created by concol on 18/09/2016.
 */
public class Producto {

    private String descripcion;
    private String nombre;
    private int precio;
    private Image imagen;

    public Producto(String descripcion, String nombre, int precio, Image imagen) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString()
    {
        return nombre +"\n Valor:"+ precio;
    }
}
