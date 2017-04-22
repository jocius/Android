package joc.airport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private BeaconRegion region;
    private static List<Beacon> newList1 = new ArrayList<>();



    private Button start;
    private TextView mInput;
    private TextView mInput2;
    private TextView mInput3;
    private TextView mInput4;

    private Integer floor2 = 0;
    private Integer floor2Power = 0;
    private Integer floor5 = 0;
    private Integer floor5Power = 0;
    private Integer scanCount = 0;
    private String startStation;

    private boolean ifExists(List<Beacon> list, String beacon){

//        for (Beacon beacon: list){
//            if (beacon.getUniqueKey())
//
//        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ListView listView = (ListView) findViewById(R.id.listView);
//        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, newList1);
//        listView.setAdapter(adapter);






        beaconManager = new BeaconManager(getApplicationContext());

//// We want the beacons heartbeat to be set at one second.
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(2),
                2);
        beaconManager.setForegroundScanPeriod(TimeUnit.SECONDS.toMillis(2),
                2);


        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {

                scanCount++;



                if (scanCount==4){
                    if(floor5>0) {
                        if (floor2Power / floor2 > floor5Power / floor5Power) {

                            startStation = "floor 2";

                        }
                    }
                    else if(floor2==floor5){

//                        if(floor2Power >floor5Power){
//
//                            startStation = "floor 5";
//                        }
//                        else{
//                            startStation = "floor 2";
//                        }

                    }
                    else {

                        startStation = "floor 5";
                    }
                    mInput3.setText(startStation);
//if scan count 5 reset all the counters
                    scanCount=0;
                    floor2 =0;
                    floor5 =0;
                    
                }
                newList1 = list;


                System.out.println("entered...");
                if (list.size()>0){

                    for (Beacon beacon : list){
                        if (beacon.getMajor()==2 || beacon.getMajor() ==5){

                            if (beacon.getMajor()==2){
                                floor2++;
                                floor2Power = floor2Power + beacon.getRssi();
                                mInput.setText("Floor: " + beacon.getMajor() + " room: "+ beacon.getMinor() + " count: " + floor2 + " RSS " +beacon.getRssi());}
                            if (beacon.getMajor()==5){
                                floor5++;
                                floor5Power = floor5Power + beacon.getRssi();
                                mInput2.setText("Floor: " + beacon.getMajor() + " room: "+ beacon.getMinor()+ " count: " + floor5 + " RSS " +beacon.getRssi());}


                            System.out.println("beacon: " + beacon.getMajor() + " minor: " + beacon.getMinor());
                        }


                    }
                    System.out.println("List size: " + list.size());
                    System.out.println("MAJOR: " + list.get(0).getMajor());
                    System.out.println("MAJOR: " + list.get(0).getMinor());
                    System.out.println("RSS value: " + list.get(0).getRssi());
                    System.out.println("measured power " + list.get(0).getMeasuredPower());

                }
            }
        });


        region = new BeaconRegion("ranged region", UUID.fromString("e3b54450-ab73-4d79-85d6-519eaf0f45d9"), null, null);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {

                beaconManager.startRanging(region);
            }
        });












        start = (Button) findViewById(R.id.button1);
        mInput = (TextView) findViewById(R.id.hello);
        mInput2 = (TextView) findViewById(R.id.hello2);
        mInput3 = (TextView) findViewById(R.id.hello3);
        mInput4 = (TextView) findViewById(R.id.hello4);


        final MyApplication app  = new MyApplication();
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                floor2 = 0;
                floor5 = 0;
                scanCount = 0;
                mInput.setText("RESETED");
                mInput2.setText("RESETED");
                mInput3.setText("RESETED");


            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }

    public void updateUI(String input){

        mInput.setText(input);
    }


}
