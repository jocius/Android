package joc.airport;

/**
 * Created by justinas on 4/18/17.
 */

import android.app.Application;



import com.estimote.coresdk.observation.region.Region;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.service.BeaconManager;
import com.estimote.coresdk.observation.region.AbstractRegionMonitor;
import com.estimote.mgmtsdk.connection.protocol.Operation;


import java.util.UUID;


public class MyApplication extends Application {

    private BeaconManager beaconManager;
    private Region region;

    @Override
    public void onCreate() {
        super.onCreate();

        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {


                beaconManager.startMonitoring(new BeaconRegion("monitored region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null));


            }
        });

}

\
}
