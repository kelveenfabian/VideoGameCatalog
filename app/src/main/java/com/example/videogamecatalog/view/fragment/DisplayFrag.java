package com.example.videogamecatalog.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Game;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFrag
        extends Fragment {


    public static final String NAME = "NAME";
    public static final String SUMMARY = "SUMMARY";
    private String name;
    private String summary;
    private TextView nameView;
    private TextView summaryView;

    public DisplayFrag() {
        // Required empty public constructor
    }

    public static DisplayFrag newInstance(Game game){
        DisplayFrag displayFrag = new DisplayFrag();
        Bundle bundle = new Bundle();
        bundle.putString(NAME, game.getName());
        bundle.putString(SUMMARY, game.getSummary());
        displayFrag.setArguments(bundle);
        return  displayFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            name = getArguments().getString(NAME);
            summary = getArguments().getString(SUMMARY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameView = view.findViewById(R.id.game_name);
        summaryView = view.findViewById(R.id.game_summary);

        nameView.setText(name);
        summaryView.setText(summary);
    }
}
