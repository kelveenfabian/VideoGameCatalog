package com.example.videogamecatalog.network;

import com.example.videogamecatalog.model.Game;
import com.example.videogamecatalog.model.Genre;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IGDBService {

    @Headers({"Accept: application/json", "user-key:7c5e63c17740e6dd85f579098529cc1a"})
    @POST("genres")
    Call<List<Genre>> getGenre(@Query("fields") String fields, @Query("limit") String limit);

    @Headers({"Accept: application/json", "user-key:7c5e63c17740e6dd85f579098529cc1a"})
    @POST("games")
    Call<List<Game>> getGamesByGenre(@Body RequestBody requestBody);
}

