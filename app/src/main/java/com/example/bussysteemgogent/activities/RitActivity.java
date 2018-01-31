package com.example.bussysteemgogent.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.bussysteemgogent.R;

/**
 * Created by prebe on 31/01/2018.
 */

public class RitActivity extends AppCompatActivity {

    private String nummerplaat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rit);
        this.nummerplaat = getIntent().getStringExtra("bus");
        getSupportActionBar().setTitle("Bus: " + nummerplaat);
    }
}
