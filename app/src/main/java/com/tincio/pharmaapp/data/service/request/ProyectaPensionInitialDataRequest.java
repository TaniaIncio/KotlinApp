package com.tincio.pharmaapp.data.service.request;

/**
 * Created by S31745 on 04/09/2017.
 */

public class ProyectaPensionInitialDataRequest {
    private String codigoCuenta;
    private String token;

    public ProyectaPensionInitialDataRequest(){}
    public ProyectaPensionInitialDataRequest(String codigoCuenta, String token){
        this.codigoCuenta = codigoCuenta;
        this.token = token;
    }
    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
