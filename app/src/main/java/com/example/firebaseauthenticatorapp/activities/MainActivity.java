package com.example.firebaseauthenticatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.firebaseauthenticatorapp.R;
import com.example.firebaseauthenticatorapp.fragments.DiscussionsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    String mUserUid;
    private FirebaseDatabase realtimeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        realtimeDB = FirebaseDatabase.getInstance();
      //  saveToRealtimeDB();

        if(user != null){
            mUserUid = user.getUid();
            startActivity(new Intent(MainActivity.this, Hello.class)
                    .putExtra("userUid", mUserUid));
        } else {
            startActivity(new Intent(MainActivity.this, Register.class));
        }


    }
    private void saveToRealtimeDB(){
        DatabaseReference myRef = realtimeDB.getReference("message1");
        myRef.push().setValue("hello word1!");
    }
}