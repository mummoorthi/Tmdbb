package moorthy.example.com.tmdb;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;

import java.math.BigDecimal;
import java.util.List;

import moorthy.example.com.tmdb.Moviedetails.MovieDetailsExample;
import moorthy.example.com.tmdb.Moviedetails.MoviewdetailsGener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviewDetailPage extends AppCompatActivity implements View.OnClickListener{

    AppCompatImageView detailImage,backmovielist;
    AppCompatTextView toolbartitle,movitedetails,minutes,moviedate,moviewreview,moviewaddress,movielanguage,moviebudget,movieprice,movierevenue,movieprice1;
    String id=null,title=null;

    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moview_detail_page);
        progress = new ProgressDialog(this);
        getData();
        toolbartitle = findViewById(R.id.toolbartitle);
        backmovielist = findViewById(R.id.backmovielist);
        backmovielist.setOnClickListener(this);
        detailImage = findViewById(R.id.moviewdetailImage);
        movitedetails = findViewById(R.id.moviedetails);
        minutes = findViewById(R.id.minutes);
        moviedate = findViewById(R.id.date);
        moviewreview = findViewById(R.id.review);
        moviewaddress = findViewById(R.id.movieAddress);
        movielanguage = findViewById(R.id.moiewStatus);
        moviebudget = findViewById(R.id.budget);
        movieprice = findViewById(R.id.doller);
        movierevenue = findViewById(R.id.revenue);
        movieprice1 = findViewById(R.id.doller1);
getmoviedetailApi();

    }

    private void getmoviedetailApi() {

        if (id!=null){
            showLoad();
            Call<MovieDetailsExample> moviedetais = Init_Retrofit.RetroInstance().getMovieDetail(id,getResources().getString(R.string.api_key));

            moviedetais.enqueue(new Callback<MovieDetailsExample>() {

                @Override
                public void onResponse(@NonNull Call<MovieDetailsExample> call, @NonNull Response<MovieDetailsExample> response) {
                    if (response.isSuccessful()){
                        if(isLoading()){
                            hideLoading();
                        }
                        MovieDetailsExample moviedetails=response.body();
                        if (moviedetails!=null){
                            if (moviedetails.getOverview()!=null){
                                movitedetails.setText(moviedetails.getOverview());
                            }
                            if (moviedetails.getTitle()!=null){
                                toolbartitle.setText(moviedetails.getTitle());

                            }
                            if (moviedetails.getPosterPath()!=null){
                                String imageUrl="https://image.tmdb.org/t/p/w500/"+moviedetails.getPosterPath();
                                Glide.with(MoviewDetailPage.this).load(imageUrl).into(detailImage);
                            }
                            if (moviedetails.getRuntime()!=null){
                                String runTime=moviedetails.getRuntime().toString()+" Minutes";
                                minutes.setText(runTime);
                            }
                            if (moviedetails.getReleaseDate()!=null){
                                moviedate.setText(Common.convertDate(moviedetails.getReleaseDate()));
                            }


                            if (moviedetails.getVoteAverage()!=null){
                                String rating=moviedetails.getVoteAverage().toString();
                                moviewreview.setText(rating);
                            }
                            if (moviedetails.getGenres()!= null){
                                moviewaddress.setText(getGeneres(moviedetails.getGenres()));
                            }
                            if (moviedetails.getOriginalLanguage()!=null){
                                movielanguage.setText(moviedetails.getOriginalLanguage());
                            }
                            if (moviedetails.getBudget()!=null){
                                movieprice.setText(getMillion(moviedetails.getBudget()));
                            }
                            if (moviedetails.getBudget()!=null){
                                movieprice1.setText(getMillion(moviedetails.getRevenue()));
                            }

                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieDetailsExample> call, @NonNull Throwable t) {
                    if(isLoading()){
                        hideLoading();
                    }
                }
            });
        }

    }

    private void getData() {

        if (getIntent()!=null){
            if (getIntent().getExtras()!=null){
                id=getIntent().getExtras().getString("id");
                title=getIntent().getExtras().getString("title");
            }
        }
    }

    private String getMillion(BigDecimal decimal){
        return "$"+decimal.divide(BigDecimal.valueOf(1000000),0,1).toString()+" Millions";
    }
    private String getGeneres(List<MoviewdetailsGener> aryGeneres){
        if (aryGeneres!=null){
            if (aryGeneres.size()>0){
                StringBuilder objBuilder=new StringBuilder();
                for (int i=0;i<aryGeneres.size();i++){
                    if (i!=0){
                        objBuilder.append(", ");
                    }
                    objBuilder.append(aryGeneres.get(i).getName());
                }
                return objBuilder.toString();
            }
        }
        return "";
    }

    public void showLoad() {
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress.setCanceledOnTouchOutside(false);
        progress.setIndeterminate(true);
        progress.show();
        progress.setContentView(R.layout.progress);
    }
    public void hideLoading() {
        if (progress != null) {
            progress.dismiss();
        }
    }
    public Boolean isLoading() {
        if (progress != null) {
            return progress.isShowing();
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backmovielist:
                finish();
                break;
        }
    }
}
