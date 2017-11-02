package com.tincio.pharmaapp.domain.interactor;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.data.service.ApiClient;
import com.tincio.pharmaapp.data.service.request.UsuarioRequest;
import com.tincio.pharmaapp.data.service.response.UsuarioResponse;
import com.tincio.pharmaapp.domain.callback.UsuarioCallback;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioInteractor {

    UsuarioCallback callback;
    Context context;
    private String TAG = getClass().getSimpleName();
    public UsuarioInteractor(UsuarioCallback callback, Context context) {
        this.callback = callback;
        this.context = context;
    }

    public void getUsuario(UsuarioRequest request) {
        RequestBody caption = RequestBody.create(MediaType.parse("text/plain"), new String(request.getEmail()));
        Call<List<UsuarioResponse>> response = ApiClient.getApiService().getUsuario(caption, request.getPassword());
        Log.i(TAG, " Request Usuario : " + " URL -> " +response.request().url());
        Log.i(TAG, " Request Usuario changed : " + " URL -> " +response.request().url().toString().replace("=","%3D"));
        //response.request().url(originalRequest.url().toString().replace("%3D","="));
        response.request().newBuilder().url(response.request().url().toString().replace("%3D","="));//.addEncodedQueryParameter("%3D","=").build();
        Log.i(TAG, " Request Usuario : " + " URL -> " +response.request().url());
        //retrofit.baseUrl().newBuilder().addEncodedQueryParameter("%3D","=").build();
        response.enqueue(new Callback<List<UsuarioResponse>>(){
                             @Override
                             public void onResponse(Call<List<UsuarioResponse>> call, Response<List<UsuarioResponse>> response) {
                                   //AppLogger.info(TAG, "downloadData onResponse");
                              Log.i(TAG, " Response Usuario : " + new Gson().toJson(response.body()));

                                 try {
                                     //final UsuarioResponse dataResponse = response.body();
                                        if(response.body().size() >0)
                                            callback.onResponse(true, "");
                                        else
                                            callback.onResponse(false, context.getString(R.string.error_usuario_clave));

                                 } catch (Exception e) {
                                     e.printStackTrace();
                                     callback.onResponse(false, context.getString(R.string.error_service));
                                 }
                             }

                             @Override
                             public void onFailure(Call<List<UsuarioResponse>> call, Throwable t) {
                                 //AppLogger.error(TAG, "downloadData onFailure");
                                 Log.e(TAG, t.getMessage());
                                 callback.onResponse(false, context.getString(R.string.error_service));
                             }
                         }
        );
    }

}
