package com.example.videogamecatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.videogamecatalog.model.VideoGame;
import com.example.videogamecatalog.network.IgdbRetrofit;
import com.example.videogamecatalog.network.IgdbService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private List<VideoGame> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRetrofit();
    }

    public void getRetrofit() {
        Retrofit retrofit = IgdbRetrofit.getInstance();
        retrofit.create(IgdbService.class)
                .getPlatform("*")
                .enqueue(new Callback<List<VideoGame>>() {
                    @Override
                    public void onResponse(Call<List<VideoGame>> call, Response<List<VideoGame>> response) {
                        Log.d("TAG", "" + response.body().size()); //.get(0).getName());
                        Log.d("TAG", call.request().toString());
                        Log.d("TAG", response.toString());
                    }

                    @Override
                    public void onFailure(Call<List<VideoGame>> call, Throwable t) {
                        Log.d("TAG", t.getMessage());
                    }
                });
    }
}
