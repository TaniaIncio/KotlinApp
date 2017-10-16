package com.tincio.pharmaapp.presentation.util.widget;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.model.GenericModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogCustomFragment extends DialogFragment implements TimePickerFragment.OnFragmentInteractionListener, View.OnClickListener, CheckBox.OnCheckedChangeListener {

    EditText txtHoraInicioLunes,txtHoraInicioMartes,txtHoraInicioMiercoles,txtHoraInicioJueves,txtHoraInicioViernes,txtHoraInicioSabado;
    EditText txtHoraFinLunes,txtHoraFinMartes,txtHoraFinMiercoles,txtHoraFinJueves,txtHoraFinViernes,txtHoraFinSabado;
    EditText txtGeneral;
    TimePickerFragment timePickerFragment;
    CheckBox chkLunes, chkMartes, chkMiercoles, chkJueves, chkViernes, chkSabado;
    String TAG = getClass().getSimpleName();
    Button btnGuardar;
    ArrayList<GenericModel> listItemsSelected;
    OnFragmentInteractionListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_disponibilidad_cliente, container, false);
        setupInitCOntrols(view);

        return view;
    }

    void setupInitCOntrols(View view){
        txtHoraInicioLunes = (EditText)view.findViewById(R.id.txt_horainicio_lunes);
        txtHoraInicioMartes = (EditText)view.findViewById(R.id.txt_horainicio_martes);
        txtHoraInicioMiercoles = (EditText)view.findViewById(R.id.txt_horainicio_miercoles);
        txtHoraInicioJueves = (EditText)view.findViewById(R.id.txt_horainicio_jueves);
        txtHoraInicioViernes = (EditText)view.findViewById(R.id.txt_horainicio_viernes);
        txtHoraInicioSabado = (EditText)view.findViewById(R.id.txt_horainicio_sabado);

        txtHoraFinLunes = (EditText)view.findViewById(R.id.txt_horafin_lunes);
        txtHoraFinMartes = (EditText)view.findViewById(R.id.txt_horafin_martes);
        txtHoraFinMiercoles = (EditText)view.findViewById(R.id.txt_horafin_miercoles);
        txtHoraFinJueves = (EditText)view.findViewById(R.id.txt_horafin_jueves);
        txtHoraFinViernes = (EditText)view.findViewById(R.id.txt_horafin_viernes);
        txtHoraFinSabado = (EditText)view.findViewById(R.id.txt_horafin_sabado);

        chkLunes = (CheckBox)view.findViewById(R.id.check_lunes);
        chkMartes = (CheckBox)view.findViewById(R.id.check_martes);
        chkMiercoles = (CheckBox)view.findViewById(R.id.check_miercoles);
        chkJueves = (CheckBox)view.findViewById(R.id.check_jueves);
        chkViernes = (CheckBox)view.findViewById(R.id.check_viernes);
        chkSabado = (CheckBox)view.findViewById(R.id.check_sabado);

        btnGuardar = (Button)view.findViewById(R.id.btn_guardar_disponibilidad);
        listItemsSelected = new ArrayList<GenericModel>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEvents();
    }

    public void setupListener(OnFragmentInteractionListener listener){
        this.listener = listener;
    }

    void setEvents(){
        txtHoraInicioLunes.setOnClickListener(this);
        txtHoraFinLunes.setOnClickListener(this);
        txtHoraInicioMartes.setOnClickListener(this);
        txtHoraFinMartes.setOnClickListener(this);
        txtHoraInicioMiercoles.setOnClickListener(this);
        txtHoraFinMiercoles.setOnClickListener(this);
        txtHoraInicioJueves.setOnClickListener(this);
        txtHoraFinJueves.setOnClickListener(this);
        txtHoraInicioViernes.setOnClickListener(this);
        txtHoraFinViernes.setOnClickListener(this);
        txtHoraInicioSabado.setOnClickListener(this);
        txtHoraFinSabado.setOnClickListener(this);

        chkLunes.setOnCheckedChangeListener(this);
        chkMartes.setOnCheckedChangeListener(this);
        chkMiercoles.setOnCheckedChangeListener(this);
        chkJueves.setOnCheckedChangeListener(this);
        chkViernes.setOnCheckedChangeListener(this);
        chkSabado.setOnCheckedChangeListener(this);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setItemsSelected();
                if(listener!=null)
                    listener.onGetListItemsSelected(listItemsSelected);
            }
        });
    }

    void setItemsSelected(){
        GenericModel model;
        if(chkLunes.isChecked()){
            model = new GenericModel();
            model.setNombre(chkLunes.getTag().toString());
            if(txtHoraInicioLunes.getText().toString().isEmpty()) {
                txtHoraInicioLunes.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraInicio(txtHoraInicioLunes.getText().toString());
            if(txtHoraFinLunes.getText().toString().isEmpty()) {
                txtHoraFinLunes.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraFin(txtHoraFinLunes.getText().toString());
            listItemsSelected.add(model);
        }
        /*martes*/
        if(chkMartes.isChecked()){
            model = new GenericModel();
            model.setNombre(chkMartes.getTag().toString());
            if(txtHoraInicioMartes.getText().toString().isEmpty()) {
                txtHoraInicioMartes.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraInicio(txtHoraInicioMartes.getText().toString());
            if(txtHoraFinMartes.getText().toString().isEmpty()) {
                txtHoraFinMartes.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraFin(txtHoraFinMartes.getText().toString());
            listItemsSelected.add(model);
        }
        /*miercoles*/
        if(chkMiercoles.isChecked()){
            model = new GenericModel();
            model.setNombre(chkMiercoles.getTag().toString());
            if(txtHoraInicioMiercoles.getText().toString().isEmpty()) {
                txtHoraInicioMiercoles.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraInicio(txtHoraInicioMiercoles.getText().toString());
            if(txtHoraFinMiercoles.getText().toString().isEmpty()) {
                txtHoraFinMiercoles.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraFin(txtHoraFinMiercoles.getText().toString());
            listItemsSelected.add(model);
        }
        /*Jueves*/
        if(chkJueves.isChecked()){
            model = new GenericModel();
            model.setNombre(chkJueves.getTag().toString());
            if(txtHoraInicioJueves.getText().toString().isEmpty()) {
                txtHoraInicioJueves.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraInicio(txtHoraInicioJueves.getText().toString());
            if(txtHoraFinJueves.getText().toString().isEmpty()) {
                txtHoraFinJueves.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraFin(txtHoraFinJueves.getText().toString());
            listItemsSelected.add(model);
        }
        /*Viernes*/
        if(chkViernes.isChecked()){
            model = new GenericModel();
            model.setNombre(chkViernes.getTag().toString());
            if(txtHoraInicioViernes.getText().toString().isEmpty()) {
                txtHoraInicioViernes.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraInicio(txtHoraInicioViernes.getText().toString());
            if(txtHoraFinViernes.getText().toString().isEmpty()) {
                txtHoraFinViernes.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraFin(txtHoraFinViernes.getText().toString());
            listItemsSelected.add(model);
        }
         /*Sabado*/
        if(chkSabado.isChecked()){
            model = new GenericModel();
            model.setNombre(chkSabado.getTag().toString());
            if(txtHoraInicioSabado.getText().toString().isEmpty()) {
                txtHoraInicioSabado.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraInicio(txtHoraInicioSabado.getText().toString());
            if(txtHoraFinSabado.getText().toString().isEmpty()) {
                txtHoraFinSabado.setError(getString(R.string.error_ingrese_hora));
                return;
            }else
                model.setHoraFin(txtHoraFinSabado.getText().toString());
            listItemsSelected.add(model);
        }
        dismiss();
    }

    @Override
    public void onShowTime(String time) {
        Log.i(TAG, "show time "+time);
        txtGeneral.setError(null);
        txtGeneral.setText(time);
    }

    @Override
    public void onClick(View view) {
        txtGeneral = (EditText) view;
        Log.i("dialog ",txtGeneral.getTag().toString());
        if(Boolean.parseBoolean(txtGeneral.getTag().toString()))
            showTImePicker();
    }

    void showTImePicker(){
        timePickerFragment= new TimePickerFragment();
        timePickerFragment.setupListener(this);
        timePickerFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if(compoundButton.getTag().equals(getString(R.string.chk_lunes))){
            txtHoraInicioLunes.setTag(checked);txtHoraFinLunes.setTag(checked);return;
        }
        if(compoundButton.getTag().equals(getString(R.string.chk_martes))){
            txtHoraInicioMartes.setTag(checked);txtHoraFinMartes.setTag(checked);return;
        }
        if(compoundButton.getTag().equals(getString(R.string.chk_miercoles))){
            txtHoraInicioMiercoles.setTag(checked);txtHoraFinMiercoles.setTag(checked);return;
        }
        if(compoundButton.getTag().equals(getString(R.string.chk_jueves))){
            txtHoraInicioJueves.setTag(checked);txtHoraFinJueves.setTag(checked);return;
        }
        if(compoundButton.getTag().equals(getString(R.string.chk_viernes))){
            txtHoraInicioViernes.setTag(checked);txtHoraFinViernes.setTag(checked);return;
        }
        if(compoundButton.getTag().equals(getString(R.string.chk_sabado))){
            txtHoraInicioSabado.setTag(checked);txtHoraFinSabado.setTag(checked);return;
        }
    }

    public interface OnFragmentInteractionListener{
        void onGetListItemsSelected(ArrayList<GenericModel> list);
    }
    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Dialogo")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }*/
}
