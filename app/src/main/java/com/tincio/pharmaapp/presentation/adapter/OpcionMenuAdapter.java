package com.tincio.pharmaapp.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.model.OpcionMenu;

import java.util.List;

/**
 * Created by tincio on 22/07/2016.
 */
public class OpcionMenuAdapter extends RecyclerView.Adapter<OpcionMenuAdapter.OpcionMenuViewHolder> {

    Context context;
    List<OpcionMenu> list;

    public OpcionMenuAdapter(List<OpcionMenu> list) {
        this.list = list;
    }

    @Override
    public OpcionMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_opcion_menu, parent, false);
        OpcionMenuViewHolder viewHolder = new OpcionMenuViewHolder(view);
        return viewHolder;
    }

    OpcionMenu opc;

    @Override
    public void onBindViewHolder(OpcionMenuViewHolder holder, int position) {
        opc = list.get(position);
        holder.txtOpcNav.setText(opc.getNombre());
     //   holder.imgOpc.setImageDrawable(Images.getDrawableByName(context, opc.getImagen()));
       /* if (holder.txtOpcNav.getText().equals("Cerrar Sesion")) {
            holder.imgIrMenu.setImageDrawable(Images.getDrawableByName(context, "ic_plus_close_new2"));
        }
        if (holder.txtOpcNav.getText().equals("Cerrar Sesion")) {
            holder.imgOpc.setVisibility(View.INVISIBLE);
        }*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OpcionMenuViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearOpcNav;
        ImageView imgOpc;
        TextView txtOpcNav;
        ImageView imgIrMenu;

        public OpcionMenuViewHolder(View itemView) {
            super(itemView);
            linearOpcNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickLIstener != null) {
                        onItemClickLIstener.setOnItemClickListener(list.get(getPosition()));
                    }
                }
            });
        }
    }

    public OnItemClickListener onItemClickLIstener;

    public interface OnItemClickListener {
        public void setOnItemClickListener(OpcionMenu opcionMenu);
    }

    public void setOnItemClickLIstener(OnItemClickListener onItemClickListener) {
        this.onItemClickLIstener = onItemClickListener;
    }
}