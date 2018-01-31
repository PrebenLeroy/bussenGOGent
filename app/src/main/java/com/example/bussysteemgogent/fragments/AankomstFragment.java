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
        View v =  inflater.inflate(R.layout.fragment_aankomst, container, false);

        this.aankomstPlaats = (EditText) v.findViewById(R.id.aankomstPlaats);

        this.uur = (EditText) v.findViewById(R.id.aankomstUur);

        this.km = (EditText) v.findViewById(R.id.aankomstKm);

        this.btnSamenvatting = (Button) v.findViewById(R.id.button);
        this.btnSamenvatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener listener = (MyListener) getActivity();
                listener.goToSamenvattingTab(aankomstPlaats.getText().toString(), uur.getText().toString(), km.getText().toString());
            }
        });
        return v;
    }
}
