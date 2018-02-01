package com.example.bussysteemgogent.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bussysteemgogent.MyListener;
import com.example.bussysteemgogent.R;
import com.example.bussysteemgogent.adapters.ViewPagerAdapter;
import com.example.bussysteemgogent.fragments.AankomstFragment;
import com.example.bussysteemgogent.fragments.SamenvattingFragment;
import com.example.bussysteemgogent.fragments.StartFragment;

/**
 * Created by prebe on 31/01/2018.
 */

public class RitActivity extends AppCompatActivity implements MyListener {

    private String nummerplaat;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StartFragment start;
    private AankomstFragment aankomst;
    private SamenvattingFragment samenvatting;
    private String soort, datum, vertrekTijd, vertrekPlaats, aantalLeerlingen, vertrekKm, aankomstTijd, aankomstPlaats, aankomstKm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rit);
        this.nummerplaat = getIntent().getStringExtra("bus");
        getSupportActionBar().setTitle("Bus: " + nummerplaat);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.start = new StartFragment();
        this.aankomst = new AankomstFragment();
        this.samenvatting = new SamenvattingFragment();
        adapter.addFragment(start, "Vertrek");
        adapter.addFragment(aankomst, "Bestemming");
        adapter.addFragment(samenvatting, "Samenvatting");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void goToAankomstTab(String soort, String datum, String tijd, String plaats, String aantalLeerlingen, String km){
        this.soort = soort;
        this.datum = datum;
        this.vertrekTijd = tijd;
        this.vertrekPlaats = plaats;
        this.aantalLeerlingen = aantalLeerlingen;
        this.vertrekKm = km;
        Log.i("vertrek", km);
        viewPager.setCurrentItem(1, false);
    }

    @Override
    public void goToSamenvattingTab(String aankomstPlaats, String aankomstUur, String aankomstKm) {
        this.aankomstPlaats = aankomstPlaats;
        this.aankomstTijd = aankomstUur;
        this.aankomstKm = aankomstKm;
        Log.i("aankomstKm", aankomstKm);
        this.samenvatting.setStrings(soort, datum, vertrekTijd, vertrekPlaats, aantalLeerlingen, vertrekKm, aankomstTijd, this.aankomstPlaats, this.aankomstKm);
        viewPager.setCurrentItem(2, false);
    }

    @Override
    public void goToMain() {
        onBackPressed();
    }
}
