package com.example.saeongmeonjul.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.saeongmeonjul.R;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class BluetoothFragment extends Fragment {
    BluetoothSPP bt;
    private Button btnOn, btnOff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_bluetooth, container, false);

        btnOn = rootView.findViewById(R.id.btnSendOn);
        btnOff = rootView.findViewById(R.id.btnSendOff);

        bt = new BluetoothSPP(getActivity());
//        bt = new BluetoothSPP(container.getContext());

        if (!bt.isBluetoothAvailable()){
            Toast.makeText(getActivity(), "Bluetooth is not available", Toast.LENGTH_SHORT).show();
//            finish();
        }

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getActivity(), "Connected to" + name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeviceDisconnected() {
                Toast.makeText(getActivity(), "Connection lost", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeviceConnectionFailed() {
                Log.i("Check", "Unable to connect");
            }
        });

        bt.setAutoConnectionListener(new BluetoothSPP.AutoConnectionListener() {

            @Override
            public void onNewConnection(String name, String address) {
                Log.i("Check", "New Connection - " + name + " - " + address);
            }

            @Override
            public void onAutoConnectionStarted() {
                Log.i("Check", "Auto menu_connection started");
            }

        });

        Button btnConnect = rootView.findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt.getServiceState() == BluetoothState.STATE_CONNECTED){
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getActivity(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });


        return rootView;
    }

    public void onDestroy(){
        super.onDestroy();
        bt.stopService();
    }

    public void onStart(){
        super.onStart();
        if(!bt.isBluetoothEnabled()){
            bt.enable();
        } else {
            if(!bt.isServiceAvailable()){
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == BluetoothState.REQUEST_CONNECT_DEVICE){
            if(resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if(requestCode == BluetoothState.REQUEST_ENABLE_BT){
            if(resultCode == Activity.RESULT_OK){
                bt.setupService();
            } else {
                Toast.makeText(getActivity(), "Bluetooth was not enabled", Toast.LENGTH_SHORT).show();
//                finish();
            }
        }
    }

    public void setup(){

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "TOUCH ON BUTTON", Toast.LENGTH_SHORT).show();
                bt.send("on", true);
            }
        });
        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "TOUCH OFF BUTTON", Toast.LENGTH_SHORT).show();
                bt.send("off", true);
            }
        });
        bt.autoConnect("IOIO");
    }
}
