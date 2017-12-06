package com.tincio.pharmaapp.data.service;

public class ApiConstants {

    public static final int API_TIMEOUT = 90;
    //https://api.backendless.com/5BA9DF87-1078-DDE2-FF1A-10D9DCAE0600/5EF27282-9F11-333E-FFD6-AE5B390CCC00/data/Usuario?where=email%3D'usuario%40gmail.com'

    public static final String GET_USUARIO = "Usuario";
    public static final String GET_MEDICOS_LUNES = "medicos?where=lunes_inicio!%3D''";
    public static final String GET_MEDICOS_MARTES = "medicos?where=martes_inicio!%3D''";
    public static final String GET_MEDICOS_MIERCOLES = "medicos?where=miercoles_incio!%3D''";
    public static final String GET_MEDICOS_JUEVES = "medicos?where=jueves_inicio!%3D''";
    public static final String GET_MEDICOS_VIERNES = "medicos?where=viernes_incio!%3D''";
}
