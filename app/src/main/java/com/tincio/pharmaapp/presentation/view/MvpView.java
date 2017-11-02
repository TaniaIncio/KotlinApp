package com.tincio.pharmaapp.presentation.view;

import android.content.Context;

/**
 * Created by juan on 25/05/2016.
 */
public interface MvpView {
    Context getContext();
    void showLoader(String message);
    void hideLoader();
}
