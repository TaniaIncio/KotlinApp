package com.tincio.pharmaapp.presentation.view;

import android.content.Context;

public interface LoginView extends MvpView {

    void showError(String message);
    void goNextActivity();

}