package com.tincio.pharmaapp.data.service.response;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tincio.pharmaapp.R;

public class MedicosResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("miercoles_inicio")
    @Expose
    private String miercolesInicio;
    @SerializedName("viernes_fin")
    @Expose
    private String viernesFin;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("es_medico_privado")
    @Expose
    private String esMedicoPrivado;
    @SerializedName("hospital")
    @Expose
    private String hospital;
    @SerializedName("jueves_fin")
    @Expose
    private String juevesFin;
    @SerializedName("martes_fin")
    @Expose
    private String martesFin;
    @SerializedName("martes_inicio")
    @Expose
    private String martesInicio;
    @SerializedName("lunes_fin")
    @Expose
    private String lunesFin;
    @SerializedName("apellidos")
    @Expose
    private String apellidos;

    @SerializedName("numero_visitas_mes")
    @Expose
    private String numeroVisitasMes;
    @SerializedName("miercoles_fin")
    @Expose
    private String miercolesFin;
    @SerializedName("disponibilidad")
    @Expose
    private String disponibilidad;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("cedula_profesional")
    @Expose
    private String cedulaProfesional;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("lunes_inicio")
    @Expose
    private String lunesInicio;
    @SerializedName("especialidad")
    @Expose
    private String especialidad;
    @SerializedName("importancia_medico")
    @Expose
    private String importanciaMedico;
    @SerializedName("jueves_inicio")
    @Expose
    private String juevesInicio;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("tipo_medico")
    @Expose
    private String tipoMedico;
    @SerializedName("viernes_inicio")
    @Expose
    private String viernesInicio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMiercolesInicio() {
        return miercolesInicio;
    }

    public void setMiercolesInicio(String miercolesInicio) {
        this.miercolesInicio = miercolesInicio;
    }

    public String getViernesFin() {
        return viernesFin;
    }

    public void setViernesFin(String viernesFin) {
        this.viernesFin = viernesFin;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getEsMedicoPrivado() {
        return esMedicoPrivado;
    }

    public void setEsMedicoPrivado(String esMedicoPrivado) {
        this.esMedicoPrivado = esMedicoPrivado;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getJuevesFin() {
        return juevesFin;
    }

    public void setJuevesFin(String juevesFin) {
        this.juevesFin = juevesFin;
    }

    public String getMartesFin() {
        return martesFin;
    }

    public void setMartesFin(String martesFin) {
        this.martesFin = martesFin;
    }

    public String getMartesInicio() {
        return martesInicio;
    }

    public void setMartesInicio(String martesInicio) {
        this.martesInicio = martesInicio;
    }

    public String getLunesFin() {
        return lunesFin;
    }

    public void setLunesFin(String lunesFin) {
        this.lunesFin = lunesFin;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroVisitasMes() {
        return numeroVisitasMes;
    }

    public void setNumeroVisitasMes(String numeroVisitasMes) {
        this.numeroVisitasMes = numeroVisitasMes;
    }

    public String getMiercolesFin() {
        return miercolesFin;
    }

    public void setMiercolesFin(String miercolesFin) {
        this.miercolesFin = miercolesFin;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLunesInicio() {
        return lunesInicio;
    }

    public void setLunesInicio(String lunesInicio) {
        this.lunesInicio = lunesInicio;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getImportanciaMedico() {
        return importanciaMedico;
    }

    public void setImportanciaMedico(String importanciaMedico) {
        this.importanciaMedico = importanciaMedico;
    }

    public String getJuevesInicio() {
        return juevesInicio;
    }

    public void setJuevesInicio(String juevesInicio) {
        this.juevesInicio = juevesInicio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipoMedico() {
        return tipoMedico;
    }

    public void setTipoMedico(String tipoMedico) {
        this.tipoMedico = tipoMedico;
    }

    public String getViernesInicio() {
        return viernesInicio;
    }

    public void setViernesInicio(String viernesInicio) {
        this.viernesInicio = viernesInicio;
    }

    public String getHoraInicio(String day, Context context){
        if(day.equals(context.getString(R.string.chk_lunes)))
            return getLunesInicio();
        if(day.equals(context.getString(R.string.chk_martes)))
            return getMartesInicio();
        if(day.equals(context.getString(R.string.chk_miercoles)))
            return getMiercolesInicio();
        if(day.equals(context.getString(R.string.chk_jueves)))
            return getJuevesInicio();
        if(day.equals(context.getString(R.string.chk_viernes)))
            return getViernesInicio();
        return "";
    }

    public String getHoraFin(String day, Context context){
        if(day.equals(context.getString(R.string.chk_lunes)))
            return getLunesFin();
        if(day.equals(context.getString(R.string.chk_martes)))
            return getMartesFin();
        if(day.equals(context.getString(R.string.chk_miercoles)))
            return getMiercolesFin();
        if(day.equals(context.getString(R.string.chk_jueves)))
            return getJuevesFin();
        if(day.equals(context.getString(R.string.chk_viernes)))
            return getViernesFin();
        return "";
    }

}
