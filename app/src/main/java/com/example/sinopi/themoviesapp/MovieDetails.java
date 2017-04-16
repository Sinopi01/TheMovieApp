package com.example.sinopi.themoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sinopi.Models.MovieModels;
import com.squareup.picasso.Picasso;

/**
 * Created by sinopi on 4/15/17.
 */

public class MovieDetails extends AppCompatActivity {
    MovieModels movie;

    ImageView moviePosterView;
    TextView movieDetailsView;
    TextView popularityView;
    TextView releaseDateView;
    TextView movieTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);

        moviePosterView = (ImageView)findViewById(R.id.moviePoster);
        movieDetailsView = (TextView)findViewById(R.id.movieDetails);
        popularityView = (TextView)findViewById(R.id.popularity);
        releaseDateView = (TextView)findViewById(R.id.releaseDate);
        movieTitleView = (TextView)findViewById(R.id.movieTitle);

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movies");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Movie Details");

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185//"+movie.getMoviePoster())
                .placeholder(R.color.colorPrimary)
                .into(moviePosterView);

        movieTitleView.setText(movie.getMovieTitle());
        movieDetailsView.setText(movie.getMovieDetails());
        popularityView.setText(movie.getPopularity());
        releaseDateView.setText(movie.getReleaseDate());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
    }

}
