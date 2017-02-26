package com.example.minchangjang.lessonfinalfacebook;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

/**
 * Created by minchangjang on 2017. 2. 24..
 */

public class MyApplication extends Application {

    private BeaconManager beaconManager;

    @Override
    public void onCreate() {
        super.onCreate();
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(
                        new Region("monitored region",
                                UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), 0, 0));
            }
        });

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                //TODO 액티비티를 띄우는 처리
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("mode", "beacon");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getApplicationContext().startActivity(intent);
                Toast.makeText(getApplicationContext(), "Beacon...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onExitedRegion(Region region) {

            }
        });
    }
}

