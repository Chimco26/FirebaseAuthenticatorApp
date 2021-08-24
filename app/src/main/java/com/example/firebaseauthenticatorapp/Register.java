package com.example.firebaseauthenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseauthenticatorapp.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText mEditFullName;
    private EditText mEditEmail;
    private EditText mEditPassword;
    private EditText mEditPhone;
    private Button mRegisterButton;
    private TextView mLoginMove;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private DatabaseReference mRealtimeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEditFullName = findViewById(R.id.full_name_register);
        mEditEmail = findViewById(R.id.email_register);
        mEditPassword = findViewById(R.id.password_register);
        mEditPhone = findViewById(R.id.phone_register);
        mRegisterButton = findViewById(R.id.button_register);
        mLoginMove = findViewById(R.id.textView3_register);
        progressBar = findViewById(R.id.progressBar_register);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        mRealtimeDatabase = FirebaseDatabase.getInstance().getReference();

        mRegisterButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email = mEditEmail.getText().toString();
            String password = mEditPassword.getText().toString();
            firebaseRegister(email, password);
        });

        mLoginMove.setOnClickListener(v -> startActivity(new Intent(Register.this, Login.class)));

    }

//    private  void saveToRealtimeDB(){
//        Map<String, Object> user = new HashMap<>();
//        user.put("full_name", mEditFullName.getText().toString());
//        user.put("email", mEditEmail.getText().toString());
//        user.put("phone", mEditPhone.getText().toString());
//
//        mRealtimeDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
//    }

    private void saveToFirebasDB() {
        User user = new User(mEditFullName.getText().toString(),
                mEditEmail.getText().toString(),
                mEditPhone.getText().toString(),
                FirebaseAuth.getInstance().getCurrentUser().getUid());

//        Map<String, Object> user = new HashMap<>();
//        user.put("full_name", mEditFullName.getText().toString());
//        user.put("email", mEditEmail.getText().toString());
//        user.put("phone", mEditPhone.getText().toString());

        db.collection("Users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .set(user)
                .addOnSuccessListener(documentReference ->
                        Toast.makeText(Register.this, "User Create", Toast.LENGTH_LONG).show())
                .addOnFailureListener(e ->
                        Toast.makeText(Register.this, "User NOT Create!!!", Toast.LENGTH_LONG).show());
    }

    private void firebaseRegister(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.GONE);
            if(task.isSuccessful()){
                Intent intent = new Intent(getApplicationContext(), Hello.class);
                startActivity(intent.putExtra("userUid", FirebaseAuth.getInstance().getCurrentUser().getUid()));
                saveToFirebasDB();
//                saveToRealtimeDB();
            } else {
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                Log.e("TAG", task.getException().getMessage());
                if(task.getException().getMessage() == "The email address is already in use by another account."){
                    Bundle bundle = new Bundle();
                    bundle.putString("email", mEditEmail.getText().toString());
                    Intent intent = new Intent(Register.this, Login.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
}