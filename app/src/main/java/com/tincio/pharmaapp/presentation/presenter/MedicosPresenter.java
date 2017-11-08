package com.tincio.pharmaapp.presentation.presenter;

import com.tincio.pharmaapp.data.service.request.MedicosRequest;
import com.tincio.pharmaapp.data.service.request.UsuarioRequest;
import com.tincio.pharmaapp.data.service.response.MedicosResponse;

import java.util.List;

public interface MedicosPresenter {

    void getMedicosDia(MedicosRequest request);

/*    void checkIfSaldoRentDescargado();

    void obtenerEvolucionValorCuota();*/
}
