package com.example.firebaseauthenticatorapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    private TextView helloTextView;
    private String mUserUid;
    private FirebaseFirestore db;
    private Button mLogoutButton;
    private FirebaseAuth mAuth;
    private List<Users> mListUsers;
    private RecyclerView mListUsersRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        mAuth = FirebaseAuth.getInstance();

        mLogoutButton = findViewById(R.id.logout_button);
        helloTextView = findViewById(R.id.hello_uid);
        mListUsersRecycler = findViewById(R.id.recyclerView_users);

        db = FirebaseFirestore.getInstance();
        Bundle bundle = getIntent().getExtras();
        mUserUid = bundle.getString("userUid");
        Log.e("TAG", ""+ mUserUid);

        initRecyclerView();

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

    public void initListUsers(){
        mListUsers = new ArrayList<>();
        db.collection("Users").get().addOnCompleteListener(task -> {
            if(task.isComplete()){
                for(QueryDocumentSnapshot doc : task.getResult()){
                    mListUsers.add(doc.toObject(Users.class));
                }
            }
            for(Users user : mListUsers){
                Log.e("TAG", user.getFullName());
            }
        });
    }

    public void initRecyclerView(){
        initListUsers();
        mListUsersRecycler.setAdapter(new UsersAdapter(mListUsers));
    }
}