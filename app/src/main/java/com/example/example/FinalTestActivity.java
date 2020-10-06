package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FinalTestActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textViewResult;
    Button mButtonAgain, mButtonMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_test);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(BasicTestActivity.mFinalResult*10);

        textViewResult = findViewById(R.id.textViewResult);
        textViewResult.setText(String.valueOf(BasicTestActivity.mFinalResult)+"/10");

        mButtonAgain = findViewById(R.id.button_again);
        mButtonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasicTestActivity.mFinalResult = 0;
                Intent intent = new Intent(FinalTestActivity.this, BasicTestActivity.class);
                startActivity(intent);
            }
        });

        mButtonMain = findViewById(R.id.button_main);
        mButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalTestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}