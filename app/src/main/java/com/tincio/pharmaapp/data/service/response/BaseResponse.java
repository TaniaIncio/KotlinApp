package com.tincio.pharmaapp.data.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S31745 on 14/07/2017.
 */

public class BaseResponse {

    @SerializedName("resultado")
    @Expose
    private Integer resultado;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public Integer getResultado() {
        return resultado;
    }

    public void setResultado(Integer resultado) {
        this.resultado = resultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}