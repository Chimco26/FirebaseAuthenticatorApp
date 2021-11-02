package com.example.firebaseauthenticatorapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseauthenticatorapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText mEditEmail;
    private EditText mEditPassword;
    private Button mLoginButton;
    private TextView mRegisterMove;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEditEmail = findViewById(R.id.email_login);
        mEditPassword = findViewById(R.id.password_login);
        mLoginButton = findViewById(R.id.button_login);
        mRegisterMove = findViewById(R.id.textView3_login);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar_login);

        if(getIntent().hasExtra("email")){
            mEditEmail.setText(getIntent().getExtras().getString("email", ""));
        }

        mLoginButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email = mEditEmail.getText().toString();
            String password = mEditPassword.getText().toString();
            firebaseRegister(email,password);
        });

        mRegisterMove.setOnClickListener(v -> startActivity(new Intent(Login.this, Register.class)));

    }

    private void firebaseRegister(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            progressBar.setVisibility(View.GONE);
            if(task.isSuccessful()){
                Toast.makeText(Login.this, "Successfull Login!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Hello.class);
                startActivity(intent.putExtra("userUid", FirebaseAuth.getInstance().getCurrentUser().getUid()));
            } else {
                Toast.makeText(Login.this, "Error Login!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}