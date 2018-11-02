package moorthy.example.com.tmdb.Api_service;

import java.util.Map;

import moorthy.example.com.tmdb.Moviedetails.MovieDetailsExample;
import moorthy.example.com.tmdb.Movielist.MovieListExample;
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

    @GET("movie/{ID}")
    Call<MovieDetailsExample> getMovieDetail(@Path(value = "ID", encoded = true) String ID, @Query("api_key") String api_key);


}
