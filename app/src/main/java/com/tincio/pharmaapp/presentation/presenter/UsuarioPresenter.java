package com.tincio.pharmaapp.presentation.presenter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.tincio.pharmaapp.data.service.request.UsuarioRequest;

public interface UsuarioPresenter {

    void loginUser(UsuarioRequest request);

/*    void checkIfSaldoRentDescargado();

    void obtenerEvolucionValorCuota();*/
}
