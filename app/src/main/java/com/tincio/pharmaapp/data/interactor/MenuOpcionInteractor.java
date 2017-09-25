package com.tincio.pharmaapp.data.interactor;


import com.tincio.pharmaapp.data.callback.MenuOpcionCallback;
import com.tincio.pharmaapp.data.model.OpcionMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 25/05/2016.
 */
public class MenuOpcionInteractor {

    MenuOpcionCallback callback;

    public MenuOpcionInteractor(MenuOpcionCallback callback){
        this.callback = callback;
    }
    public void getListOpcionMenu(){
        try{
            List<OpcionMenu> list = new ArrayList<>();
            list.add(new OpcionMenu(1,"Vehículos","auto"));
            list.add(new OpcionMenu(2,"Reservacion","ic_import_contacts_white_24dp"));
            list.add(new OpcionMenu(3,"Servicios","auto"));
            list.add(new OpcionMenu(4,"Preguntas Frecuentes","ic_forum_white_24dp"));
            list.add(new OpcionMenu(5,"Contacto","ic_contact_phone_white_24dp"));
            list.add(new OpcionMenu(6,"Ubicanos","ic_map_white_24dp"));
            callback.onResponse(list);
        }catch(Exception e){
            throw e;
        }
    }
}
