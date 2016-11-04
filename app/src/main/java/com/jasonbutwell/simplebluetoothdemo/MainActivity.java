package com.jasonbutwell.simplebluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( bluetoothAdapter.isEnabled() ) {
            Toast.makeText(getApplicationContext(),"Bluetooth is turned on.",Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);

            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth is turned on.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
