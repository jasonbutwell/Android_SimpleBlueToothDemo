package com.jasonbutwell.simplebluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;

    // Disable Bluetooth - just for fun

    public void turnBluetoothOff( View view ) {

        bluetoothAdapter.disable();

        // Check we actually turned it off

        if ( bluetoothAdapter.isEnabled()) {

            // If we couldn't disable it we show this toast message

            Toast.makeText(getApplicationContext(), "Bluetooth could not be disabled", Toast.LENGTH_LONG).show();
        }
        else {

            // If we did actually disable it 

            Toast.makeText(getApplicationContext(),"Bluetooth is off", Toast.LENGTH_LONG).show();
        }
    }

    // Find Bluetooth devices - using an intent

    public void findDiscoverableDevices( View view ) {

        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(intent);
    }

    // show devices in list view

    public void viewPairedDevices( View view ) {

        // Use a set to get the bonded devices

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        // initialise our list view to display the results

        ListView pairedDevicesListView = (ListView)findViewById(R.id.ListViewPairedDevices);

        // initialise arraylist to use for our list view with the adapter

        ArrayList pairedDevicesArrayList = new ArrayList();

        // Looop through for all paired devices and add the name of the blue tooth device to the list

        for(BluetoothDevice bluetoothDevice : pairedDevices)
            pairedDevicesArrayList.add(bluetoothDevice.getName());

        // create our new array adapter to show our results using simple_list_item_1 format

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,pairedDevicesArrayList);

        // set the array adapter to show our results in the list view

        pairedDevicesListView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // check to see if blue tooth is enabled or disabled when we run this app. We show a toast message if it is turned on.
        // If its not we will attempt to turn it on using an intent for action_request_enable
        // To pass control to the OS to try to enable bluetooth for us.

        if ( bluetoothAdapter.isEnabled() ) {

            // It's already on
            Toast.makeText(getApplicationContext(),"Bluetooth is turned on.",Toast.LENGTH_LONG).show();

        } else {

            // It's not on - so we will attempt to turn it on via the os from a requesting intent

            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);

            // Check to see whether we were succesful in turning on Bluetooth or not.
            // If we were we display a toast message here also

            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth is turned on.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
