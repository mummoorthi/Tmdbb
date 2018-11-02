package moorthy.example.com.tmdb;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import moorthy.example.com.tmdb.Adapter.popularMovie_list_adapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class popular_movies_screen extends AppCompatActivity {

    RecyclerView objRecyler_popularMovieList;
    ProgressDialog progress;
    popularMovie_list_adapter movieList_adapter;
    private int lastVisibleItem, totalItemCount,totalcount,page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies_screen);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progress = new ProgressDialog(this);
        getMoviesList();
    }


    private void getMoviesList() {
        Map<String,Object> objParams=new HashMap<>();
        objParams.put("api_key",getResources().getString(R.string.api_key));
        objParams.put("sort_by","popularity.desc");
        objParams.put("page",page);

        Call<ResponseBody> call = Init_Retrofit.RetroInstance().getMoviesList(objParams);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    System.out.println("check_interface1");

                    try {
                        System.out.println("check_interface"+response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        {

    }
}
}
