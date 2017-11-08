package com.tincio.pharmaapp.presentation.view;

import com.tincio.pharmaapp.data.service.response.MedicosResponse;

import java.util.List;

public interface MedicosView extends MvpView {

    void showError(String message);
    void drawMedicosinMap(List<MedicosResponse> listMedicos);

}