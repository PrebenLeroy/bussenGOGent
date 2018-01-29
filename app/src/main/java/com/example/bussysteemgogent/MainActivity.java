package com.example.bussysteemgogent;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnStart = (Button) findViewById(R.id.btnRit);

        final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View dialog = inflater.inflate(R.layout.spinner_bus, null);

        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);

        ArrayList<String> list = new ArrayList<>();
        list.add("1-AAA-123");
        list.add("1-BBB-123");
        list.add("1-CCC-123");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        this.btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                        .setView(dialog).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }

                        }).setNegativeButton(android.R.string.cancel, null).show();

            }
        });
    }
}
