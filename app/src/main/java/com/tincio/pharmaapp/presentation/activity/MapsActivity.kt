package com.tincio.pharmaapp.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.presentation.adapter.MapAdapterRecycler
import kotlinx.android.synthetic.main.activity_maps.*
import java.lang.reflect.Array
import android.widget.ArrayAdapter
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.toolbar.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var mMap: GoogleMap
    var adapterDoctor: MapAdapterRecycler? = null
    val ZOOM : Float = 14f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setUp()
    }

    private fun setUp(){
        var array = Array(7, {  "";"";"";"";"";"";"";""})
        rec_doctors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rec_doctors.hasFixedSize()
        adapterDoctor = MapAdapterRecycler(array)
        rec_doctors.adapter = adapterDoctor
     //   setUpSpinner()
        setuNavigationDrawer()
    }

    private fun setuNavigationDrawer(){
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupRecycler(){
        var array = Array(7, {  "";"";"";"";"";"";"";""})
        rec_doctors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rec_doctors.hasFixedSize()
        adapterDoctor = MapAdapterRecycler(array)
        rec_doctors.adapter = adapterDoctor
    }

    private fun setUpSpinner(){
        //spinner_map.adapter(Adap)
        val dataAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.array_day))
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //spinner_map.setAdapter(dataAdapter)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-12.0891996,-77.0570098)
        addMarkers()
        setUpMap()
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, ZOOM))
    }

    private fun setUpMap(){
       mMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener{
           override fun onMarkerClick(marker: Marker?): Boolean {
               Log.i("indice marker", marker!!.id)

               return false
           }

       })
    }

    private fun addMarkers(){
        mMap.addMarker(MarkerOptions().position(LatLng(-12.0891996,-77.0570098)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
        mMap.addMarker(MarkerOptions().position(LatLng(-12.0949766,-77.0281831)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
        mMap.addMarker(MarkerOptions().position(LatLng(-12.1215361,-77.0463574)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
        mMap.addMarker(MarkerOptions().position(LatLng(-12.1443466,-77.0297666)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
        mMap.addMarker(MarkerOptions().position(LatLng(-12.0987112,-77.0528037)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
        mMap.addMarker(MarkerOptions().position(LatLng(-12.086794,-77.0614297)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
        mMap.addMarker(MarkerOptions().position(LatLng(-12.0871402,-77.0674807)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker)))
    }
}
