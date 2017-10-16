package com.tincio.pharmaapp.presentation.util.widget;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by tania on 15/10/2017.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    OnFragmentInteractionListener mListener;

    public void setupListener(OnFragmentInteractionListener listener){
        mListener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerFragment and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.i("hora ",""+hourOfDay+":"+minute);
        if(mListener != null) {
            Log.i("hora dso",""+hourOfDay+":"+minute);
            mListener.onShowTime("" + hourOfDay + ":" + minute);
        }
    }

    public interface OnFragmentInteractionListener{
        void onShowTime(String time);
    }
}