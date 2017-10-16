package com.tincio.pharmaapp.presentation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.presentation.adapter.ClienteRutaAdapter;

import java.util.Arrays;
import java.util.List;

public class RutaActivity extends AppCompatActivity {

    RecyclerView recCliente;
    ClienteRutaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);
        initControls();
    }

    void initControls(){
        recCliente = (RecyclerView)findViewById(R.id.rec_clientes_ruta);
        String[] array = new String[]{"","","","","","",""};
        List<String> list = Arrays.asList(array);
        adapter = new ClienteRutaAdapter(list);
        recCliente.setAdapter(adapter);
    }
}
