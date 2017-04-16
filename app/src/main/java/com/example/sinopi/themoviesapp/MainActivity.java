package com.example.sinopi.themoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sinopi.Adapter.MoviesAdapter;
import com.example.sinopi.Models.MovieModels;
import com.example.sinopi.Network.NetworkRequest;
import com.example.sinopi.Network.Services;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ClickActivator.OnItemClickListener {

    private final String API_KEY = "api key here"; //pass in the api key here
    RecyclerView mMoviesRecyclerView;
    MoviesAdapter moviesAdapter;
    ArrayList<MovieModels> movies;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        movies = null;

        moviesAdapter = new MoviesAdapter(this);

        mMoviesRecyclerView = (RecyclerView)findViewById(R.id.movie_list);
        mMoviesRecyclerView.setHasFixedSize(true);
        mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mMoviesRecyclerView.setAdapter(moviesAdapter);



       mMoviesRecyclerView.addOnItemTouchListener(new ClickActivator(this,this));

        populateData(1);

    }



    private  void setMoviePosters(ArrayList<MovieModels>movies)
    {
        this.movies = movies;
        moviesAdapter.swapDataset(movies);
    }

    private void populateData(int option)
    {
        //show the progress bar
        progressBar.setVisibility(View.VISIBLE);

        NetworkRequest httpClient = Services.createService(NetworkRequest.class);

        Call<MovieModels.SearchResult> call = null;

        if( option == 1)
            call = httpClient.getPopularMovies(API_KEY);
        else if(option == 2)
            call = httpClient.getTopRatedMovies(API_KEY);
        else
            throw new IllegalArgumentException("wrong query type supplied");

        //runs on a background thread and gets the data using the API
        call.enqueue(new Callback<MovieModels.SearchResult>() {
            @Override
            public void onResponse(Call<MovieModels.SearchResult> call,Response<MovieModels.SearchResult> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if(response.isSuccessful()) {
                    setMoviePosters(response.body().getItems());
                }else {
                    Toast.makeText(MainActivity.this, "failed to get movies now", Toast.LENGTH_LONG)
                            .show();
                }
            }
            @Override
            public void onFailure(Call<MovieModels.SearchResult> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this," failed to get movies now",Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_sort_popular:
                populateData(1);
                break;
            case R.id.action_sort_toprated:
                populateData(2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }



    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, MovieDetails.class);
        intent.putExtra("movies",movies.get(position));
        startActivity(intent);
        overridePendingTransition(R.anim.slide__in_right, R.anim.slide_out_right);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

}
