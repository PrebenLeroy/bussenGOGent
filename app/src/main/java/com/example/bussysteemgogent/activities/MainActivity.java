package com.example.bussysteemgogent.activities;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bussysteemgogent.R;
import com.example.bussysteemgogent.persistency.BusContract;

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

        list = getAllBusses();

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
