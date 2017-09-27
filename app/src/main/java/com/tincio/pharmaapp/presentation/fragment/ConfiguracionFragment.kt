package com.tincio.pharmaapp.presentation.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tincio.pharmaapp.R
import kotlinx.android.synthetic.main.linear_row_config_cliente.*
import kotlinx.android.synthetic.main.linear_row_config_comentario_producto.*
import kotlinx.android.synthetic.main.linear_row_config_comentario_visita.*
import kotlinx.android.synthetic.main.linear_row_config_necesidades_paciente.*


/**
 * A simple [Fragment] subclass.
 */
class ConfiguracionFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_configuracion, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
    }

    fun setupEvents(){
        carview_necesidad_clientes.setOnClickListener {
            activity.supportFragmentManager.beginTransaction().replace(R.id.container_test, NecesidadMedicosFragment()).addToBackStack("").commit()
        }
        carview_necesidad_pacientes.setOnClickListener {
            activity.supportFragmentManager.beginTransaction().replace(R.id.container_test, NecesidadPacienteFragment()).addToBackStack("").commit()
        }
        carview_comentario_visita.setOnClickListener {
            activity.supportFragmentManager.beginTransaction().replace(R.id.container_test, ComentarioVisitaFragment()).addToBackStack("").commit()
        }
        carview_comentario_producto.setOnClickListener {
            activity.supportFragmentManager.beginTransaction().replace(R.id.container_test, ComentarioProductoFragment()).addToBackStack("").commit()
        }
    }

}// Required empty public constructor
