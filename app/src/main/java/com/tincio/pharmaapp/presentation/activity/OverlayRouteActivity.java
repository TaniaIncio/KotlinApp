package com.tincio.pharmaapp.presentation.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ahmadrosid.lib.drawroutemap.DrawRouteMaps;
import com.amalbit.trail.RouteOverlayView;
import com.amalbit.trail.TrailSupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.model.OpcionMenu;
import com.tincio.pharmaapp.data.service.request.MedicosRequest;
import com.tincio.pharmaapp.data.service.response.MedicosResponse;
import com.tincio.pharmaapp.presentation.adapter.MapAdapterRecycler;
import com.tincio.pharmaapp.presentation.adapter.OpcionMenuAdapter;
import com.tincio.pharmaapp.presentation.presenter.MedicosPresenter;
import com.tincio.pharmaapp.presentation.presenter.MedicosPresenterImpl;
import com.tincio.pharmaapp.presentation.presenter.MenuOpcionPresenter;
import com.tincio.pharmaapp.presentation.util.MapaUtil;
import com.tincio.pharmaapp.presentation.util.maps.CustomClusterRenderer;
import com.tincio.pharmaapp.presentation.util.maps.MyItemCluster;
import com.tincio.pharmaapp.presentation.view.MedicosView;
import com.tincio.pharmaapp.presentation.view.MenuView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OverlayRouteActivity extends AppCompatActivity implements OnMapReadyCallback,
        MedicosView, DrawRouteMaps.OnDrawInteractor,
        NavigationView.OnNavigationItemSelectedListener, MenuView {

    private GoogleMap mMap;

    private MapStyleOptions mapStyle;

    private TrailSupportMapFragment mapFragment;

    private TextView mSpinner;

    private SwitchCompat mSwitchCompat;
    private MedicosPresenter presenter;
    List<MedicosResponse> listMedicos;
    List<LatLng> listPoints;
    List<MedicosResponse> listPointsMedicos;
    private ClusterManager<MyItemCluster> mClusterManager;
    DatePickerDialog datePickerDialog;
    RecyclerView rec_doctors;
    MapAdapterRecycler adapterDoctor = null;
    RecyclerView rcv_opc_menu;
    DrawerLayout drawer_layout;
    String INTENT_NAME_FRAGMENT = null;
    OpcionMenuAdapter adapterMenu;
    MenuOpcionPresenter presenterMenu = null;
    Toolbar toolbar;
    FloatingActionButton ic_menu;
    String daySelected;
    List<MedicosResponse> listMedicosFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_projection_route);*/
        setContentView(R.layout.activity_navigation_menu);

        View view = new FrameLayout(this);
        mSpinner = findViewById(R.id.spinner_location);
        rcv_opc_menu = findViewById(R.id.rcv_opc_menu);
        rec_doctors = findViewById(R.id.rec_doctors);
        drawer_layout = findViewById(R.id.drawer_layout);
        ic_menu = findViewById(R.id.ic_menu);
        daySelected = getString(R.string.chk_jueves);

        toolbar = findViewById(R.id.toolbar);
        mSwitchCompat = findViewById(R.id.switch_btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mapFragment = (TrailSupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
        mapStyle = MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.mapstyle);
        /***/
        listPoints = new ArrayList<>();
        listPointsMedicos = new ArrayList<>();
        callMedicos();
        initControls();
        setEvents();
    }

    /*@Override
    protected int getLayoutId() {
        return R.layout.app_bar_navigation_menu;
    }*/

    private void initControls() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rec_doctors.setLayoutManager(layoutManager);
        rec_doctors.hasFixedSize();

        presenterMenu = new MenuOpcionPresenter();
        presenterMenu.setView(this);
        presenterMenu.getListOpcionMenu();

        setupNavigationDrawer();
       /* adapterDoctor = new MapAdapterRecycler(listMedicos, "Jueves");
        rec_doctors.setAdapter(adapterDoctor);*/
       /* rec_doctors.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            // layoutManager.findFirstCompletelyVisibleItemPosition()
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var indice = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.i("indice visible ", " " + layoutManager.findFirstCompletelyVisibleItemPosition() + " position ")
                if (indice != -1) {
//                    Log.i("ingreso ", " "+layoutManager.findFirstCompletelyVisibleItemPosition()+" position "+markers!![indice].position)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(listMedicos!!.get(indice).latitud.toDouble(), listMedicos!!.get(indice).longitud.toDouble()), ZOOM))
                }
            }
        })*/


        //   setUpSpinner()

    }

    void setEvents() {
        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b)
                    mapStyle = MapStyleOptions.loadRawResourceStyle(getApplicationContext(), R.raw.mapstyle);
                else
                    mapStyle = null;
                mMap.setMapStyle(mapStyle);

            }
        });

        mSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateTimeField();
            }
        });

        ic_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(GravityCompat.START);
            }
        });
        setEventMenu();

      /*  mSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDateTimeField();
            }
        });*/
    }

    private void callMedicos() {
        MedicosRequest request = new MedicosRequest();
        request.setDia(daySelected);// = daySelected
        request.setOffset(40);//= OFFSET
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
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                rec_doctors.setVisibility(View.GONE);
            }
        });

        mClusterManager = new ClusterManager<MyItemCluster>(this, mMap);
        mClusterManager.setRenderer(new CustomClusterRenderer(getContext(), mMap, mClusterManager));
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MyItemCluster>() {
            @Override
            public boolean onClusterItemClick(MyItemCluster myItemCluster) {
                Log.i("itms seleccionados ", myItemCluster.getOrder() + " order item seleccionado");
                ArrayList<MedicosResponse> lista = new ArrayList<>();
                lista.add(myItemCluster.getItem());
                adapterDoctor = new MapAdapterRecycler(lista, "Jueves");
                rec_doctors.setAdapter(adapterDoctor);
                rec_doctors.setVisibility(View.VISIBLE);
                setEventsRecyclerDoctor();
                return false;
            }
        });
        mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItemCluster>() {
            @Override
            public boolean onClusterClick(Cluster<MyItemCluster> cluster) {
                ArrayList<MedicosResponse> lista = new ArrayList<>();
                for (MyItemCluster item : cluster.getItems()) {
                    lista.add(item.getItem());
                }
                adapterDoctor = new MapAdapterRecycler(lista, "Jueves");
                rec_doctors.setAdapter(adapterDoctor);
                rec_doctors.setVisibility(View.VISIBLE);
                //  lista.add(cluster.getItems());
                setEventsRecyclerDoctor();
                Log.i("itms seleccionados ", cluster.getItems().size() + " order item cluster seleccionado");
                return false;
            }
        });

        /*adapterDoctor = new MapAdapterRecycler(listMedicos, "Jueves");
        rec_doctors.setAdapter(adapterDoctor);*/
    }

    void setEventsRecyclerDoctor() {

        adapterDoctor.setOnItemClickListener(new MapAdapterRecycler.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int posicion) {
                startActivity(new Intent(getApplicationContext(), RegistrarClienteActivity.class));
            }
        });
    }

    public void zoomRoute(List<LatLng> lstLatLngRoute) {

        if (mMap == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty()) return;

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (LatLng latLngPoint : lstLatLngRoute)
            boundsBuilder.include(latLngPoint);

        int routePadding = 100;
        LatLngBounds latLngBounds = boundsBuilder.build();

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding));
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
        orderTime();
        // orderMoreImportance();
      //  orderMoreClose();
        checkTime();
        fillPoints();
    }

    void orderMoreClose() {
        if (listMedicos.size() == 0) return;
        LatLng latLngReference = new LatLng(Double.parseDouble(listMedicos.get(0).getLatitud()), Double.parseDouble(listMedicos.get(0).getLongitud()));
        listMedicos.get(0).setDistance(0);
        for (int i = 1; i < listMedicos.size(); i++) {
            listMedicos.get(i).setDistance(MapaUtil.haversine(latLngReference.latitude, latLngReference.longitude,
                    Double.parseDouble(listMedicos.get(i).getLatitud()), Double.parseDouble(listMedicos.get(i).getLongitud())));
        }
        Collections.sort(listMedicos, new Comparator<MedicosResponse>() {
            public int compare(MedicosResponse p1, MedicosResponse p2) {
                return Double.compare(p1.getDistance(), p2.getDistance());
            }
        });
    }

    void orderTime() {
        Collections.sort(this.listMedicos, new Comparator<MedicosResponse>() {
            @Override
            public int compare(MedicosResponse lhs, MedicosResponse rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return getSeconds(lhs.getJuevesInicio()) > getSeconds(rhs.getJuevesInicio()) ? -1 :
                        (getSeconds(lhs.getJuevesInicio()) < getSeconds(rhs.getJuevesInicio())) ? 1 : 0;
            }
        });
    }



    void checkTime() {
        listMedicosFinal = new ArrayList<>();
        if (this.listMedicos.size() == 0) return;
        MedicosResponse itemInicio = this.listMedicos.get(0);
        listMedicosFinal.add(itemInicio);
        for (int i = 1; i < listMedicos.size(); i++) {
            itemInicio= new MedicosResponse();
            /**Si trabajan en el mismo hospital y mismo horario**/
            if (getHour(listMedicos.get(i)).equals(getHour(itemInicio)) && itemInicio.getLatitud() == listMedicos.get(i).getLatitud()) {
                itemInicio = listMedicos.get(i);
//                this.listMedicosFinal.add(itemInicio);
            } else {
                /***Buscar el que este mas cerca**/
                itemInicio = getMedicosMasCercaPoint(i, itemInicio);
                i = i+(itemInicio.getOrder()-i);
               // this.listMedicosFinal.add(itemInicio);
            }
            this.listMedicosFinal.add(itemInicio);
            // itemInicio = listMedicos.get(i);
        }
    }

    MedicosResponse getMedicosMasCercaPoint(int indiceStart, MedicosResponse itemCheck) {
        MedicosResponse itemNext = null;
        List<MedicosResponse> listMedicosTemp = new ArrayList<>();
        for (int i = indiceStart; i < indiceStart + 5; i++) {
            if (i == listMedicos.size()) break;
            listMedicosTemp.add(listMedicos.get(i));
            listMedicosTemp.get(listMedicosTemp.size()-1).setOrder(indiceStart);
        }

        LatLng latLngReference = new LatLng(Double.parseDouble(itemCheck.getLatitud()), Double.parseDouble(itemCheck.getLongitud()));

        for (int i = 1; i < listMedicosTemp.size(); i++) {
            listMedicosTemp.get(i).setDistance(MapaUtil.haversine(latLngReference.latitude, latLngReference.longitude,
                    Double.parseDouble(listMedicosTemp.get(i).getLatitud()), Double.parseDouble(listMedicosTemp.get(i).getLongitud())));
        }
        Collections.sort(listMedicosTemp, new Comparator<MedicosResponse>() {
            public int compare(MedicosResponse p1, MedicosResponse p2) {
                return Double.compare(p1.getDistance(), p2.getDistance());
            }
        });

        return  listMedicosTemp.size()>=2 ? listMedicosTemp.get(1): null;
    }

    String getHour(MedicosResponse item) {
        if (daySelected.equals(getString(R.string.chk_jueves)))
            return item.getJuevesInicio();
        if (daySelected.equals(getString(R.string.chk_miercoles)))
            return item.getMiercolesInicio();
        if (daySelected.equals(getString(R.string.chk_martes)))
            return item.getMartesInicio();
        if (daySelected.equals(getString(R.string.chk_lunes)))
            return item.getLunesInicio();
        if (daySelected.equals(getString(R.string.chk_viernes)))
            return item.getViernesInicio();
        return "";
    }

    int getSeconds(String time) {
        String[] units = time.split(":"); //will break the string up into an array
        int minutes = Integer.parseInt(units[0]); //first element
        int seconds = Integer.parseInt(units[1]); //second element
        return 60 * minutes + seconds;
    }

    void orderMoreImportance() {
        Collections.sort(this.listMedicos, new Comparator<MedicosResponse>() {
            @Override
            public int compare(MedicosResponse lhs, MedicosResponse rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return Integer.valueOf(lhs.getPrioridad()) > Integer.valueOf(rhs.getPrioridad()) ? -1 :
                        (Integer.valueOf(lhs.getPrioridad()) < Integer.valueOf(rhs.getPrioridad())) ? 1 : 0;
            }
        });
    }

    void fillPoints() {
        checkDistanceInKilometer();
        zoomRoute(listPoints);
        drawPointInMap();
        int i = 0;
        for (LatLng item : listPoints) {
            if (i == listPoints.size() - 1) return;
            DrawRouteMaps.getInstance(this, this)
                    .draw(listPoints.get(i), listPoints.get(i + 1), mMap);
            i++;
        }

    }

    void checkDistanceInKilometer() {
        for (MedicosResponse item : listMedicosFinal) {
         //   if (item.getDistance() <= 5) {
                listPoints.add(new LatLng(Double.parseDouble(item.getLatitud()), Double.parseDouble(item.getLongitud())));
                listPointsMedicos.add(item);
           // }
        }
    }

    void drawPointInMap() {
        int indice = 0;
        for (LatLng item : listPoints) {
            MyItemCluster offsetItem = new MyItemCluster(item.latitude, item.longitude, indice, listPointsMedicos.get(indice));
            mClusterManager.addItem(offsetItem);

            /* mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(item.getLatitud()), Double.parseDouble(item.getLongitud())))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker))
                    .snippet(String.valueOf(indice))
                    .title(item.getNombres()+" "+item.getApellidos())
            );*/
            indice++;
        }
    }

    @Override
    public void getAllPoints(List<LatLng> points) {
        mapFragment.setUpPath(points, mMap, getCurrentAnimType());
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                //zoomRoute(listPoints);
                mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
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


    private void setDateTimeField() {
        Calendar dateSelected = Calendar.getInstance();
        Calendar newCalendar = dateSelected;
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.i("dia seleccionado ", "day of month " + dayOfMonth);
                Calendar c = Calendar.getInstance();
                c.set(year, monthOfYear, dayOfMonth);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                daySelected(dayOfWeek);
                mMap.clear();

                listPoints = new ArrayList<>();
                listMedicos = new ArrayList<>();
                listPointsMedicos = new ArrayList<>();
                mClusterManager = null;
                mClusterManager = new ClusterManager<MyItemCluster>(getContext(), mMap);
                //mClusterManager.setAlgorithm(new GridBasedAlgorithm<MyItemCluster>());
                callMedicos();
                //    dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0);
                //  dateEditText.setText(dateFormatter.format(dateSelected.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        // dateEditText.setText(dateFormatter.format(dateSelected.getTime()));
    }

    void daySelected(int dayOfWeek) {
        if (Calendar.MONDAY == dayOfWeek) {
            daySelected = getString(R.string.chk_lunes);
        } else if (Calendar.TUESDAY == dayOfWeek) {
            daySelected = getString(R.string.chk_martes);
            ;
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            daySelected = getString(R.string.chk_miercoles);
            ;
        } else if (Calendar.THURSDAY == dayOfWeek) {
            daySelected = getString(R.string.chk_jueves);
        } else if (Calendar.FRIDAY == dayOfWeek) {
            daySelected = getString(R.string.chk_viernes);
        } else if (Calendar.SATURDAY == dayOfWeek) {
            daySelected = getString(R.string.chk_lunes);
        } else if (Calendar.SUNDAY == dayOfWeek) {
            daySelected = getString(R.string.chk_lunes);
            ;
        }
    }

    /***Para menu*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void showListOpcionMenu(List<OpcionMenu> listOpcionMenu) {
        rcv_opc_menu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcv_opc_menu.hasFixedSize();
        adapterMenu = new OpcionMenuAdapter(listOpcionMenu);
        rcv_opc_menu.setAdapter(adapterMenu);
        setEventMenu();
    }

    void setEventMenu() {
        adapterMenu.setOnItemClickLIstener(new OpcionMenuAdapter.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(OpcionMenu opcionMenu) {
                drawer_layout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                intent.putExtra(INTENT_NAME_FRAGMENT, opcionMenu.getNombre());
                startActivity(intent);
            }
        });


    }

    private void setupNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /***end menu*/
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(uri: Uri)

        void showLoader(String message);

        void hideLoader();

        void showDialog(String message);
    }
}
