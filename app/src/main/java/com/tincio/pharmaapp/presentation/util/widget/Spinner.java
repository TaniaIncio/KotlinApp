package com.tincio.pharmaapp.presentation.util.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.tincio.pharmaapp.R;

public class Spinner {
   String[] mButtons;
   SpinnerModel[] mParams;
   AlertDialog.Builder mDialog;
   Button set,cancel;
   NumberPicker mPicker;
   String[] mDisplayedValue;
   public Spinner (Activity activity, String title, String[] buttons, SpinnerModel[] data,View.OnClickListener cancelListener,View.OnClickListener acceptListener){
       try {
           mParams=data;
           mDialog = new AlertDialog.Builder(activity);
           mDisplayedValue = new String[data.length];
           for (int i = 0; i < data.length; i++) {
               mDisplayedValue[i] = data[i].getNombre();
           }
           LayoutInflater inflater = activity.getLayoutInflater();
           View dialogView = inflater.inflate(R.layout.yearpicker, null);
           mPicker = (NumberPicker) dialogView.findViewById(R.id.dialog_picker);
           ((TextView)dialogView.findViewById(R.id.dialog_title)).setText(title);
           set=(Button)dialogView.findViewById(R.id.btn_aceptar_picker);
           set.setText(buttons[0]);
           set.setOnClickListener(acceptListener);
           cancel=(Button)dialogView.findViewById(R.id.btn_cancelar_picker);
           cancel.setText(buttons[1]);
           cancel.setOnClickListener(cancelListener);
           mPicker.setMaxValue(data.length - 1);
           mPicker.setMinValue(0);
           mPicker.setWrapSelectorWheel(false);
           mPicker.setDisplayedValues(mDisplayedValue);
           mPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
           mDialog.setView(dialogView);

       }catch(Exception e){
            e.printStackTrace();
       }
   }
   public Dialog getDialog(){
       return mDialog.create();
   }
   public int getPos(){
       return mPicker.getValue();
   }



    /*
    final Dialog d = new Dialog(getActivity());
        d.setTitle("Year Picker");
        d.setContentView(R.layout.yearpicker);
    Button set = (Button) d.findViewById(R.id.button1);
    Button cancel = (Button) d.findViewById(R.id.button2);
    final NumberPicker nopicker = (NumberPicker) d.findViewById(R.id.numberPicker1);

        set.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
        String pick=""+nopicker.getValue();
        Toast.makeText(getActivity(),pick,Toast.LENGTH_LONG).show();
        d.dismiss();

    }
    });
        cancel.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
        d.dismiss();


    }
    });
        d.show();
        */
}
