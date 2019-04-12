package com.example.videogamecatalog.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.videogamecatalog.R;
import com.example.videogamecatalog.model.Game;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFrag
        extends Fragment {


    public static final String NAME = "NAME";
    public static final String URL = "URL";
    public String url;
    private String name;
    private TextView nameView;
    private WebView webView;

    public DisplayFrag() {
        // Required empty public constructor
    }

    public static DisplayFrag newInstance(Game game){
        DisplayFrag displayFrag = new DisplayFrag();
        Bundle bundle = new Bundle();
        bundle.putString(NAME, game.getName());
        bundle.putString(URL, game.getUrl());
        displayFrag.setArguments(bundle);
        return  displayFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            name = getArguments().getString(NAME);
            url = getArguments().getString(URL);
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
        initializeViews(view);
        setViews();
    }

    public void initializeViews(View view){
        nameView = view.findViewById(R.id.game_name);
        webView = view.findViewById(R.id.game_website);
    }

    public void setViews(){
        nameView.setText(name);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

}

