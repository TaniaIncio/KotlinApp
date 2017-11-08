package com.tincio.pharmaapp.domain.interactor;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.service.ApiClient;
import com.tincio.pharmaapp.data.service.request.MedicosRequest;
import com.tincio.pharmaapp.data.service.response.MedicosResponse;
import com.tincio.pharmaapp.domain.callback.MedicosCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicosInteractor {

    MedicosCallback callback;
    Context context;
    private String TAG = getClass().getSimpleName();
    public MedicosInteractor(MedicosCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    private Call<List<MedicosResponse>> getResponseDia(MedicosRequest request){
        if(request.getDia().equals(context.getString(R.string.chk_lunes))){
            return  ApiClient.getApiService().getMedicosLunes(request.getOffset());
            //  return;
        }
        if(request.getDia().equals(context.getString(R.string.chk_martes))){
            return  ApiClient.getApiService().getMedicosMartes(request.getOffset());
        }
        if(request.getDia().equals(context.getString(R.string.chk_miercoles))){
            return  ApiClient.getApiService().getMedicosMiercoles(request.getOffset());
        }
        if(request.getDia().equals(context.getString(R.string.chk_jueves))){
            return  ApiClient.getApiService().getMedicosJueves(request.getOffset());
        }
        if(request.getDia().equals(context.getString(R.string.chk_viernes))){
            return ApiClient.getApiService().getMedicosViernes(request.getOffset());
        }
        return null;
    }

    public void getMedicos(MedicosRequest request) {
        Call<List<MedicosResponse>> response = getResponseDia(request);

        Log.i(TAG, " Request Medicos : " + " URL -> " +response.request().url());
        response.enqueue(new Callback<List<MedicosResponse>>(){
                             @Override
                             public void onResponse(Call<List<MedicosResponse>> call, Response<List<MedicosResponse>> response) {
                                   //AppLogger.info(TAG, "downloadData onResponse");
                              Log.i(TAG, " Response Medicos : " + new Gson().toJson(response.body()));

                                 try {
                                     //final MedicosResponse dataResponse = response.body();
                                     if(response.body()==null) {
                                         callback.onResponse(false, context.getString(R.string.error_empty_medicos), null);
                                         return;
                                     }
                                        if(response.body().size() >0)
                                            callback.onResponse(true, "", response.body());
                                        else
                                            callback.onResponse(false, context.getString(R.string.error_empty_medicos), null);

                                 } catch (Exception e) {
                                     e.printStackTrace();
                                     callback.onResponse(false, context.getString(R.string.error_service), null);
                                 }
                             }

                             @Override
                             public void onFailure(Call<List<MedicosResponse>> call, Throwable t) {
                                 //AppLogger.error(TAG, "downloadData onFailure");
                                 Log.e(TAG, t.getMessage());
                                 callback.onResponse(false, context.getString(R.string.error_service), null);
                             }
                         }
        );
    }

}
