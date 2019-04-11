package com.example.videogamecatalog.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Genre;
import com.example.videogamecatalog.view.GenreViewHolder;
import com.example.videogamecatalog.view.fragment.FragmentListener;

import java.util.List;

public class GenreAdapter
        extends RecyclerView.Adapter<GenreViewHolder> {

    private List<Genre> genreList;
    private FragmentListener listener;

    public GenreAdapter(List<Genre> genreList, FragmentListener listener) {
        this.genreList = genreList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                                  .inflate(R.layout.genre_itemview, viewGroup, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder header, int position) {
        header.onBind(genreList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return genreList.size();
    }

    public void setData(List<Genre> genreList) {
        this.genreList = genreList;
        notifyDataSetChanged();
    }
}
