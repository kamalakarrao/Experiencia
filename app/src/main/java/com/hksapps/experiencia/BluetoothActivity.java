package com.hksapps.experiencia;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class BluetoothActivity extends AppCompatActivity {

    private BluetoothSPP bt;
    private String Mac=null;
    BluetoothAdapter mBluetoothAdapter;
    private final static int REQUEST_ENABLE_BT = 1;

   private TextView ble_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        bt = new BluetoothSPP(this);

        ble_name = (TextView) findViewById(R.id.ble_name);


        // Get an instance of the BluetoothAdapter class
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // If the adapter is null it means that the device does not support Bluetooth
            Toast.makeText(this, "Bluetooth is not supported", Toast.LENGTH_SHORT).show();
        }
        mBluetoothAdapter.startDiscovery();

        Button btn = (Button) findViewById(R.id.refresh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        registerAndShowBluetooth();

                Log.i("Dude!","Refresh is done");
            }
        });




        if (!mBluetoothAdapter.isEnabled()) {
            // We need to enable the Bluetooth, so we ask the user
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // REQUEST_ENABLE_BT es un valor entero que vale 1
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


        registerAndShowBluetooth();

        ble_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

if(Mac!=null){

}
            }
        });
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

        try{
            if(mReceiver!=null)
                unregisterReceiver(mReceiver);
        }catch(Exception e)
        {

        }
        super.onDestroy();

    }
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            Log.i("Inside","receiver");
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // A Bluetooth device was found
                // Getting device information from the intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "Device found: " + device.getName() + " MAC " + device.getAddress(), Toast.LENGTH_SHORT).show();
                Log.i("B_activity", "Device found: " + device.getName() + "; MAC " + device.getAddress());
            if(device.getName().equalsIgnoreCase("Experiencia")){

                ble_name.setText(device.getName());
                ble_name.setVisibility(View.VISIBLE);
                Mac = device.getAddress();
            }

            }
        }

    };


    private void registerAndShowBluetooth(){

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);


        if (mBluetoothAdapter.isDiscovering()) {
            // Bluetooth is already in modo discovery mode, we cancel to restart it again
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
    }


}
