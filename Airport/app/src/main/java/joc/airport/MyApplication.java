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




}


    public  List<Beacon> startIt(){

                return newList1;

    }


}
