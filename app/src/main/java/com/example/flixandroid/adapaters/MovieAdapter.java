package com.example.flixandroid.adapaters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixandroid.R;
import com.example.flixandroid.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter (Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    // involves inflating a layout for XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "OnCreateViewHolder");
        View movieView =  LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // involves populating data into the item thru viewholder
    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "OnBindViewHolder " + position);
        // get the movie at the passed in position
        Movie movie = movies.get(position);
        //Bind the movie data into the view holder
        holder.bind(movie);
    }

    // returns the total count of items in the list
    @Override
    public int getItemCount(){
       return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //if photo is in landscape, then imageUrl =  backdrop image.
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            }
            else {
                // else imageUrl = poster image
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);
        }
    }
}
