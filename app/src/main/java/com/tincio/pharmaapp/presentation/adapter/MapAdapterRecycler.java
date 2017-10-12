package com.tincio.pharmaapp.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.MarkerOptions;
import com.tincio.pharmaapp.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.ArrayList;

/**
 * Created by tincio on 22/07/2016.
 */
public class MapAdapterRecycler extends RecyclerView.Adapter<MapAdapterRecycler.ViewHolderItem> {

    String[] arrayString;
    Context context;

    public MapAdapterRecycler(String[] arrayString){
        this.arrayString = arrayString;
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
//        holder.txtItemRecycler.setText(arrayString[position]);

    }

    @Override
    public int getItemCount() {
        return arrayString.length;
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        TextView txtItemRecycler;
       // LinearLayout linearFront;
        //LinearLayout linearBack;
        EasyFlipView flipView;
        TextView btnRegistrar;
        public ViewHolderItem(View itemView) {
            super(itemView);
            flipView = (EasyFlipView)itemView.findViewById(R.id.flip_cardview);
            btnRegistrar = (TextView)itemView.findViewById(R.id.btn_registrar_visita);
            btnRegistrar.setOnClickListener(new View.OnClickListener() {
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

        void setAnimations(){
           // linearFront.setCUs
             /*linearFront.setAnimation(new Ani(
                    R.anim.card_flip_right_in,
                    R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in,
                    R.animator.card_flip_left_out)*/
                //     linearFront.startAnimation(AnimationUtils.loadAnimation(context,R.anim.flip_in_right));
           // linearFront.setVisibility(View.GONE);
          //  linearBack.setVisibility(View.VISIBLE);
         //   linearFront.startAnimation(AnimationUtils.loadAnimation(context,R.anim.flip_in_left));
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
