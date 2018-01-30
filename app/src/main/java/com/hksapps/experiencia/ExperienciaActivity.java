package com.hksapps.experiencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

public class ExperienciaActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    public BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bt = new BluetoothSPP(this);


        bt.setupService();
        bt.startService(BluetoothState.DEVICE_OTHER);


        String[] arr = bt.getPairedDeviceName();

        boolean firstTime = true;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equalsIgnoreCase("experiencia")) {
                firstTime = false;
                Toast.makeText(this, "found", Toast.LENGTH_SHORT).show();

            }


        }


        if (firstTime) {

            Intent i = new Intent(ExperienciaActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }


    }


}
