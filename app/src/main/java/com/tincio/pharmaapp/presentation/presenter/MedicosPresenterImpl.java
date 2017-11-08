package com.tincio.pharmaapp.presentation.presenter;


import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.service.request.MedicosRequest;
import com.tincio.pharmaapp.data.service.response.MedicosResponse;
import com.tincio.pharmaapp.domain.callback.MedicosCallback;
import com.tincio.pharmaapp.domain.interactor.MedicosInteractor;
import com.tincio.pharmaapp.presentation.view.MedicosView;

import java.util.List;

public class MedicosPresenterImpl implements MedicosPresenter, MedicosCallback {

    private MedicosView view;
    MedicosInteractor interactor;

    public MedicosPresenterImpl(MedicosView view) {
        this.view = view;
        interactor = new MedicosInteractor(this, view.getContext());
    }


    @Override
    public void onResponse(Boolean status, String mensaje, List<MedicosResponse> list) {
        view.hideLoader();
        if(status)
            view.drawMedicosinMap(list);
        else
            view.showError(mensaje);
    }

    @Override
    public void getMedicosDia(MedicosRequest request) {
        view.showLoader(view.getContext().getString(R.string.loader_searching_medicos));
        interactor.getMedicos(request);
    }
}
