package com.example.videogamecatalog.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch(itemId){
            case R.id.github_item:
                String githubWebAddress = getString(R.string.github_web_text);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubWebAddress));
                startActivity(intent);
                return true;
            case R.id.linkedin_item:
                String linkedinWebAddress = getString(R.string.linkedin_web_text);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedinWebAddress));
                startActivity(intent1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSplashFrag() {
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.catalog_frag, SplashFrag.newInstance())
                                   .commit();
    }

    @Override
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
