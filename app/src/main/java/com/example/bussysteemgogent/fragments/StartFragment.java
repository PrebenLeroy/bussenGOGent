package com.example.bussysteemgogent.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bussysteemgogent.MyListener;
import com.example.bussysteemgogent.R;

/**
 * Created by prebe on 31/01/2018.
 */

public class StartFragment extends Fragment {

    Spinner soort;
    Button btnAankomst;
    EditText datum, tijd, plaats, aantalLeerlingen, km;

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

        this.tijd = (EditText) v.findViewById(R.id.vertrekUur);

        this.plaats = (EditText) v.findViewById(R.id.vertrekplaats);

        this.aantalLeerlingen = (EditText) v.findViewById(R.id.aantalLeerlingen);

        this.km = (EditText) v.findViewById(R.id.startKm);

        this.btnAankomst = (Button) v.findViewById(R.id.button);
        this.btnAankomst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener listener = (MyListener) getActivity();
                if(soort.getSelectedItem().toString() == "" || soort.getSelectedItem().toString().isEmpty() || datum.getText().toString() == "" || datum.getText().toString().isEmpty()
                        || tijd.getText().toString() == "" || tijd.getText().toString().isEmpty() || plaats.getText().toString() == "" || plaats.getText().toString().isEmpty()
                        || aantalLeerlingen.getText().toString() == "" || aantalLeerlingen.getText().toString().isEmpty() || km.getText().toString() == ""
                        || km.getText().toString().isEmpty()){
                    datum.setError("Datum is verplicht");
                    tijd.setError("Vertrekuur is verplicht");
                    plaats.setError("Vertrekplaats is verplicht");
                    aantalLeerlingen.setError("Het aantal leerlingen is verplicht");
                    km.setError("Kilometerstand bij vertrek is verplicht");
                    Toast.makeText(getContext(), "Alle velden moeten ingevuld worden", Toast.LENGTH_SHORT).show();
                } else {
                    listener.goToAankomstTab(soort.getSelectedItem().toString(), datum.getText().toString(),
                            tijd.getText().toString(), plaats.getText().toString(),
                            aantalLeerlingen.getText().toString(), km.getText().toString());
                }
            }
        });

        return v;
    }
}
