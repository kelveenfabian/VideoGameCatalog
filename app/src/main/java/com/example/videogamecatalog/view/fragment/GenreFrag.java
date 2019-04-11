package com.example.videogamecatalog.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.controller.GenreAdapter;
import com.example.videogamecatalog.model.Genre;
import com.example.videogamecatalog.network.IGDBApi;
import com.example.videogamecatalog.network.IGDBService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFrag
        extends Fragment
        implements SearchView.OnQueryTextListener {

    private RecyclerView rv;
    private List<Genre> genreList = new ArrayList<>();
    private FragmentListener listener;
    private GenreAdapter adapter;
    private SearchView genreSearchView;

    public GenreFrag() {
        // Required empty public constructor
    }

    public static GenreFrag newInstance() {
        return new GenreFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        getRetrofit();
        searchViewListener();
    }

    public void initializeViews(View view) {
        rv = view.findViewById(R.id.genre_rv);
        genreSearchView = view.findViewById(R.id.genre_searchview);
    }

    public void searchViewListener() {
        genreSearchView.setOnQueryTextListener(this);
    }

    public void getRetrofit() {
        IGDBApi.getService(IGDBService.class)
               .getGenre("*", "20")
               .enqueue(new Callback<List<Genre>>() {

                   @Override
                   public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                       //                       Log.d("TAG", "" + response.body().size()); //.get(0).getName());
                       //                       Log.d("TAG", call.request().toString());
                       //                       Log.d("TAG", response.toString());
                       genreList.addAll(response.body());
                       showGenreRecyclerView();

                   }

                   @Override
                   public void onFailure(Call<List<Genre>> call, Throwable t) {
                       Log.d("TAG", t.getMessage());
                       Toast.makeText(getContext(),
                                      "Cannot retrieve genre data at the moment. Please try again.",
                                      Toast.LENGTH_SHORT).show();
                   }
               });
    }
    public void showGenreRecyclerView() {
        adapter = new GenreAdapter(genreList, listener);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            listener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Genre> newGenreList = new ArrayList<>();

        for (Genre genre : genreList) {
            if (genre.getName().toLowerCase().startsWith(newText.toLowerCase())) {
                newGenreList.add(genre);
            }
        }
        adapter.setData(newGenreList);

        return false;
    }
}
