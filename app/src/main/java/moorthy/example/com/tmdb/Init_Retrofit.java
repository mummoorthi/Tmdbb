package moorthy.example.com.tmdb;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import moorthy.example.com.tmdb.Api_service.apiI_Interfaces;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by moorthy on 11/2/18.
 */

public class Init_Retrofit {

    public static apiI_Interfaces RetroInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(apiI_Interfaces.class);
    }



}
