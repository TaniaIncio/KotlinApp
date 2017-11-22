package com.ahmadrosid.lib.drawroutemap;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by juan on 22/11/2017.
 */

public interface DrawCallback {
    void getAllPoints(List<LatLng> points);
}
