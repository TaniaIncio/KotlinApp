package com.tincio.pharmaapp.data.service.request;

public class MedicosRequest {

    private String dia;
    private int offset;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
