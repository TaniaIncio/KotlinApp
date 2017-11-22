package com.tincio.pharmaapp.presentation.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.amalbit.trail.RouteOverlayView;
import com.amalbit.trail.TrailSupportMapFragment;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.Data;
import com.tincio.pharmaapp.data.service.request.MedicosRequest;
import com.tincio.pharmaapp.data.service.response.MedicosResponse;
import com.tincio.pharmaapp.presentation.presenter.MedicosPresenter;
import com.tincio.pharmaapp.presentation.presenter.MedicosPresenterImpl;
import com.tincio.pharmaapp.presentation.util.MapaUtil;
import com.tincio.pharmaapp.presentation.util.maps.CustomClusterRenderer;
import com.tincio.pharmaapp.presentation.util.maps.MapAnimator;
import com.tincio.pharmaapp.presentation.util.maps.MyItemCluster;
import com.tincio.pharmaapp.presentation.view.MedicosView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OverlayRouteActivity extends AppCompatActivity implements OnMapReadyCallback,
    AdapterView.OnItemSelectedListener,MedicosView, DrawRouteMaps.OnDrawInteractor {

    private GoogleMap mMap;

    private MapStyleOptions mapStyle;

    private List<LatLng> route;

    private TrailSupportMapFragment mapFragment;

    private Spinner mSpinner;

    private SwitchCompat mSwitchCompat;
    private MedicosPresenter presenter;
    List<MedicosResponse> listMedicos;
    List<LatLng> listPoints;
    private ClusterManager<MyItemCluster> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projection_route);

        View view = new FrameLayout(this);

        mSpinner = findViewById(R.id.spinner_location);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.array_place, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        mSwitchCompat = findViewById(R.id.switch_btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mapFragment = (TrailSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        route = Data.getRoute();

        mapStyle = MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.mapstyle);
        /***/
        listPoints = new ArrayList<>();
        callMedicos();
    }

    private void callMedicos() {
        MedicosRequest request = new MedicosRequest();
        request.setDia("Jueves");// = daySelected
        request.setOffset(10);//= OFFSET
        presenter = new MedicosPresenterImpl(this);
        presenter.getMedicosDia(request);
    }

    //public void onClick(View view) {
    //    switch (view.getId()) {
    //        case R.id.btn_bengaluru:
    //            route = Data.getRoute();
    //            zoomRoute(route);
    //            mapFragment.setUpPath(route, mMap, getCurrentAnimType());
    //            break;
    //        case R.id.btn_tokyo:
    //            //mapFragment.getOverlayView().stopAnimating();
    //            zoomRoute(route);
    //            mapFragment.setUpPath(route, mMap, getCurrentAnimType());
    //            //route = Data.getTokyoRoute();
    //            break;
    //        case R.id.btn_newyork:
    //            //route = Data.getNewYorkRoute();
    //            mapFragment.getOverlayView().stopAnimating();
    //            break;
    //    }
    //
    //}

    @Override
    public void onMapReady(final GoogleMap map) {
        mMap = map;
        mMap.setMapStyle(mapStyle);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        mMap.setMaxZoomPreference(18);

       /* mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override public void onMapLoaded() {
                zoomRoute(listPoints);
                mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override public void onCameraMove() {
                        mapFragment.onCameraMove(mMap);
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override public void run() {
                        mapFragment.setUpPath(listPoints, mMap, getCurrentAnimType());
                        mSwitchCompat.setChecked(true);
                        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                mapFragment.setUpPath(listPoints, mMap, getCurrentAnimType());
                            }
                        });
                    }
                }, 1000);
            }
        });*/
        mClusterManager = new ClusterManager<MyItemCluster>(this, mMap);
        mClusterManager.setRenderer(new CustomClusterRenderer(getContext(), mMap, mClusterManager));
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
    }

    public void zoomRoute(List<LatLng> lstLatLngRoute) {

        if (mMap == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty()) return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : lstLatLngRoute)
            boundsBuilder.include(latLngPoint);

        int routePadding = 10;
        LatLngBounds latLngBounds = boundsBuilder.build();

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
    }

    @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                route = Data.getRoute();
                break;
            case 1:
                route = Data.getTokyoRoute();
                break;
            case 2:
                route = Data.getNewYorkRoute();
                break;
        }

        /*zoomRoute(route);
        mapFragment.setUpPath(route, mMap, getCurrentAnimType());*/
    }

    @Override public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private RouteOverlayView.AnimType getCurrentAnimType() {
      //  if(mSwitchCompat.isChecked()) {
          //  return RouteOverlayView.AnimType.ARC;
    /*    } else {
            return RouteOverlayView.AnimType.ARC;
        }*/
        return RouteOverlayView.AnimType.PATH;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showLoader(String message) {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void drawMedicosinMap(List<MedicosResponse> listMedicos) {
        this.listMedicos = listMedicos;
        orderMoreImportance();
        orderMoreClose();
        fillPoints();
    }

    void orderMoreClose(){
        if(listMedicos.size()==0)return;
        LatLng latLngReference =  new LatLng(Double.parseDouble(listMedicos.get(0).getLatitud()),Double.parseDouble(listMedicos.get(0).getLongitud()));
        listMedicos.get(0).setDistance(0);
        for (int i = 1; i< listMedicos.size();i++){
             listMedicos.get(i).setDistance(MapaUtil.haversine(latLngReference.latitude, latLngReference.longitude,
                     Double.parseDouble(listMedicos.get(i).getLatitud()),Double.parseDouble(listMedicos.get(i).getLongitud())));
        }
        Collections.sort(listMedicos, new Comparator<MedicosResponse>() {
            public int compare(MedicosResponse p1, MedicosResponse p2) {
                return Double.compare(p1.getDistance(), p2.getDistance());
            }
        });
    }
    void orderMoreImportance(){
        Collections.sort(this.listMedicos, new Comparator<MedicosResponse>() {
            @Override
            public int compare(MedicosResponse lhs, MedicosResponse rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return Integer.valueOf(lhs.getImportanciaMedico()) > Integer.valueOf(rhs.getImportanciaMedico()) ? -1 :
                        (Integer.valueOf(lhs.getImportanciaMedico()) < Integer.valueOf(rhs.getImportanciaMedico())) ? 1 : 0;
            }
        });
    }

    void fillPoints(){
        checkDistanceInKilometer();
        zoomRoute(listPoints);
        drawPointInMap();
        int i = 0;
        for (LatLng item: listPoints){
            DrawRouteMaps.getInstance(this, this)
                    .draw(listPoints.get(i), listPoints.get(i+1), mMap);
            i ++;
        }




//        MapAnimator.getInstance().animateRoute(mMap, listPoints);
//move        mapFragment.setUpPath(listPoints, mMap, getCurrentAnimType());
    }

    void checkDistanceInKilometer(){
        for (MedicosResponse item: listMedicos) {
            if(item.getDistance() <= 5) {
                listPoints.add(new LatLng(Double.parseDouble(item.getLatitud()), Double.parseDouble(item.getLongitud())));
            }
        }
    }

    void drawPointInMap(){
        int indice = 0;
        for (LatLng item: listPoints) {
                MyItemCluster offsetItem = new MyItemCluster(item.latitude, item.longitude, indice);
                mClusterManager.addItem(offsetItem);

            /* mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(item.getLatitud()), Double.parseDouble(item.getLongitud())))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker))
                    .snippet(String.valueOf(indice))
                    .title(item.getNombres()+" "+item.getApellidos())
            );*/
            indice ++;
        }
    }
    @Override
    public void getAllPoints(List<LatLng> points) {
        mapFragment.setUpPath(points, mMap, getCurrentAnimType());
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override public void onMapLoaded() {
                //zoomRoute(listPoints);
                mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override public void onCameraMove() {
                        mapFragment.onCameraMove(mMap);
                    }
                });

            /*    Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override public void run() {
                        mapFragment.setUpPath(listPoints, mMap, getCurrentAnimType());
                        mSwitchCompat.setChecked(true);
                        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                mapFragment.setUpPath(listPoints, mMap, getCurrentAnimType());
                            }
                        });
                    }
                }, 1000);*/
            }
        });
        //drawPointInMap();
    }

    /*private RouteOverlayView.AnimType getCurrentAnimType(){
        //     return if (mSwitchCompat.isChecked()) {
        return  RouteOverlayView.AnimType.PATH;
        //   } else {
        //return     RouteOverlayView.AnimType.ARC
        //}
    }*/

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(uri: Uri)

        void showLoader(String message);
        void hideLoader();
        void showDialog(String message);
    }
}
