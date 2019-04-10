package com.example.videogamecatalog.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Game;
import com.example.videogamecatalog.view.fragment.DisplayFrag;
import com.example.videogamecatalog.view.fragment.FragmentListener;
import com.example.videogamecatalog.view.fragment.GameFrag;
import com.example.videogamecatalog.view.fragment.GenreFrag;

import java.util.List;


public class MainActivity
        extends AppCompatActivity
        implements FragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showGenreFragment();
    }


    public void showGenreFragment() {
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.catalog_frag, GenreFrag.newInstance())
                                   .commit();
    }

    @Override
    public void GenreToGamesFrag(List<Game> gamesList) {
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.catalog_frag, GameFrag.newInstance(gamesList))
                                   .addToBackStack(null)
                                   .commit();
    }

    @Override
    public void DisplayGameInfoFrag(Game game) {
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.catalog_frag, DisplayFrag.newInstance(game))
                                   .addToBackStack(null)
                                   .commit();
    }



}
