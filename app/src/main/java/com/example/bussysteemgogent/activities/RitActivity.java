package com.example.bussysteemgogent.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
        adapter.addFragment(new StartFragment(), "Vertrek");
        adapter.addFragment(new AankomstFragment(), "Bestemming");
        adapter.addFragment(new SamenvattingFragment(), "Samenvatting");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void goToAankomstTab() {
        viewPager.setCurrentItem(1, false);
    }

    @Override
    public void goToSamenvattingTab() {
        viewPager.setCurrentItem(2, false);
    }

    @Override
    public void goToMain() {
        onBackPressed();
    }
}
