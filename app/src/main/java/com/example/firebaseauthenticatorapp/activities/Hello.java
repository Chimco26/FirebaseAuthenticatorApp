package com.example.firebaseauthenticatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.firebaseauthenticatorapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Hello extends AppCompatActivity {

    private TextView helloTextView;
    private String mUserUid;
    private FirebaseFirestore db;
    private Button mLogoutButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mAuth = FirebaseAuth.getInstance();
        mLogoutButton = findViewById(R.id.logout_button);
        helloTextView = findViewById(R.id.hello_uid);
        db = FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        mUserUid = bundle.getString("userUid");
        Log.e("TAG", ""+ mUserUid);

        DocumentReference docRef = db.collection("Users").document(mUserUid);

        docRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                helloTextView.setText("Hello " + document.getString("fullName") + "!!!");
            }
        });

        mLogoutButton.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(Hello.this, Login.class));
        });
    }
}