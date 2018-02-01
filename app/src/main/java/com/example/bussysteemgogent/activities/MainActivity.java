package com.example.bussysteemgogent.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bussysteemgogent.R;
import com.example.bussysteemgogent.persistency.BusContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Spinner spinner;
    private  ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnStart = (Button) findViewById(R.id.btnRit);

        this.btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void viewDialog() {
        final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View dialog = inflater.inflate(R.layout.spinner_bus, null);

        spinner = (Spinner) dialog.findViewById(R.id.spinner);

        this.list = new ArrayList<>();

        this.list = getAllBusses();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        new AlertDialog.Builder(MainActivity.this)
                .setView(dialog).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nummerplaat = spinner.getSelectedItem().toString();
                Intent intent = new Intent(MainActivity.this, RitActivity.class);
                intent.putExtra("bus", nummerplaat);
                startActivity(intent);
            }
        }).setNegativeButton(android.R.string.cancel, null).show();
    }


    private ArrayList<String> getAllBusses() {
        ArrayList<String> list = new ArrayList<>();
        String[] projection = {
                BusContract.BusEntry._ID,
                BusContract.BusEntry.COLUMN_NUMMERPLAAT
        };

        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;

        Uri uri = BusContract.BusEntry.CONTENT_URI;

        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);

        if (cursor != null) {
            try {
                while(cursor.moveToNext()) {
                    list.add(cursor.getString(cursor.getColumnIndex(BusContract.BusEntry.COLUMN_NUMMERPLAAT)));
                }
            } finally {
                cursor.close();
            }
        }

        return list;
    }
}
