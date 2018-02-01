package com.example.bussysteemgogent.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bussysteemgogent.MyListener;
import com.example.bussysteemgogent.R;

/**
 * Created by prebe on 31/01/2018.
 */

public class AankomstFragment extends Fragment {

    Button btnSamenvatting;
    EditText aankomstPlaats, uur, km;

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
}
