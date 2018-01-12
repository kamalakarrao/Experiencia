package com.hksapps.experiencia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends AppCompatActivity {

    private BluetoothSPP bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txt = (TextView) findViewById(R.id.txt);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bt.send("r",true);

            }
        });


         bt = new BluetoothSPP(this);

        connectToBluetooth();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,(bt.getConnectedDeviceAddress()) , Toast.LENGTH_SHORT).show();

                bt.send("R",true);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    public void onStart() {
        super.onStart();
        if(!bt.isBluetoothEnabled()) {
            bt.enable();
            bt.autoConnect("Experiencia");
            // Do somthing if bluetooth is disable
        } else {
            // Do something if bluetooth is already enable2
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if(resultCode == Activity.RESULT_OK)

            bt.connect(data);
        } else if(requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if(resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
               // setup();
            } else {
                // Do something if user doesn't choose any device (Pressed back)
            }
        }
    }

    private void connectToBluetooth() {

       // Intent intent = new Intent(getApplicationContext(), DeviceList.class);
        //startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);

        bt.setupService();
        bt.startService(BluetoothState.DEVICE_OTHER);

        Intent intent = new Intent(getApplicationContext(), DeviceList.class);
        startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);

      //  bt.connect("20:16:04:07:55:73");

     /*   bt.setupService();

        bt.startService(BluetoothState.DEVICE_OTHER);
      //  setup();
       // bt.autoConnect("Experiencia");

        Toast.makeText(this,String.valueOf(bt.isAutoConnecting()) , Toast.LENGTH_SHORT).show();


        //if(!bt.isAutoConnecting()) {

    //Toast.makeText(this,(bt.getConnectedDeviceName()) , Toast.LENGTH_SHORT).show();

    bt.connect("20:16:04:07:55:73");

        Toast.makeText(this,(bt.getConnectedDeviceAddress()) , Toast.LENGTH_SHORT).show();


//}
        String[] arr = bt.getPairedDeviceAddress();
        String[] arr2 = bt.getPairedDeviceName();


        for(int i=0;i<arr.length;i++) {

            Log.d("Mac Address : ",arr[i]);
            Log.d("Name  : ",arr2[i]);
           // Toast.makeText(this, arr[i], Toast.LENGTH_SHORT).show();
        }
     */   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
