package com.tincio.pharmaapp.presentation.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import com.tincio.pharmaapp.R
import com.tincio.pharmaapp.data.service.request.MedicosRequest
import com.tincio.pharmaapp.data.service.response.MedicosResponse
import com.tincio.pharmaapp.presentation.activity.ListaRutaActivity
import com.tincio.pharmaapp.presentation.activity.PrincipalActivity
import com.tincio.pharmaapp.presentation.adapter.MapAdapterRecycler
import com.tincio.pharmaapp.presentation.presenter.MedicosPresenter
import com.tincio.pharmaapp.presentation.presenter.MedicosPresenterImpl
import com.tincio.pharmaapp.presentation.util.Images
import com.tincio.pharmaapp.presentation.util.MapaUtil
import com.tincio.pharmaapp.presentation.util.widget.Spinner
import com.tincio.pharmaapp.presentation.util.widget.SpinnerModel
import com.tincio.pharmaapp.presentation.view.MedicosView
import kotlinx.android.synthetic.main.activity_navigation_menu.*
import kotlinx.android.synthetic.main.fragment_mapa.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.view_spinner_map.*
import java.util.logging.Logger

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MapaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MapaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapaFragment : Fragment(), OnMapReadyCallback,View.OnClickListener, MedicosView {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var markers : Array<MarkerOptions>? = null
    var presenter: MedicosPresenter? = null
    var daySelected : String = ""
    val OFFSET = 20
    var listMedicos: MutableList<MedicosResponse>? = null

    private var mListener: OnFragmentInteractionListener? = null
    var mDialog: Dialog? = null
    private lateinit var mMap: GoogleMap
    var adapterDoctor: MapAdapterRecycler? = null
    val ZOOM : Float = 12f
    var sp : Spinner? = null
    //val layoutManager :LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_mapa, container, false)
        presenter = MedicosPresenterImpl(this)
        var mapFragment : SupportMapFragment?=null
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        daySelected = getString(R.string.chk_miercoles)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setEvents()
    }


    fun callMedicos(){
        var request = MedicosRequest()
        request.dia = daySelected
        request.offset = OFFSET
        presenter!!.getMedicosDia(request)
    }

    fun setEvents(){
        ic_go_list.setOnClickListener {
            startActivity(Intent(activity@activity, ListaRutaActivity::class.java))
            activity.overridePendingTransition(R.anim.slide_up, R.anim.stay) }

        ic_menu.setOnClickListener{
            activity.drawer_layout.openDrawer(GravityCompat.START)
        }

        spinner_map.setOnClickListener{
                sp = Spinner(getActivity(), "Seleccione", Images.getButtons(), Images.getWeek(), this, this)
                mDialog = sp!!.getDialog()
                mDialog!!.show()
        }
    }




    private fun setUp(){
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rec_doctors.layoutManager = layoutManager
        rec_doctors.hasFixedSize()
        adapterDoctor = MapAdapterRecycler(listMedicos, daySelected)
        rec_doctors.adapter = adapterDoctor
        rec_doctors.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            // layoutManager.findFirstCompletelyVisibleItemPosition()
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var indice = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.i("indice visible ", " "+layoutManager.findFirstCompletelyVisibleItemPosition()+" position ")
                if (indice!=-1){
//                    Log.i("ingreso ", " "+layoutManager.findFirstCompletelyVisibleItemPosition()+" position "+markers!![indice].position)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(listMedicos!!.get(indice).latitud.toDouble(),listMedicos!!.get(indice).longitud.toDouble()), ZOOM))
                }
            }
        })





        //   setUpSpinner()

    }


  /*  private fun setUpSpinner(){
        //spinner_map.adapter(Adap)
        val dataAdapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resources.getStringArray(R.array.array_day))
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //spinner_map.setAdapter(dataAdapter)
    }*/
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-12.0891996,-77.0570098)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, ZOOM))
        callMedicos()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
        fun showLoader(message: String?)
        fun hideLoader()
        fun showDialog(message: String?)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        fun newInstance(param1: String, param2: String): MapaFragment {
            val fragment = MapaFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    /**Methodos herence*/

    override fun showError(message: String?) {
        if(mListener!=null)
            mListener!!.showLoader(getString(R.string.loader_searching_medicos))
    }

    override fun hideLoader() {
        mListener!!.hideLoader()
    }

    override fun drawMedicosinMap(listMedicos: MutableList<MedicosResponse>?) {
        this.listMedicos = listMedicos
        addMarkers()
        setUpMap()
        drawRuta()
        setUp()
    }

    override fun showLoader(message: String?) {
        if(mListener!=null)
            mListener!!.showLoader(getString(R.string.loader_searching_medicos))
    }

    override fun onClick(p0: View?) {

        txt_spinner!!.text = Images.getWeek()[sp!!.pos].nombre;
        if(sp!!.pos == 0){
            daySelected = getString(R.string.chk_lunes)
        }
        if(sp!!.pos == 1){
            daySelected = getString(R.string.chk_martes)
        }
        if(sp!!.pos == 2){
            daySelected = getString(R.string.chk_miercoles)
        }
        if(sp!!.pos == 4){
            daySelected = getString(R.string.chk_jueves)
        }
        if(sp!!.pos == 5){
            daySelected = getString(R.string.chk_viernes)
        }
        mDialog!!.dismiss()
        callMedicos()
    }


    fun drawRuta(){
        MapaUtil.drawRoute(LatLng(listMedicos!!.get(0).latitud.toDouble(), listMedicos!!.get(0).latitud.toDouble()),
                LatLng(listMedicos!!.get(1).latitud.toDouble(), listMedicos!!.get(1).latitud.toDouble()), mMap)
    }

    private fun setUpMap(){
        mMap.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener{
            override fun onMarkerClick(marker: Marker?): Boolean {
                Log.i("indice marker", marker!!.id)
               // rec_doctors.smoothScrollToPosition(marker!!.snippet.toInt())
                return false
            }
        })
    }

    private fun addMarkers(){
        var indice : Int = 0
        for (item: MedicosResponse in listMedicos!!){
            mMap.addMarker(MarkerOptions().position(LatLng(item.latitud.toDouble(),item.longitud.toDouble()))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker))
                    .snippet(indice.toString())
                    .title(item.nombres)
                    )
            indice ++
          //          .title(item.id.toString())
        }
        //  .snippet(indice.toString())
        if(listMedicos!!.size > 0){
            val sydney = LatLng(listMedicos!!.get(0).latitud.toDouble(),listMedicos!!.get(0).longitud.toDouble())
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, ZOOM))
        }
    }

    /*end meths */

}// Required empty public constructor
