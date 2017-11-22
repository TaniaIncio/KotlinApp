package com.ahmadrosid.lib.drawroutemap;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ocittwo on 11/14/16.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class DrawRouteMaps implements DrawCallback {

    private static DrawRouteMaps instance;
    private Context context;
    private static OnDrawInteractor listener;
    public static ArrayList<LatLng> pointsAll = null;

    public static DrawRouteMaps getInstance(Context context, OnDrawInteractor callback) {
        if(instance==null) {
            instance = new DrawRouteMaps();
            pointsAll = new ArrayList<>();
        }
        instance.context = context;
        listener = callback;
        return instance;
    }

    public DrawRouteMaps draw(LatLng origin, LatLng destination, GoogleMap googleMap){
        String url_route = FetchUrl.getUrl(origin, destination);
        DrawRoute drawRoute = new DrawRoute(googleMap, this);
        drawRoute.execute(url_route);
        return instance;
    }

    public static Context getContext() {
        return instance.context;
    }

    @Override
    public void getAllPoints(List<LatLng> points) {
        pointsAll.addAll(points);
        if(listener !=null)
            listener.getAllPoints(pointsAll);
    }

    public interface OnDrawInteractor{
        void getAllPoints(List<LatLng> points);
    }
}
