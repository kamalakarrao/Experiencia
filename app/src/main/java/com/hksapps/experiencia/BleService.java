package com.hksapps.experiencia;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

/**
 * Created by Pranav on 21-01-2018.
 */

public class BleService extends Service {
    public static BluetoothSPP bt;
    public static String Mac = null;
    BluetoothAdapter mBluetoothAdapter;
    public final static int REQUEST_ENABLE_BT = 1;
    public BleService() {

        bt = new BluetoothSPP(this);

        bt.setupService();
        bt.startService(BluetoothState.DEVICE_OTHER);


        if (Mac != null) {
            Toast.makeText(this, "mac  idm found", Toast.LENGTH_SHORT).show();

            bt.connect(Mac);
        }
        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {






        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            public void onDeviceConnected(String name, String address) {
                // Do something when successfully connected
                if(name.equalsIgnoreCase("Experiencia")&&address.equalsIgnoreCase(Mac)){

                    Toast.makeText(BluetoothActivity.context, "Connected ", Toast.LENGTH_SHORT).show();


                    // bt.send("C",true);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    bt.send("C",true);

                    Intent i = new Intent(BleService.this,ExperienciaActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    //  finish();
                }
            }

            public void onDeviceDisconnected() {
                // Do something when connection was disconnected
            }

            public void onDeviceConnectionFailed() {
                // Do something when connection failed
            }
        });



        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        // Do your Bluetooth Work Here



    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }

}
