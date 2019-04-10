package com.example.videogamecatalog.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Game;
import com.example.videogamecatalog.model.Genre;
import com.example.videogamecatalog.network.IGDBApi;
import com.example.videogamecatalog.network.IGDBService;
import com.example.videogamecatalog.view.fragment.FragmentListener;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreViewHolder
        extends RecyclerView.ViewHolder {

    public static final String BASE_QUERY = "fields *;\n";
    private TextView genreTitle;

    public GenreViewHolder(@NonNull View itemView) {
        super(itemView);
        genreTitle = itemView.findViewById(R.id.genre_title);
    }

    public void onBind(Genre genre, FragmentListener listener) {
        genreTitle.setText(genre.getName());
        genreTitle.setOnClickListener(v -> {
            //            // START QUERY FORMATION
            //            String finalQuery = BASE_QUERY + "where genres = %s;";
            //            boolean needsOffset = true;
            ////            if (needsOffset) {
            ////                finalQuery += "offset = %d;";
            ////            }
            //            int offset = 50;
            //            String content = String.format(finalQuery, genre.getId());
            //            // END QUERY FORMATION

            RequestBody requestBody = RequestBody.create(null,
                                                         BASE_QUERY +
                                                         "limit " +
                                                         25 +
                                                         ";\n" +
                                                         "where genres = " +
                                                         genre.getId() +
                                                         ";" + "sort name asc;" );

            IGDBApi.getService(IGDBService.class)
                   .getGamesByGenre(requestBody)
                   .enqueue(new Callback<List<Game>>() {

                       @Override
                       public void onResponse(Call<List<Game>> call,
                                              Response<List<Game>> response) {
                           List<Game> allDaGames = response.body();
                           if (allDaGames != null && !allDaGames.isEmpty()) {
                               listener.GenreToGamesFrag(allDaGames);
                               Toast.makeText(itemView.getContext(),
                                              "id: " +
                                              genre.getId() +
                                              "\n\n" +
                                              allDaGames.get(0).getName(),
                                              Toast.LENGTH_LONG).show();
                           } else {
                               Toast.makeText(itemView.getContext(),
                                              "No games found!",
                                              Toast.LENGTH_LONG).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<List<Game>> call, Throwable t) {
                           Toast.makeText(itemView.getContext(),
                                          "Shit, we failed!",
                                          Toast.LENGTH_LONG).show();
                       }
                   });
        });
    }
}