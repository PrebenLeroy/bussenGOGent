package com.example.bussysteemgogent.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bussysteemgogent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by prebe on 29/01/2018.
 */

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener {

    ConstraintLayout full;
    Button login;
    EditText email, password;
    private FirebaseAuth mAuth;
    private String emailString, passwordString;
    private static String TAG = "BUSSENGOGENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        this.full = findViewById(R.id.full);
        full.setBackgroundColor(Color.parseColor("#bbeebb"));

        this.email = (EditText) findViewById(R.id.editText);

        this.password = (EditText) findViewById(R.id.editText2);

        this.login = findViewById(R.id.loginbtn);
        this.login.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.loginbtn) {
            this.emailString = email.getText().toString();
            this.passwordString = password.getText().toString();
            if(emailString == "" || emailString.isEmpty() || passwordString == "" || passwordString.isEmpty()){
                Toast.makeText(LoginActivity.this, "Gelieve alle velden in te vullen",
                        Toast.LENGTH_SHORT).show();
            } else {
                signIn(emailString, passwordString);
            }
        }
    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            LoginActivity.this.email.setText("");
                            LoginActivity.this.password.setText("");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("user", user.getEmail());
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Inloggen mislukt",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
