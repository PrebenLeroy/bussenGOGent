package com.example.bussysteemgogent.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bussysteemgogent.MyListener;
import com.example.bussysteemgogent.R;

/**
 * Created by prebe on 31/01/2018.
 */

public class AankomstFragment extends Fragment {

    Button btnSamenvatting, btnAankomstPlaats;
    EditText aankomstPlaats, uur, km;
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

    public AankomstFragment() {
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
        View v = inflater.inflate(R.layout.fragment_aankomst, container, false);

        this.aankomstPlaats = (EditText) v.findViewById(R.id.aankomstPlaats);

        this.btnAankomstPlaats = (Button) v.findViewById(R.id.button3);
        this.btnAankomstPlaats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDialog();
            }
        });

        this.uur = (EditText) v.findViewById(R.id.aankomstUur);

        this.km = (EditText) v.findViewById(R.id.aankomstKm);

        this.btnSamenvatting = (Button) v.findViewById(R.id.button);
        this.btnSamenvatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener listener = (MyListener) getActivity();
                if (aankomstPlaats.getText().toString() == "" || aankomstPlaats.getText().toString().isEmpty()) {
                    aankomstPlaats.setError("Bestemming is verplicht");
                }
                if (uur.getText().toString() == "" || uur.getText().toString().isEmpty() || !uur.getText().toString().matches("(((0|1)[0-9])|(2[0-3])):[0-5][0-9]")) {
                    uur.setError("Aankomstuur is verplicht en is van de vorm \"uu:mm\"");
                }
                if (km.getText().toString() == "" || km.getText().toString().isEmpty()) {
                    km.setError("Kilometerstand bij aankomst is verplicht");
                }
                if (aankomstPlaats.getText().toString() == "" || uur.getText().toString() == "" || km.getText().toString() == ""
                        || aankomstPlaats.getText().toString().isEmpty() || uur.getText().toString().isEmpty() || km.getText().toString().isEmpty()
                        || !uur.getText().toString().matches("(((0|1)[0-9])|(2[0-3])):[0-5][0-9]")) {
                }
                else {
                    listener.goToSamenvattingTab(aankomstPlaats.getText().toString(), uur.getText().toString(), km.getText().toString());
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
                AankomstFragment.this.aankomstPlaats.setText((String) lv.getItemAtPosition(lv.getCheckedItemPosition()));
            }
        }).setNegativeButton(android.R.string.cancel, null).show();
    }
}
