package com.tincio.pharmaapp.presentation.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tincio.pharmaapp.presentation.util.widget.SpinnerModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Images {
    public static Drawable getDrawableByName(Context context,String name){
        Drawable drawable = null;
        try{
            int resourceId = context.getResources().getIdentifier(name,"drawable",context.getPackageName());
            drawable =  context.getResources().getDrawable(resourceId);
        }catch(Exception e){
            throw e;
        }
        return drawable;
    }

    public static SpinnerModel[] getWeek(){
        SpinnerModel[] listModels = new SpinnerModel[5];
        listModels[0] = new SpinnerModel(0,"Lunes");
        listModels[1] = new SpinnerModel(0,"Martes");
        listModels[2] = new SpinnerModel(0,"Miercoles");
        listModels[3] = new SpinnerModel(0,"Jueves");
        listModels[4] = new SpinnerModel(0,"Viernes");

        return listModels;
    }


    public static String[] getButtons(){
        return new String[]{"Establecer", "Cancelar"};
    }

    public static ArrayList<LatLng> getListaLatLng(){
        ArrayList<LatLng> lista = new ArrayList<LatLng>();
        lista.add(new LatLng(-12.0891996, -77.0570098));
        lista.add(new LatLng(-12.0949766, -77.0281831));
        lista.add(new LatLng(-12.1215361, -77.0463574));

        lista.add(new LatLng(-12.1443466, -77.0297666));
        lista.add(new LatLng(-12.0987112, -77.0528037));
        lista.add(new LatLng(-12.086794, -77.0614297));
        lista.add(new LatLng(-12.0871402, -77.0674807));

        return lista;
    }

    public static void setMapRuta(GoogleMap map){
        Polyline line = map.addPolyline(new PolylineOptions()
                .add(new LatLng(-12.0891996, -77.0570098), new LatLng(-12.0949766, -77.0281831),
                        new LatLng(-12.1215361, -77.0463574), new LatLng(-12.1443466, -77.0297666),
                        new LatLng(-12.0987112, -77.0528037), new LatLng(-12.086794, -77.0614297),
                        new LatLng(-12.0871402, -77.0674807))
                .width(5)
                .color(Color.BLUE));

    }


}
