package com.clkasonicexample.bluetoothtest_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4;
    private BluetoothAdapter mBluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b1 = (Button) findViewById(R.id.button2);
        b1 = (Button) findViewById(R.id.button3);
        b1 = (Button) findViewById(R.id.button4);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listView = (ListView) findViewById(R.id.listView);
    }

    public void On(View v) {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent TurnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(TurnOn, 0);
            Toast.makeText(getApplicationContext(), "TURNED ON", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "ALREADY ON", Toast.LENGTH_LONG).show();
        }
    }

    public void Off(View v) {
        mBluetoothAdapter.disable();
        Toast.makeText(getApplicationContext(), "TURNED OFF", Toast.LENGTH_LONG).show();
    }

    public void Visible(View v) {
        Intent GetVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(GetVisible, 0);
    }

    public void List(View v) {
        pairedDevices = mBluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for (BluetoothDevice bt : pairedDevices)
            list.add(bt.getName());

        Toast.makeText(getApplicationContext(), "SHOWING PAIRED DEVICES", Toast.LENGTH_LONG).show();
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

    }
}
