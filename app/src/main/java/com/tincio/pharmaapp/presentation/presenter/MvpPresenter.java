package com.tincio.pharmaapp.presentation.presenter;

/**
 * Created by juan on 25/05/2016.
 */
public interface MvpPresenter<V> {
    public void setView(V view);
    public void detachView();
}
