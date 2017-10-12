package com.tincio.pharmaapp.presentation.util.widget;

/**
 * Created by S31745 on 03/08/2017.
 */

public class SpinnerModel {
    private int codigo;
    private String nombre;
    private Boolean isSelected;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public SpinnerModel(int codigo,String nombre){
        this.codigo=codigo;
        this.nombre=nombre;
    }

    public SpinnerModel(int codigo,String nombre, Boolean isSelected){
        this.codigo=codigo;
        this.nombre=nombre;
        this.isSelected = isSelected;
    }
}