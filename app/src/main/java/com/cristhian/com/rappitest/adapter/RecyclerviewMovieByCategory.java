package com.cristhian.com.rappitest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cristhian.com.rappitest.R;
import com.cristhian.com.rappitest.model.MovieResults;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerviewMovieByCategory extends RecyclerView.Adapter<RecyclerviewMovieByCategory.RecyclerViewHolder> {

    private List<MovieResults.ResultsBean> movies;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerviewMovieByCategory(Context context, List<MovieResults.ResultsBean> movies) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerviewMovieByCategory.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewMovieByCategory.RecyclerViewHolder viewHolder, int i) {

        String image = movies.get(i).getPoster_path();
        Picasso.get().load("http://image.tmdb.org/t/p/w185/"+image).resize(320,580).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.image);
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image)
        ImageView image;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerviewMovieByCategory.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }
}