package com.tincio.pharmaapp.data.model;

/**
 * Created by juan on 25/05/2016.
 */
public class OpcionMenu {

    int id;
    String imagen;
    String nombre;

    OpcionMenu(){}
    public OpcionMenu(int id, String nombre, String imagen){
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
