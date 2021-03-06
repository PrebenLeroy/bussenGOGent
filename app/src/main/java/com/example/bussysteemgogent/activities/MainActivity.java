package com.example.bussysteemgogent.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bussysteemgogent.R;
import com.example.bussysteemgogent.persistency.BusContract;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    ListView lv;
    private  ArrayList<String> list;
    private String user;
    TextView name;
    private FirebaseAuth mAuth;
    private String nummerplaat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.user = getIntent().getStringExtra("user");

        this.name = (TextView) findViewById(R.id.naam);
        this.name.setText(user);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.btnLogOut:
                mAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void viewDialog() {
        final LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View dialog = inflater.inflate(R.layout.spinner_bus, null);

        lv = (ListView) dialog.findViewById(R.id.spinner);

        this.list = new ArrayList<>();

        this.list = getAllBusses();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_single_choice, list);

        lv.setAdapter(adapter);

        new AlertDialog.Builder(MainActivity.this)
                .setView(dialog).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, RitActivity.class);
                intent.putExtra("bus", (String) lv.getItemAtPosition(lv.getCheckedItemPosition()));
                intent.putExtra("passw", getIntent().getStringExtra("passw"));
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
