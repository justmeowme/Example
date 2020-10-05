package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class IntroduceActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    TextView mText;
    ProgressBar mBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        mText = findViewById(R.id.textView);
        mBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("user").document(userID);
        documentReference.addSnapshotListener(IntroduceActivity.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mText.setText("Привет, " + documentSnapshot.getString("name") + "!");
            }
        });

        int delay = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroduceActivity.this, MainActivity.class));
                finish();
            }
        }, delay*1000);



    }
}