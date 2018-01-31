package com.example.bussysteemgogent.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bussysteemgogent.MyListener;
import com.example.bussysteemgogent.R;

/**
 * Created by prebe on 31/01/2018.
 */

public class SamenvattingFragment extends Fragment {

    Button btnSend;
    private String soort, datum, vertrekTijd, vertrekPlaats, aantalLeerlingen, vertrekKm, aankomstTijd, aankomstPlaats, aankomstKm;
    TextView dt, vP, aL, s, vK, aT, aP, aK;

    public SamenvattingFragment() {
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
        View v = inflater.inflate(R.layout.fragment_samenvatting, container, false);

        this.dt = (TextView) v.findViewById(R.id.txtDT);
        this.vP = (TextView) v.findViewById(R.id.txtVertrek);
        this.aL = (TextView) v.findViewById(R.id.txtAantalLeerlingen);
        this.s = (TextView) v.findViewById(R.id.txtSoort);
        this.vK = (TextView) v.findViewById(R.id.txtKilometersVertrek);
        this.aT = (TextView) v.findViewById(R.id.txtTijd);
        this.aP = (TextView) v.findViewById(R.id.txtAankomst);
        this.aK = (TextView) v.findViewById(R.id.txtKilometersAankomst);

        this.btnSend = (Button) v.findViewById(R.id.button2);
        this.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyListener listener = (MyListener) getActivity();
                listener.goToMain();
            }
        });
        return v;
    }

    public void setStrings(String soort, String datum, String vertrekTijd, String vertrekPlaats,
                           String aantalLeerlingen, String vertrekKm, String aankomstTijd, String aankomstPlaats, String aankomstKm) {
        this.soort = soort;
        this.s.setText(soort);

        this.datum = datum;
        this.vertrekTijd = vertrekTijd;
        this.dt.setText(datum + " om " + vertrekTijd);

        this.vertrekPlaats = vertrekPlaats;
        this.vP.setText(vertrekPlaats);

        this.aantalLeerlingen = aantalLeerlingen;
        this.aL.setText(aantalLeerlingen);

        this.vertrekKm = vertrekKm;
        this.vK.setText(vertrekKm);

        this.aankomstTijd = aankomstTijd;
        this.aT.setText(aankomstTijd);

        this.aankomstPlaats = aankomstPlaats;
        this.aP.setText(aankomstPlaats);

        this.aankomstKm = aankomstKm;
        this.aK.setText(aankomstKm);
    }
}
