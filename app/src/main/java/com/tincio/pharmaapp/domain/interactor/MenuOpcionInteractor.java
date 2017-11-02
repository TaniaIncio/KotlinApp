package com.tincio.pharmaapp.domain.interactor;


import com.tincio.pharmaapp.domain.callback.MenuOpcionCallback;
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
            //list.add(new OpcionMenu(1,"Home","ic_home"));
            list.add(new OpcionMenu(2,"Clientes","ic_cliente"));
            list.add(new OpcionMenu(3,"Productos","ic_producto"));
            list.add(new OpcionMenu(4,"Configuracion","ic_settings"));
            list.add(new OpcionMenu(5,"Mis Rutas","ic_ruta"));
            list.add(new OpcionMenu(6,"Cerrar Sesion","ic_close"));
            callback.onResponse(list);
        }catch(Exception e){
            throw e;
        }
    }
}
