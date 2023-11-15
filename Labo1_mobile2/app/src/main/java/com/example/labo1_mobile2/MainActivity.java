package com.example.labo1_mobile2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private AlienSolarSystem alien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        alien=new AlienSolarSystem(this);
        setContentView(alien);
    }
}