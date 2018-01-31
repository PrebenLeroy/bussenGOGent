package com.example.bussysteemgogent;

/**
 * Created by prebe on 31/01/2018.
 */

public interface MyListener {
    public void goToAankomstTab(String soort, String datum, String tijd, String plaats, String aantalLeerlingen, String km);
    public void goToSamenvattingTab(String aankomstPlaats, String aankomstUur, String aankomstKm);
    public void goToMain();
}
