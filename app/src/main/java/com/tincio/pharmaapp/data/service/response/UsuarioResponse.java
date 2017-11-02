package com.tincio.pharmaapp.data.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhonatan on 26/01/2017.
 */

public class UsuarioResponse {

    /*@SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("objectId")
    @Expose
    private String objectId;*/
    @SerializedName("nombre")
    @Expose
    private Object nombre;
   /* @SerializedName("updated")
    @Expose
    private Object updated;*/
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("ownerId")
    @Expose
    private Object ownerId;
    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("email")
    @Expose
    private String email;



    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Object ownerId) {
        this.ownerId = ownerId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
