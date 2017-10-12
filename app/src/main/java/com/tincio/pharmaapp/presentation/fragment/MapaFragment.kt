package com.tincio.pharmaapp.presentation.fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
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
import com.tincio.pharmaapp.presentation.activity.ListaRutaActivity
import com.tincio.pharmaapp.presentation.activity.PrincipalActivity
import com.tincio.pharmaapp.presentation.adapter.MapAdapterRecycler
import com.tincio.pharmaapp.presentation.util.Images
import com.tincio.pharmaapp.presentation.util.widget.Spinner
import com.tincio.pharmaapp.presentation.util.widget.SpinnerModel
import kotlinx.android.synthetic.main.activity_navigation_menu.*
import kotlinx.android.synthetic.main.fragment_mapa.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.logging.Logger

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MapaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MapaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapaFragment : Fragment(), OnMapReadyCallback,View.OnClickListener {
    override fun onClick(p0: View?) {
        mDialog!!.dismiss()
    }

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var markers : Array<MarkerOptions>? = null

    private var mListener: OnFragmentInteractionListener? = null
    var mDialog: Dialog? = null
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
     /*   val mapFragment = view.findViewById<View>(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/
//        setUp()

        var mapFragment : SupportMapFragment?=null
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUp()
        setEvents()
    }

    fun setEvents(){
        ic_go_list.setOnClickListener {
            startActivity(Intent(activity@activity, ListaRutaActivity::class.java))
            activity.overridePendingTransition(R.anim.slide_up, R.anim.stay) }

        ic_menu.setOnClickListener{
            activity.drawer_layout.openDrawer(GravityCompat.START)
        }

        spinner_map.setOnClickListener{
            var sp = Spinner(getActivity(), "Seleccione", Images.getButtons(), Images.getWeek(), this, this);
             mDialog = sp.getDialog();
            mDialog!!.show()
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    private lateinit var mMap: GoogleMap
    var adapterDoctor: MapAdapterRecycler? = null
    val ZOOM : Float = 14f


    private fun setUp(){
        var array = Array(7, {  "";"";"";"";"";"";"";""})
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rec_doctors.layoutManager = layoutManager
        rec_doctors.hasFixedSize()
        adapterDoctor = MapAdapterRecycler(array)
        rec_doctors.adapter = adapterDoctor
        rec_doctors.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            // layoutManager.findFirstCompletelyVisibleItemPosition()
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var indice = layoutManager.findFirstCompletelyVisibleItemPosition();
                Log.i("indice visible ", " "+layoutManager.findFirstCompletelyVisibleItemPosition()+" position ")
                if (indice!=-1){
                    Log.i("ingreso ", " "+layoutManager.findFirstCompletelyVisibleItemPosition()+" position "+markers!![indice].position)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markers!![indice].position, ZOOM))
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
        addMarkers()
        setUpMap()
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, ZOOM))
      drawRuta()
    }

    fun drawRuta(){
        Images.setMapRuta(mMap)
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
        markers = Array(7, { MarkerOptions().position(LatLng(-12.0891996,-77.0570098)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));MarkerOptions().position(LatLng(-12.0949766,-77.0281831)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
            MarkerOptions().position(LatLng(-12.1215361,-77.0463574)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
            MarkerOptions().position(LatLng(-12.1443466,-77.0297666)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
            MarkerOptions().position(LatLng(-12.0987112,-77.0528037)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
            MarkerOptions().position(LatLng(-12.086794,-77.0614297)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
            MarkerOptions().position(LatLng(-12.0871402,-77.0674807)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker))})
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment MapaFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MapaFragment {
            val fragment = MapaFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
