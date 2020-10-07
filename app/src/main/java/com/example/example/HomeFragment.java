package com.example.example;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class HomeFragment extends Fragment {

    Button mButtonTestRus;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = v.findViewById(R.id.progressBar_rus);
        progressBar.setProgress(MainActivity.getDefaultsInt("rus_level_progress", getActivity()));


        mButtonTestRus = v.findViewById(R.id.all_tests_rus);
        mButtonTestRus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ToTestActivity.class);
                startActivity(intent);

            }
        });


        return v;
    }

}