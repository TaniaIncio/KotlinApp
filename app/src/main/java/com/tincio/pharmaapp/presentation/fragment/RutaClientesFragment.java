package com.tincio.pharmaapp.presentation.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.presentation.adapter.ClienteRutaAdapter;
import com.tincio.pharmaapp.presentation.util.Images;
import com.tincio.pharmaapp.presentation.util.widget.Spinner;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RutaClientesFragment extends Fragment implements View.OnClickListener {
    RecyclerView recCliente;
    ClienteRutaAdapter adapter;
    EditText txxDiaSemana;
    public RutaClientesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ruta, container, false);
        recCliente = (RecyclerView)view.findViewById(R.id.rec_clientes_ruta);
        txxDiaSemana = (EditText)view.findViewById(R.id.spinner_dia_semana);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initControls();
        setEvents();
    }

    void initControls(){
        String[] array = new String[]{"","","","","","","","",""};
        List<String> list = Arrays.asList(array);
        adapter = new ClienteRutaAdapter(list);
        recCliente.setAdapter(adapter);
    }
    Dialog mDialog;
    void setEvents(){
        txxDiaSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner sp = new Spinner(getActivity(), "Seleccione", new String[]{"Establecer", "Cancelar"}, Images.getWeek(), this, this);
                 mDialog = sp.getDialog();
                mDialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(mDialog!=null)
            mDialog.dismiss();
        txxDiaSemana.setText("Lunes");
    }
}
