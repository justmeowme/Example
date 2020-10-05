package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.lessons:
                        fragment = new LessonsFragment();
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                return true;
            }
        });

    }
}