package com.example.bussysteemgogent;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by prebe on 29/01/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private ConstraintLayout full;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        this.full = findViewById(R.id.full);
        full.setBackgroundColor(Color.parseColor("#bbeebb"));

        this.login = findViewById(R.id.loginbtn);
        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
