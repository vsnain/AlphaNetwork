package com.example.alphanetwork.Dark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alphanetwork.R;


public class DarkGlobalWallFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "DarkGlobalFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dark_global_wall ,container, false);

        return view;
    }
}