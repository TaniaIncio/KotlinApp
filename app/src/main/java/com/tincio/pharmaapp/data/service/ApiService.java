package com.tincio.pharmaapp.data.service;

import com.tincio.pharmaapp.data.service.response.UsuarioResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /*@GET(ApiConstants.GET_USUARIO)
        //Call<JSONObject> getYoutbeFeeds(@Query("key") String developerKey, @Query("channelId") String channelId, @Query("part") String id, @Query("alt") String alt);
    Call<List<UsuarioResponse>> getUsuario(@Query("email") String email, @Query("password") String password);*/


    @GET(ApiConstants.GET_USUARIO)
        //Call<JSONObject> getYoutbeFeeds(@Query("key") String developerKey, @Query("channelId") String channelId, @Query("part") String id, @Query("alt") String alt);
    Call<List<UsuarioResponse>> getUsuario(@Query(value = "where=email") RequestBody email, @Query(value = "password", encoded = true) String password);
    /*@POST(ApiConstants.POST_GET_USUARIO)
    Call<GeneralDataResponse> getGeneralData(@Body Object data);*/
    /*@GET("data/Step")
 +    Call<List<StepResponse>> geSteps(@Query(value = "where", encoded = true) String idRecipe);*/

}