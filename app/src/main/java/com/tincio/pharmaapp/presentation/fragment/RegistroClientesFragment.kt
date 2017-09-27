package com.tincio.pharmaapp.presentation.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tincio.pharmaapp.R
import kotlinx.android.synthetic.main.fragment_clientes.*


/**
 * A simple [Fragment] subclass.
 */
class RegistroClientesFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_clientes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
    }

    fun setupEvents(){
        btn_register_client.setOnClickListener{
            fragmentManager.popBackStack()
        }
    }

}// Required empty public constructor
