package com.example.sinopi.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sinopi on 4/15/17.
 */
public class MovieModels implements Parcelable {

    public static final Parcelable.Creator<MovieModels> CREATOR = new Parcelable.Creator<MovieModels>()
    {

        @Override
        public MovieModels createFromParcel(Parcel parcel) {
            return new MovieModels(parcel);
        }

        @Override
        public MovieModels[] newArray(int size) {
            return new MovieModels[size];
        }
    };
    @SerializedName("poster_path")
    private String moviePoster;
    @SerializedName("original_title")
    private String movieTitle;
    @SerializedName("overview")
    private String movieDetails;
    @SerializedName("vote_average")
    private String popularity;
    @SerializedName("release_date")
    private String releaseDate;

    private MovieModels(Parcel in)
    {
        this.moviePoster = in.readString();
        this.movieTitle = in.readString();
        this.movieDetails = in.readString();
        this.popularity = in.readString();
        this.releaseDate = in.readString();
    }

    public String getMoviePoster() {return moviePoster;}

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(String movieDetails) {
        this.movieDetails = movieDetails;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(moviePoster);
        dest.writeString(movieTitle);
        dest.writeString(movieDetails);
        dest.writeString(popularity);
        dest.writeString(releaseDate);
    }

    public static class SearchResult {

        private ArrayList<MovieModels> results;

        public ArrayList<MovieModels> getItems(){return results;}
    }
}

