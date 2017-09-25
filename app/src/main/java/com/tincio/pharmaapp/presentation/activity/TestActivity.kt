package com.tincio.pharmaapp.presentation.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.presentation.fragment.*
import com.tincio.pharmaapp.presentation.fragment.dummy.DummyContent

class TestActivity : AppCompatActivity(), ListClientesFragment.OnListFragmentInteractionListener, ProductoFragment.OnListFragmentInteractionListener, NecesidadPacienteFragment.OnListFragmentInteractionListener , ComentarioProductoFragment.OnListFragmentInteractionListener, ComentarioVisitaFragment.OnListFragmentInteractionListener, NecesidadMedicosFragment.OnListFragmentInteractionListener{
    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        supportFragmentManager.beginTransaction().replace(R.id.container, NecesidadMedicosFragment()).commit()
    }
}
