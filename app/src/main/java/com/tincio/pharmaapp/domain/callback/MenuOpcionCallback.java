package com.tincio.pharmaapp.domain.callback;

import com.tincio.pharmaapp.data.model.OpcionMenu;

import java.util.List;

/**
 * Created by juan on 25/05/2016.
 */
public interface MenuOpcionCallback {
    public void onResponse(List<OpcionMenu> listMenu);
}
