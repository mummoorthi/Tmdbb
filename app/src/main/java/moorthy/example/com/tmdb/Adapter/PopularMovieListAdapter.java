package moorthy.example.com.tmdb.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import moorthy.example.com.tmdb.Model.MovieListResult;
import moorthy.example.com.tmdb.PopularMovieScreen;
import moorthy.example.com.tmdb.R;

/**
 * Created by moorthy on 11/2/18.
 */

public class PopularMovieListAdapter extends RecyclerView.Adapter<PopularMovieListAdapter.MyviewHolder>{

    private Activity activity;
    private List<MovieListResult> data;
    //private Interface_Adap objInterAdap;


    public PopularMovieListAdapter(Activity popularMovieScreen, List<MovieListResult> movielist) {
        this.activity=popularMovieScreen;
        this.data=movielist;
    }

    @NonNull
    @Override
    public PopularMovieListAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_movie_list, parent, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieListAdapter.MyviewHolder holder, int position) {

        if (data.get(position)!=null){
            if (data.get(position).getPosterPath()!=null){
                String imageUrl="https://image.tmdb.org/t/p/w500/"+data.get(position).getPosterPath();
                Glide.with(activity).load(imageUrl).into(holder.movieimage);
            }
            if (data.get(position).getTitle()!=null){
                holder.moviewtitle.setText(data.get(position).getTitle());
            }
            if (data.get(position).getOverview()!=null){
                holder.moviedescriptions.setText(data.get(position).getOverview());
            }
            if (data.get(position).getVoteAverage()!=null){
                String average=data.get(position).getVoteAverage().toString();
                holder.movierating.setText(average);
            }
            if (data.get(position).getReleaseDate()!=null){
                holder.moviedate.setText(convertDate(data.get(position).getReleaseDate()));
            }
            if (data.get(position).getOriginalLanguage()!=null){
                holder.moviestatus.setText(data.get(position).getOriginalLanguage());
            }
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView movieimage;
        AppCompatTextView moviewtitle, moviedescriptions, movierating, moviedate, moviestatus;
        ConstraintLayout movieparent;

        public MyviewHolder(View itemView) {
            super(itemView);
            movieimage = itemView.findViewById(R.id.movieimage);
            moviewtitle = itemView.findViewById(R.id.movieTitle);
            moviedescriptions = itemView.findViewById(R.id.moviedescription);
            movierating = itemView.findViewById(R.id.review);
            moviedate = itemView.findViewById(R.id.movieDate);
            moviestatus = itemView.findViewById(R.id.moiewStatus);
            movieparent = itemView.findViewById(R.id.movieparent);
        }
    }

    public static String convertDate(String objDate){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date;
        try {
            date = fmt.parse(objDate);
            SimpleDateFormat format_day = new SimpleDateFormat("dd",Locale.getDefault());
            SimpleDateFormat format_year = new SimpleDateFormat(" MMMM yyyy",Locale.getDefault());
            String day=format_day.format(date);
            String suffix=getDayOfMonthSuffix(Integer.parseInt(day));
            String rem_Date=format_year.format(date);
            return day+suffix+" "+rem_Date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    private static String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }
}
