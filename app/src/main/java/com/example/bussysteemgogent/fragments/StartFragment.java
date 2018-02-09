package com.example.bussysteemgogent.fragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bussysteemgogent.MyListener;
import com.example.bussysteemgogent.R;
import com.example.bussysteemgogent.persistency.BusContract;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by prebe on 31/01/2018.
 */

public class StartFragment extends Fragment {

    Spinner soort;
    Button btnAankomst, btnScholen;
    EditText datum, tijd, plaats, aantalLeerlingen, km;
    ListView lv;
    private String[] list = new String[] {"GO! Scholengroep Gent", "GO! Basisschool Merelbeke (De Linde)", "GO! Basisschool Merelbeke (Flora)",
            "GO! Basisschool Het Molenschip (met mogelijkheid internaat)", "GO! Basisschool De Klaver", "GO! Daltonschool Gent De Kleine Icarus",
            "GO! Basisschool Oostakker De Vogelzang", "GO! Basisschool De Wijze Boom", "GO! Basisschool Gentbrugge", "GO! Leefschool Eikenkring",
            "GO! Basisschool Mijlpaal", "GO! Basisschool Voskenslaan", "GO! Basisschool De Wijze Eik (Eeklostraat)", "GO! Basisschool De Wijze Eik (A. Cas de ter Bekenlaan)",
            "GO! Leefschool De Oogappel", "GO! Einstein Atheneum", "GO! Atheneum Gentbrugge", "GO! Atheneum Mariakerke", "GO! Atheneum Merelbeke", "GO! Popelin Lyceum",
            "GO! Atheneum Voskenslaan", "GO! Lyceum Gent", "GO! Middenschool Voskenslaan", "GO! KTA-GITO Groenkouter (Sint-Baafskouter)", "GO! KTA-GITO Groenkouter (Jef Crickstraat)",
            "GO! KTA Mobi (Coupure - Gent)", "GO! KTA Mobi (Industriepark - Drongen)", "GO! MUDA", "GO! Tuinbouwschool Melle", "GO! MPI De Oase", "GO! BUSO Reynaertschool",
            "GO! Campus Impuls", "GO! Academie Muziek, Woord en Dans Gent (DKO Panta Rhei)", "CVO Panta Rhei De Avondschool", "GO! Internaat De Koekoek", "GO! Internaat Ter Linde",
            "GO! Internaat Questopia", "GO! CLB Gent (Voskenslaan - Gent)", "GO! CLB Gent (Varingstraat - Melle)", "Garage Van Nevel", "Sporthal Bourgoyen", "Kristalijn",
            "Zwembad Rozebroeken", "Sporthal Neptunus", "Zwembad Merelbeke", "Zwembad Rooigem", "Sporthal Keiskant", "Sporthal Eddy Mercx", "Zwembad Puyenbroeck", "CC De Stroming Evergem",
            "Zwembad Den Boer", "Kinderboerderij De Campagne", "Blaarmeersen", "Topsporthal", "Zwembad Strop"
    };

    public StartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_start, container, false);

        this.soort = (Spinner) v.findViewById(R.id.spinnerSoort);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.soorten, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.soort.setAdapter(adapter);

        this.datum = (EditText) v.findViewById(R.id.datum);
        this.datum.setText(getCurrentDate());
        this.datum.setEnabled(false);

        this.tijd = (EditText) v.findViewById(R.id.vertrekUur);

        this.btnScholen = (Button) v.findViewById(R.id.btnScholen);
        this.btnScholen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDialog();
            }
        });

        this.plaats = (EditText) v.findViewById(R.id.vertrekplaats);

        this.aantalLeerlingen = (EditText) v.findViewById(R.id.aantalLeerlingen);

        this.km = (EditText) v.findViewById(R.id.startKm);

        this.btnAankomst = (Button) v.findViewById(R.id.button);
        this.btnAankomst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener listener = (MyListener) getActivity();
                if (tijd.getText().toString() == "" || tijd.getText().toString().isEmpty() || !tijd.getText().toString().matches("(((0|1)[0-9])|(2[0-3])):[0-5][0-9]")) {

                    tijd.setError("Vertrekuur is verplicht en is van de vorm \"uu:mm\"");

                }
                if (plaats.getText().toString() == "" || plaats.getText().toString().isEmpty()) {

                    plaats.setError("Vertrekplaats is verplicht");

                }
                if (aantalLeerlingen.getText().toString() == "" || aantalLeerlingen.getText().toString().isEmpty()) {

                    aantalLeerlingen.setError("Het aantal leerlingen is verplicht");

                }
                if (km.getText().toString() == "" || km.getText().toString().isEmpty()) {

                    km.setError("Kilometerstand bij vertrek is verplicht");

                }
                if (soort.getSelectedItem().toString() == "" || soort.getSelectedItem().toString().isEmpty()
                        || tijd.getText().toString() == "" || tijd.getText().toString().isEmpty()
                        || plaats.getText().toString() == "" || plaats.getText().toString().isEmpty()
                        || aantalLeerlingen.getText().toString() == "" || aantalLeerlingen.getText().toString().isEmpty()
                        || km.getText().toString() == "" || km.getText().toString().isEmpty()
                        || !tijd.getText().toString().matches("(((0|1)[0-9])|(2[0-3])):[0-5][0-9]")) {
                }
                else {
                    listener.goToAankomstTab(soort.getSelectedItem().toString(), datum.getText().toString(),
                            tijd.getText().toString(), plaats.getText().toString(),
                            aantalLeerlingen.getText().toString(), km.getText().toString());
                }
            }
        });

        return v;
    }

    private void viewDialog() {
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.spinner_bus, null);

        lv = (ListView) dialog.findViewById(R.id.spinner);

        TextView title = (TextView) dialog.findViewById(R.id.textView4);
        title.setText(R.string.selecteer_een_plaats);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_single_choice, list);

        lv.setAdapter(adapter);

        new AlertDialog.Builder(getContext())
                .setView(dialog).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StartFragment.this.plaats.setText((String) lv.getItemAtPosition(lv.getCheckedItemPosition()));
            }
        }).setNegativeButton(android.R.string.cancel, null).show();
    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(calendar.getTime());
    }

}
