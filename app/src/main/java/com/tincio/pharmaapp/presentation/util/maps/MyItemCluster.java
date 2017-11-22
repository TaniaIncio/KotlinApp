package com.tincio.pharmaapp.presentation.util.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by juan on 22/11/2017.
 */

public class MyItemCluster implements ClusterItem {
    private final LatLng mPosition;
    private int order;

    public MyItemCluster(double lat, double lng, int order) {
        mPosition = new LatLng(lat, lng);
        this.order = order;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public int getOrder() {
        return order;
    }

}
