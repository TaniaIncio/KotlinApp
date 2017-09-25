package com.tincio.pharmaapp.presentation.activity

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.presentation.adapter.MapAdapterRecycler
import com.tincio.pharmaapp.presentation.fragment.MapaFragment
import kotlinx.android.synthetic.main.activity_navigation_menu.*
import kotlinx.android.synthetic.main.fragment_mapa.*
import kotlinx.android.synthetic.main.toolbar.*


class PrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MapaFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var adapterDoctor: MapAdapterRecycler? = null
    val ZOOM : Float = 14f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_menu)
        setuNavigationDrawer()
        supportFragmentManager.beginTransaction().replace(R.id.frame_base, MapaFragment()).commit()
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

}
