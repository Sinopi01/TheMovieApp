package com.example.sinopi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sinopi.Models.MovieModels;
import com.example.sinopi.themoviesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sinopi on 4/15/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{


    private Context context;
    private ArrayList<MovieModels> mMovies;

    public MoviesAdapter(Context context)
    {
        this.context = context;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_list_item,parent,false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieModels movie = mMovies.get(position);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185//"+movie.getMoviePoster())
                .placeholder(R.color.colorPrimary)
                .error(R.drawable.error)
                .into(holder.moviePosterView);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null)
            return 0;
        return this.mMovies.size();
    }


    public void swapDataset(ArrayList<MovieModels>movies)
    {
        if(movies == null)
            return;
        this.mMovies = movies;

        this.notifyDataSetChanged();
    }
    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePosterView;

        public MovieViewHolder(View itemView) {
            super(itemView);

            moviePosterView = (ImageView)itemView.findViewById(R.id.moviePoster);
        }
    }
}

