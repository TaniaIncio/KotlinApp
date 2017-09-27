package com.tincio.pharmaapp.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.presentation.fragment.*
import com.tincio.pharmaapp.presentation.fragment.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(), ListClientesFragment.OnListFragmentInteractionListener, ProductoFragment.OnListFragmentInteractionListener, NecesidadPacienteFragment.OnListFragmentInteractionListener , ComentarioProductoFragment.OnListFragmentInteractionListener, ComentarioVisitaFragment.OnListFragmentInteractionListener, NecesidadMedicosFragment.OnListFragmentInteractionListener{
    val INTENT_NAME_FRAGMENT: String? = null
    var fragment: Fragment? = null
    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val idFragment = intent.getStringExtra(INTENT_NAME_FRAGMENT)
                ?: throw IllegalStateException("field $INTENT_NAME_FRAGMENT missing in Intent")

        txt_title.setText(idFragment)
        if (idFragment.equals("Clientes")){
            fragment = ListClientesFragment()

        }else if (idFragment.equals("Productos")){
            fragment = ProductoFragment()
        }else if (idFragment.equals("Configuracion")){
            fragment = ConfiguracionFragment()
        }else if (idFragment.equals("Mis Rutas")){
            fragment = ProductoFragment()
        }else if (idFragment.equals("Configuracion")){
            fragment = ConfiguracionFragment()
        }else{
            finish()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container_test, fragment).commit()
    }
}
