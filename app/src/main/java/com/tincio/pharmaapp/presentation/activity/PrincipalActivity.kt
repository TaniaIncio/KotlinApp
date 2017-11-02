package com.tincio.pharmaapp.presentation.activity

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.R.id.rec_doctors
import com.tincio.pharmaapp.data.model.OpcionMenu
import com.tincio.pharmaapp.presentation.adapter.MapAdapterRecycler
import com.tincio.pharmaapp.presentation.adapter.OpcionMenuAdapter
import com.tincio.pharmaapp.presentation.fragment.MapaFragment
import com.tincio.pharmaapp.presentation.presenter.MenuOpcionPresenter
import com.tincio.pharmaapp.presentation.view.MenuView
import kotlinx.android.synthetic.main.activity_navigation_menu.*
import kotlinx.android.synthetic.main.fragment_mapa.*
import kotlinx.android.synthetic.main.toolbar.*
import android.content.Intent
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout


class PrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MapaFragment.OnFragmentInteractionListener, MenuView {
    override fun showLoader(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val INTENT_NAME_FRAGMENT: String? = null
    override fun getContext(): Context {
        return applicationContext//To change body of created functions use File | Settings | File Templates.
    }

    override fun showListOpcionMenu(listOpcionMenu: MutableList<OpcionMenu>?) {
        rcv_opc_menu.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcv_opc_menu.hasFixedSize()
        adapterMenu = OpcionMenuAdapter(listOpcionMenu)
        rcv_opc_menu.adapter = adapterMenu
        setEventMenu()
    }

    fun setEventMenu(){
        adapterMenu!!.setOnItemClickLIstener { opcionMenu: OpcionMenu? ->
            drawer_layout.closeDrawer(GravityCompat.START)
            val intent = Intent(context, TestActivity::class.java)
            intent.putExtra(INTENT_NAME_FRAGMENT, opcionMenu!!.nombre )
            startActivity(intent)
        }
    }
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var adapterMenu: OpcionMenuAdapter? = null
    val ZOOM : Float = 14f
    var presenterMenu: MenuOpcionPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_menu)
        setuNavigationDrawer()
        supportFragmentManager.beginTransaction().replace(R.id.frame_base, MapaFragment()).commit()
        presenterMenu = MenuOpcionPresenter()
        presenterMenu!!.setView(this)
        presenterMenu!!.getListOpcionMenu()
    }


    private fun setuNavigationDrawer(){
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }


}
