package com.example.yunikim.callbackexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ColorListFragment.OnColorSelectedListener {

    ColorFragment mColorFragement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorFragement = (ColorFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_color);
    }

    @Override
    public void onColorSelected(int color) {
        mColorFragement.setColor(color);
    }
}
