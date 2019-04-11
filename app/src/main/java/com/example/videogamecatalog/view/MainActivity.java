package com.example.videogamecatalog.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Game;
import com.example.videogamecatalog.view.fragment.DisplayFrag;
import com.example.videogamecatalog.view.fragment.FragmentListener;
import com.example.videogamecatalog.view.fragment.GameFrag;
import com.example.videogamecatalog.view.fragment.GenreFrag;
import com.example.videogamecatalog.view.fragment.SplashFrag;

import java.util.List;


public class MainActivity
        extends AppCompatActivity
        implements FragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSplashFrag();
        new Handler().postDelayed(this::splashToGenreFrag, 1500);

    }


    public void showSplashFrag() {
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.catalog_frag, SplashFrag.newInstance())
                                   .commit();
    }

    public void splashToGenreFrag() {
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.catalog_frag, GenreFrag.newInstance())
                                   .commit();
    }

    @Override
    public void genreToGamesFrag(List<Game> gamesList) {
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.catalog_frag, GameFrag.newInstance(gamesList))
                                   .addToBackStack(null)
                                   .commit();
    }

    @Override
    public void displayGameInfoFrag(Game game) {
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.catalog_frag, DisplayFrag.newInstance(game))
                                   .addToBackStack(null)
                                   .commit();
    }



}
