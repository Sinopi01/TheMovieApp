package com.example.sinopi.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sinopi on 4/15/17.
 */

public class Services {

        private static final String BASE_API_URL = "https://api.themoviedb.org/3/movie/";
        private static HttpLoggingInterceptor loggingInterceptor = new
                HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY);
        private static OkHttpClient.Builder httpClient = new OkHttpClient.
                Builder().addInterceptor(loggingInterceptor);
        private static Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build());

        private static Retrofit retrofit = builder.build();

        public static <T> T createService(Class<T> serviceClass)
        {
            return retrofit.create(serviceClass);
        }

}
