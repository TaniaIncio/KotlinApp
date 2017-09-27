package com.tincio.pharmaapp.presentation.view;


import com.tincio.pharmaapp.data.model.OpcionMenu;

import java.util.List;

/**
 * Created by juan on 25/05/2016.
 */
public interface MenuView extends MvpView{
    void showListOpcionMenu(List<OpcionMenu> listOpcionMenu);
}
