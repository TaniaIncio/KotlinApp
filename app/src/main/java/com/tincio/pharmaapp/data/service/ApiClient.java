package com.tincio.pharmaapp.data.service;

import android.util.Log;

import com.tincio.pharmaapp.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static ApiService sApiService;
    private static HttpLoggingInterceptor interceptor;
    //

    public static ApiService getApiService() {
        if (sApiService == null) {
           // initInterceptor();
          /*  OkHttpClient okHttpClient;
            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(ApiConstants.API_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(ApiConstants.API_TIMEOUT, TimeUnit.SECONDS)
                   // .addInterceptor(interceptor)
                    .build();*/
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(initInterceptor())
                    .build();

            sApiService = retrofit.create(ApiService.class);
        }
        return sApiService;
    }
    private static OkHttpClient initInterceptor() {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                String string = original.url().toString();
                //string = string.replace("%26", "&")
                string = string.replace("where%3D", "where=");
                string = string.replace("email=", "email%3D");//%27
                string = string.replace("password=", "password%3D");
                Request newRequest = new Request.Builder()
                        .url(string)
                        .build();
                // Request customization: add request headers
                //Request.Builder requestBuilder = original.newBuilder()
                  //      .header("Authorization", "auth-value"); // <-- this is the important line

             //   Request request = requestBuilder.build();
                Log.i("urls ", "request final url " +newRequest.url());
                return chain.proceed(newRequest);
            }
        });

        OkHttpClient client = httpClient.build();
        return client;
    }

   /* Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request()
        def string = request.url().toString()
        string = string.replace("%26", "&")
        string = string.replace("%3D", "=")

        Request newRequest = new Request.Builder()
                .url(string)
                .build()

        return chain.proceed(newRequest)
    }*/


}
