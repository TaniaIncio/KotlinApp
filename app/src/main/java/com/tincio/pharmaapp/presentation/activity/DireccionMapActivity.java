package com.tincio.pharmaapp.presentation.activity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tincio.pharmaapp.R;



public class DireccionMapActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private static final int REQUEST_CHECK_SETTINGS = 200;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private static final int START_LOCATION_UPDATE_REQUEST = 101;
    LatLng puntoSelected;
    Place placeSelected;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button btnSelectorDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direccion_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_direccion);
        mapFragment.getMapAsync(this);
        preferences = getSharedPreferences(getString(R.string.preferences_file),MODE_PRIVATE);
        btnSelectorDireccion = (Button) findViewById(R.id.btn_selected_location);
        setEvents();
    }

    private void setEvents(){
        btnSelectorDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupLocationClient();
                onBackPressed();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initSearchPlaces();
        // Add a marker in Sydney and move the camera
        puntoSelected = new LatLng(-12.093844, -77.0362817);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(puntoSelected));
        mMap.setMinZoomPreference(14);
    }

    /*****SEARCH PLACES***/
    private void initSearchPlaces(){
        ///dentro de activity
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
             //   SEARCH = true;
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                placeSelected = place;
                puntoSelected = place.getLatLng();
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title("Direccion cliente"));
            }//

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //  Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            addAndCheckRequest();

        }
    }

    private void addAndCheckRequest() {
        createLocationRequest();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates locationSettingsStates = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        startLocationUpdates();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // La configuracion no ha sido satisfactoria pero puede ser arreglada mostrando el siguiente dialogo
                        try {

                            status.startResolutionForResult(DireccionMapActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignorar el error
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // La configuracion no ha sido satisfactoria y no hay nada que hacer por lo que el dialogo no se mostrara

                        break;
                }
            }
        });
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void getGoogleAPIClient(){
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, START_LOCATION_UPDATE_REQUEST);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        preferences.getString(getString(R.string.preferences_latitud_cliente),"");
    }

    private void setupLocationClient(){
        editor = preferences.edit();
        editor.putString(getString(R.string.preferences_latitud_cliente),String.valueOf(puntoSelected.latitude));
        editor.putString(getString(R.string.preferences_longitud_cliente),String.valueOf(puntoSelected.longitude));
        editor.putString(getString(R.string.preferences_direccion_cliente),String.valueOf(placeSelected.getAddress()));
        editor.apply();
    }

}
