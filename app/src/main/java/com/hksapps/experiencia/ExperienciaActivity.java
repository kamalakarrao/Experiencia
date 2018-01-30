package com.hksapps.experiencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
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
     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/


        bt = new BluetoothSPP(this);


        bt.setupService();
        bt.startService(BluetoothState.DEVICE_OTHER);



        LinearLayout rain_layout = (LinearLayout) findViewById(R.id.rain_layout);
        LinearLayout cloud_layout = (LinearLayout) findViewById(R.id.cloud_layout);
        LinearLayout sunshine_layout = (LinearLayout) findViewById(R.id.sunshine_layout);
        LinearLayout lightning_layout = (LinearLayout) findViewById(R.id.lightning_layout);

         final Switch rain_switch = (Switch) findViewById(R.id.rain_switch);
        final Switch cloud_switch = (Switch) findViewById(R.id.cloud_switch);
        final Switch sunshine_switch = (Switch) findViewById(R.id.sunshine_switch);
        final Switch lightning_switch = (Switch) findViewById(R.id.lightning_switch);


        final ImageView rain_img = (ImageView) findViewById(R.id.rain_img);
        final ImageView cloud_img = (ImageView) findViewById(R.id.cloud_img);
        final ImageView sunshine_img = (ImageView) findViewById(R.id.sunshine_img);
        final ImageView lightning_img = (ImageView) findViewById(R.id.lightning_img);


        rain_switch.setClickable(false);
        cloud_switch.setClickable(false);
        sunshine_switch.setClickable(false);
        lightning_switch.setClickable(false);

        rain_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             
                if(rain_switch.isChecked()){

                    rain_switch.setChecked(false);
                    rain_img.setImageResource(R.drawable.rainoff);
                    bt.send("r",false);

                }else {

                    rain_switch.setChecked(true);
                    rain_img.setImageResource(R.drawable.rainon);
                    bt.send("R",false);

                }
            }
        });



        cloud_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cloud_switch.isChecked()){

                    cloud_switch.setChecked(false);
                    cloud_img.setImageResource(R.drawable.cloudoff);
                    bt.send("c",false);

                }else {

                    cloud_switch.setChecked(true);
                    cloud_img.setImageResource(R.drawable.cloudon);
                    bt.send("C",false);

                }
            }
        });


        sunshine_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sunshine_switch.isChecked()){

                    sunshine_switch.setChecked(false);
                    sunshine_img.setImageResource(R.drawable.sunshineoff);
                    bt.send("s",false);

                }else {

                    sunshine_switch.setChecked(true);
                    sunshine_img.setImageResource(R.drawable.sunshineon);
                    bt.send("S",false);

                }
            }
        });




        lightning_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lightning_switch.isChecked()){

                    lightning_switch.setChecked(false);
                    lightning_img.setImageResource(R.drawable.lightningoff);
                    bt.send("l",false);

                }else {

                    lightning_switch.setChecked(true);
                    lightning_img.setImageResource(R.drawable.lightningon);
                    bt.send("L",false);

                }
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




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
