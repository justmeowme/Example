package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    String userID;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Register new user
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        final EditText mEmail = findViewById(R.id.editTextEmail);
        final EditText mPassword = findViewById(R.id.editTextPassword);
        final EditText mName = findViewById(R.id.editTextName);
        Button mRegisterButton = findViewById(R.id.buttonRegister);


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String name = mName.getText().toString();

                if ((TextUtils.isEmpty(email)) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "Не все поля заполнены", Toast.LENGTH_SHORT).show();
                } else if (password.length()<6) {
                    Toast.makeText(RegisterActivity.this, "Длина пароля должна быть больше 6 символов", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //Store user data
                                userID = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("user").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name);
                                user.put("email", email);
                                user.put("password", password);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //null
                                    }
                                });

                                Intent intent = new Intent(RegisterActivity.this, IntroduceActivity.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(RegisterActivity.this, "Что-то пошло не так. Попробуйте снова", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });


        }
}