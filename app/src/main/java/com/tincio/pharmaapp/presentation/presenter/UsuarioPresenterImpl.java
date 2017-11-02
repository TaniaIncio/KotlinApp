package com.tincio.pharmaapp.presentation.presenter;


import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.service.request.UsuarioRequest;
import com.tincio.pharmaapp.domain.callback.UsuarioCallback;
import com.tincio.pharmaapp.domain.interactor.UsuarioInteractor;
import com.tincio.pharmaapp.presentation.view.LoginView;

public class UsuarioPresenterImpl implements UsuarioPresenter, UsuarioCallback {

    private LoginView view;
    UsuarioInteractor interactor;

    public UsuarioPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new UsuarioInteractor(this, view.getContext());
    }


    @Override
    public void onResponse(Boolean status,  String mensaje) {
        view.hideLoader();
        if(status)
            view.goNextActivity();
        else
            view.showError(mensaje);
    }


    @Override
    public void loginUser(UsuarioRequest request) {
        view.showLoader(view.getContext().getString(R.string.loader_login));
        interactor.getUsuario(request);
    }
}
