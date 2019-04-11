package com.example.videogamecatalog.view.fragment;

import com.example.videogamecatalog.model.Game;

import java.util.List;

public interface FragmentListener {

    void genreToGamesFrag(List<Game> gamesList);

    void displayGameInfoFrag(Game game);
}
