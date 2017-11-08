package com.tincio.pharmaapp.data.service;

import com.tincio.pharmaapp.data.service.response.MedicosResponse;
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
    Call<List<UsuarioResponse>> getUsuario(@Query(value = "where=email") String email, @Query(value = "password", encoded = true) String password);
    /*@POST(ApiConstants.POST_GET_USUARIO)
    Call<GeneralDataResponse> getGeneralData(@Body Object data);*/
    /*@GET("data/Step")
 +    Call<List<StepResponse>> geSteps(@Query(value = "where", encoded = true) String idRecipe);*/

    @GET(ApiConstants.GET_MEDICOS_LUNES)
    //https://api.backendless.com/5BA9DF87-1078-DDE2-FF1A-10D9DCAE0600/5EF27282-9F11-333E-FFD6-AE5B390CCC00/data/medicos?offset=20&where=lunes_inicio!%3D''
    Call<List<MedicosResponse>> getMedicosLunes(@Query(value = "offset", encoded = true) int offset);

    @GET(ApiConstants.GET_MEDICOS_MARTES)
        //https://api.backendless.com/5BA9DF87-1078-DDE2-FF1A-10D9DCAE0600/5EF27282-9F11-333E-FFD6-AE5B390CCC00/data/medicos?offset=20&where=lunes_inicio!%3D''
    Call<List<MedicosResponse>> getMedicosMartes(@Query(value = "offset", encoded = true) int offset);

    @GET(ApiConstants.GET_MEDICOS_MIERCOLES)
        //https://api.backendless.com/5BA9DF87-1078-DDE2-FF1A-10D9DCAE0600/5EF27282-9F11-333E-FFD6-AE5B390CCC00/data/medicos?offset=20&where=lunes_inicio!%3D''
    Call<List<MedicosResponse>> getMedicosMiercoles(@Query(value = "offset", encoded = true) int offset);

    @GET(ApiConstants.GET_MEDICOS_JUEVES)
        //https://api.backendless.com/5BA9DF87-1078-DDE2-FF1A-10D9DCAE0600/5EF27282-9F11-333E-FFD6-AE5B390CCC00/data/medicos?offset=20&where=lunes_inicio!%3D''
    Call<List<MedicosResponse>> getMedicosJueves(@Query(value = "offset", encoded = true) int offset);

    @GET(ApiConstants.GET_MEDICOS_VIERNES)
        //https://api.backendless.com/5BA9DF87-1078-DDE2-FF1A-10D9DCAE0600/5EF27282-9F11-333E-FFD6-AE5B390CCC00/data/medicos?offset=20&where=lunes_inicio!%3D''
    Call<List<MedicosResponse>> getMedicosViernes(@Query(value = "offset", encoded = true) int offset);

}