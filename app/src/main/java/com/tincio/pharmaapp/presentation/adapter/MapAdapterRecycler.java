package com.tincio.pharmaapp.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.MarkerOptions;
import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.service.response.MedicosResponse;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tincio on 22/07/2016.
 */
public class MapAdapterRecycler extends RecyclerView.Adapter<MapAdapterRecycler.ViewHolderItem> {

    List<MedicosResponse> arrayString;
    Context context;
    String day;

    public MapAdapterRecycler(List<MedicosResponse> arrayString, String day){
        this.arrayString = arrayString;
        this.day = day;
    }
    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_map, parent, false);
        ViewHolderItem viewHolder = new ViewHolderItem(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, int position) {
        MedicosResponse item = arrayString.get(position);
        holder.txtName.setText(item.getNombres());
        holder.txtHospital.setText(item.getHospital());
        holder.txtDireccion.setText(item.getDireccion());
        holder.txtHoraInicio.setText(item.getHoraInicio(day,context));
        holder.txtHoraFin.setText(" - "+item.getHoraFin(day,context));

    }

    @Override
    public int getItemCount() {
        return arrayString.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        TextView txtName,txtHospital,txtDireccion, txtHoraInicio, txtHoraFin,txtDistancia,txtDeshacer,linkEditar;
       // LinearLayout linearFront;
        //LinearLayout linearBack;
        EasyFlipView flipView;
        TextView btnRegistrar;
        public ViewHolderItem(View itemView) {
            super(itemView);
            flipView = (EasyFlipView)itemView.findViewById(R.id.flip_cardview);
            txtName= (TextView) itemView.findViewById(R.id.txt_name_doctor);
            txtHospital = (TextView)itemView.findViewById(R.id.txt_hospital_doctor);
            txtDireccion = (TextView)itemView.findViewById(R.id.txt_direccion_doctor);
            txtHoraInicio = (TextView)itemView.findViewById(R.id.txt_hora_inicio);
            txtHoraFin = (TextView)itemView.findViewById(R.id.txt_hora_fin);
            txtDistancia= (TextView)itemView.findViewById(R.id.txt_distancia);
            txtDeshacer= (TextView)itemView.findViewById(R.id.link_deshacer);
            linkEditar = (TextView)itemView.findViewById(R.id.link_completar);
            txtDeshacer.setMovementMethod(LinkMovementMethod.getInstance());

            btnRegistrar = (TextView)itemView.findViewById(R.id.btn_registrar_visita);
            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flipView.flipTheView();

                }
            });
            txtDeshacer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flipView.flipTheView();

                }
            });

        /*    txtItemRecycler = (TextView)itemView.findViewById(R.id.txt_item_recycler);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.setOnItemClickListener(getPosition());
                    }
                }
            });*/
        }

    }

    OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        public void setOnItemClickListener(int posicion);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener){
        this.mOnItemClickListener = mItemClickListener;
    }
}
