package moorthy.example.com.tmdb.Api_service;

import java.util.Map;
import java.util.Map;

import moorthy.example.com.tmdb.Model.MovieListExample;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
/**
 * Created by moorthy on 11/2/18.
 */

public interface apiI_Interfaces {

    @GET("discover/movie")
    Call<MovieListExample> getMoviesList(@QueryMap Map<String, Object> options);

}
