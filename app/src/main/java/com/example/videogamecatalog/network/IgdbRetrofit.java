package com.example.videogamecatalog.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class IgdbRetrofit {
    private static Retrofit retrofit;

    private IgdbRetrofit(){}

    private static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();


    public static Retrofit getInstance(){
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api-v3.igdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
