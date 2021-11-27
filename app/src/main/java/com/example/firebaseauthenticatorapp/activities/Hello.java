package com.example.firebaseauthenticatorapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.models.Users;
import com.example.firebaseauthenticatorapp.views.users.UsersAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Hello extends AppCompatActivity {

    private String mUserUid;
    private FloatingActionButton mLogoutButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mLogoutButton = findViewById(R.id.logout_button);
        mAuth = FirebaseAuth.getInstance();


        Bundle bundle = getIntent().getExtras();
        mUserUid = bundle.getString("userUid");
        Log.e("TAG", ""+ mUserUid);


        mLogoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(Hello.this, MainActivity.class));
        });
    }


}