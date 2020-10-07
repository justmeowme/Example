package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ToTestActivity extends AppCompatActivity {


    Button mB1;
    TextView pType1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_test);

        pType1 = findViewById(R.id.percent1);

        try{
            if (MainActivity.getDefaultsInt("rus_task1", ToTestActivity.this)==-1){
                pType1.setText("0%");
            }
            else{
                pType1.setText(String.valueOf(MainActivity.getDefaultsInt("rus_task1", ToTestActivity.this))+"%");
            }

        } catch (Exception e){

        }


        mB1 = findViewById(R.id.button_type1);
        mB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasicTestActivity.taskType = 1;
                Intent intent = new Intent(ToTestActivity.this, BasicTestActivity.class);
                startActivity(intent);
            }
        });

    }
}