package moorthy.example.com.tmdb.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by moorthy on 11/2/18.
 */

public class popularMovie_list_adapter extends RecyclerView.Adapter<popularMovie_list_adapter.MyviewHolder> {
    @NonNull
    @Override
    public popularMovie_list_adapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull popularMovie_list_adapter.MyviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        public MyviewHolder(View itemView) {
            super(itemView);
        }
    }
}
