package com.hksapps.experiencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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


        /*Intent intent = new Intent("com.hksapps.broadcast");
        //  intent.putExtra("yourvalue", "torestore");
        sendBroadcast(intent);
*/

        bt = new BluetoothSPP(this);


        bt.setupService();
        bt.startService(BluetoothState.DEVICE_OTHER);
      // bt.connect(BleService.Mac);

        Toast.makeText(this, "MacID"+ BleService.Mac, Toast.LENGTH_SHORT).show();



        final LinearLayout rain_layout = (LinearLayout) findViewById(R.id.rain_layout);
        final LinearLayout cloud_layout = (LinearLayout) findViewById(R.id.cloud_layout);
        final LinearLayout sunshine_layout = (LinearLayout) findViewById(R.id.sunshine_layout);
        final LinearLayout lightning_layout = (LinearLayout) findViewById(R.id.lightning_layout);

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

                    rain_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    rain_switch.setChecked(false);
                    rain_img.setImageResource(R.drawable.rainoff);
                   // BleService.bt.send("r",true);

                }else {
                    rain_layout.setBackgroundColor(Color.parseColor("#607D8B"));
                  //  lightning_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                 //   sunshine_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                  //  cloud_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    rain_switch.setChecked(true);
                    rain_img.setImageResource(R.drawable.rainon);
                  //  BleService.bt.send("R",true);

                }
            }
        });



        cloud_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cloud_switch.isChecked()){

                    cloud_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    cloud_switch.setChecked(false);
                    cloud_img.setImageResource(R.drawable.cloudoff);
                   // BleService.bt.send("c",false);

                }else {


                    cloud_layout.setBackgroundColor(Color.parseColor("#607D8B"));
                 //   rain_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                 //   sunshine_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                  //  lightning_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    cloud_switch.setChecked(true);
                    cloud_img.setImageResource(R.drawable.cloudon);
                   // BleService.bt.send("C",false);

                }
            }
        });


        sunshine_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sunshine_switch.isChecked()){

                    sunshine_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    sunshine_switch.setChecked(false);
                    sunshine_img.setImageResource(R.drawable.sunshineoff);
                 //   BleService.bt.send("s",false);

                }else {

                    sunshine_layout.setBackgroundColor(Color.parseColor("#607D8B"));
                 //   rain_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                  //  lightning_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                   // cloud_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    sunshine_switch.setChecked(true);
                    sunshine_img.setImageResource(R.drawable.sunshineon);
                 //   BleService.bt.send("S",false);

                }
            }
        });




        lightning_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lightning_switch.isChecked()){

                    lightning_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    lightning_switch.setChecked(false);
                    lightning_img.setImageResource(R.drawable.lightningoff);
                 //   BleService.bt.send("l",false);

                }else {

                    lightning_layout.setBackgroundColor(Color.parseColor("#607D8B"));
                   // rain_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                   // sunshine_layout.setBackgroundColor(Color.parseColor("#90A4AE"));
                   // cloud_layout.setBackgroundColor(Color.parseColor("#90A4AE"));

                    lightning_switch.setChecked(true);
                    lightning_img.setImageResource(R.drawable.lightningon);
                 //   BleService.bt.send("L",false);

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
