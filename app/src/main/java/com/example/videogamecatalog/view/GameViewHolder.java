package com.example.videogamecatalog.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Game;

public class GameViewHolder
        extends RecyclerView.ViewHolder {

    private TextView gameTitle;

    public GameViewHolder(@NonNull View itemView) {
        super(itemView);
        gameTitle = itemView.findViewById(R.id.game_title);
    }

    public void onBind(Game game) {
        gameTitle.setText(game.getName());
    }
}
