package com.tincio.pharmaapp.presentation.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tincio.pharmaapp.presentation.util.widget.SpinnerModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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


}
