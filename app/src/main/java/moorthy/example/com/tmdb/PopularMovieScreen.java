package moorthy.example.com.tmdb;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import moorthy.example.com.tmdb.Adapter.PopularMovieListAdapter;
import moorthy.example.com.tmdb.Model.MovieListExample;
import moorthy.example.com.tmdb.Model.MovieListResult;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovieScreen extends AppCompatActivity {


    RecyclerView recyclerView;
    ProgressDialog progress;
    PopularMovieListAdapter movielistadapter;
    private int lastVisibleItem, totalItemCount, totalcount, page = 1;
    List<MovieListResult> movielist;
    private LinearLayoutManager linearmanger;
    public Boolean isloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie_screen);
        progress = new ProgressDialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setAdapter();
        getMovies();

    }

    private void setAdapter() {
        movielist = new ArrayList<>();
        movielistadapter = new PopularMovieListAdapter(this, movielist);
        recyclerView = findViewById(R.id.Id_movie_list_recyler);
        linearmanger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearmanger);
        recyclerView.setAdapter(movielistadapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    private void getMovies() {

        Map<String, Object> objParams = new HashMap<>();
        objParams.put("api_key", getResources().getString(R.string.api_key));
        objParams.put("sort_by", "popularity.desc");
        objParams.put("page", page);
        showLoad();
        Call<MovieListExample> call = Init_Retrofit.RetroInstance().getMoviesList(objParams);
        call.enqueue(new Callback<MovieListExample>() {
            @Override
            public void onResponse(Call<MovieListExample> call, Response<MovieListExample> response) {
                if (response.isSuccessful()) {
                    if (isLoading()) {
                        hideLoading();
                    }
                    MovieListExample objBody = response.body();
                    if (objBody != null) {
                        if (objBody.getResult() != null) {
                            if (objBody.getResult().size() > 0) {
                                movielist.addAll(objBody.getResult());
                                movielistadapter.notifyDataSetChanged();
                                totalcount = objBody.getTotalResults();
                                isloading = false;
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieListExample> call, Throwable t) {
                if (isLoading()) {
                    hideLoading();
                }
            }

        });

    }




    public boolean isLoading() {
        if (progress != null) {
            return progress.isShowing();
        }
        return false;
    }

    public void hideLoading(){
        if (progress != null) {
            progress.dismiss();
        }
    }

    public void showLoad() {
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress.setCanceledOnTouchOutside(false);
        progress.setIndeterminate(true);
        progress.show();
        progress.setContentView(R.layout.progress);
    }
}
