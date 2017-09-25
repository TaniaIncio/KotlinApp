package com.tincio.pharmaapp.presentation.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tincio.pharmaapp.R

import com.tincio.pharmaapp.presentation.fragment.ComentarioVisitaFragment.OnListFragmentInteractionListener
import com.tincio.pharmaapp.presentation.fragment.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ComentarioVisitaRecyclerViewAdapter(private val mValues: List<DummyItem>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<ComentarioVisitaRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_recycler_comentario_visita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    /*    holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].id
        holder.mContentView.text = mValues[position].content
*/
        holder.mView.setOnClickListener {
  //          mListener?.onListFragmentInteraction(holder.mItem)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
/*
        val mIdView: TextView
        val mContentView: TextView
        var mItem: DummyItem? = null
*/

        init {
          /*  mIdView = mView.findViewById(R.id.id) as TextView
            mContentView = mView.findViewById(R.id.content) as TextView*/
        }
/*
        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }*/
    }
}
