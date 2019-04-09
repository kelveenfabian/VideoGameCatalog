package com.example.videogamecatalog.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Game;
import com.example.videogamecatalog.view.GameViewHolder;

import java.util.List;

public class GameAdapter
        extends RecyclerView.Adapter<GameViewHolder> {

    private List<Game> gamesList;

    public GameAdapter(List<Game> gamesList) {
        Log.d("TAG", gamesList.get(0).getName());
        this.gamesList = gamesList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                                  .inflate(R.layout.game_itemview, viewGroup, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.onBind(gamesList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "" + gamesList.size());
        return gamesList.size();
    }
}
