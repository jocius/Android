package joc.airport;

/**
 * Created by justinas on 4/18/17.
 */

import android.app.Application;
import android.util.Log;


import com.estimote.coresdk.observation.region.Region;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.estimote.coresdk.observation.region.AbstractRegionMonitor;
import com.estimote.mgmtsdk.connection.protocol.Operation;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class MyApplication extends Application {

    private BeaconManager beaconManager;
    private BeaconRegion region;
    private static List<Beacon> newList1 = new ArrayList<Beacon>();




    @Override
    public void onCreate() {

        super.onCreate();
        System.out.println("On created@@@@!!");
        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.disconnect();
//// We want the beacons heartbeat to be set at one second.
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(5),
                5);
        beaconManager.setForegroundScanPeriod(TimeUnit.SECONDS.toMillis(5),
                5);


        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {


                System.out.println("entered...");
                if (list.size()>0){
                    newList1.add(list.get(0));
                    System.out.println("List size: " + list.size());
                System.out.println("MAJOR: " + list.get(0).getMajor());
                System.out.println("MAJOR: " + list.get(0).getMinor());
                    System.out.println("RSS value: " + list.get(0).getRssi());
                    System.out.println("measured power " + list.get(0).getMeasuredPower());



                }
            }
        });
//        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
//            @Override
//            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> list) {
//                System.out.println("entered...");
//                if (list.size()>0){
//                System.out.println("MAJOR: " + list.get(0).getMajor());
//                System.out.println("MAJOR: " + list.get(0).getMinor());
//                    Log.d("Airport", "Nearest places: " + list.get(0).getMinor());
//                }
//            }
//
//            @Override
//            public void onExitedRegion(BeaconRegion beaconRegion) {
//                System.out.println("exited");
//            }
//        });

        region = new BeaconRegion("ranged region", UUID.fromString("e3b54450-ab73-4d79-85d6-519eaf0f45d9"), null, null);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {

                beaconManager.startRanging(region);
            }
        });


//        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//
//
//                beaconManager.startMonitoring(new BeaconRegion("monitored region",
//                        UUID.fromString("e3b54450-ab73-4d79-85d6-519eaf0f45d9"), null, null));
//
//
//            }
//        });




}


    public  List<Beacon> startIt(){

                return newList1;

    }


}
