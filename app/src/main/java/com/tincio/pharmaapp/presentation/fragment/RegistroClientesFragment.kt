package com.tincio.pharmaapp.presentation.fragment


import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.data.model.GenericModel
import com.tincio.pharmaapp.presentation.activity.DireccionMapActivity
import kotlinx.android.synthetic.main.fragment_clientes.*
import java.util.*
import com.tincio.pharmaapp.presentation.util.widget.DialogCustomFragment




/**
 * A simple [Fragment] subclass.
 */
class RegistroClientesFragment : Fragment(), DialogCustomFragment.OnFragmentInteractionListener  {

    override fun onGetListItemsSelected(list: ArrayList<GenericModel>?) {
        var disponibilidad = ""
        for (model in list!!) {
            disponibilidad = disponibilidad + model.getNombre() +":"+model.horaInicio+"-"+model.horaFin+" ";
        }
        txt_disponibilidad.setText(disponibilidad)
    }

    var mListener: OnFragmentInteractionListener? = null
    var preferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_clientes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setInits()
    }

    fun setInits(){
        preferences = activity.getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        Log.i("tag", "onresume onresume")
        setData()
    }

    fun setupEvents(){
        btn_register_client.setOnClickListener{
            fragmentManager.popBackStack()
        }

        btn_direccion_cliente.setOnClickListener{
            if(mListener != null)
                mListener!!.onChangeActivity(DireccionMapActivity::class.java)
        }
        btn_disponibilidad_cliente.setOnClickListener{
            showDialog()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onChangeActivity(activity: Class<DireccionMapActivity>)
    }

    fun setData(){
        txt_direccion_cliente.setText(preferences!!.getString(getString(R.string.preferences_direccion_cliente),""))
    }

    fun showDialog() {
        try {
            val fragment = DialogCustomFragment()
            fragment.setupListener(this)
            fragment.show(activity.getSupportFragmentManager(), "")
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}// Required empty public constructor
