package com.example.videogamecatalog.view.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.controller.GameAdapter;
import com.example.videogamecatalog.model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFrag
        extends Fragment {

    private ArrayList<Game> gameList = new ArrayList<>();
    public static final String GAMELIST = "GAMELIST";
    private RecyclerView rv;
    private GameAdapter adapter;

    public GameFrag() {
        // Required empty public constructor
    }

    public static GameFrag newInstance(List<Game> gamesList) {
        GameFrag gameFrag = new GameFrag();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(GAMELIST, (ArrayList<? extends Parcelable>) gamesList);
        gameFrag.setArguments(bundle);
        return gameFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            gameList = getArguments().getParcelableArrayList(GAMELIST);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        showGameRecyclerView(gameList);
    }

    public void showGameRecyclerView(List<Game> gameList) {
        adapter = new GameAdapter(gameList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void initializeViews(View view) {
        rv = view.findViewById(R.id.game_rv);
    }
}
