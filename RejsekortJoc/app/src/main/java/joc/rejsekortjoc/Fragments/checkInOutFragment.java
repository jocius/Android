package joc.rejsekortjoc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import joc.rejsekortjoc.Database.HistoryDB;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.HelpClasses.BeaconInfo;
import joc.rejsekortjoc.HelpClasses.History;
import joc.rejsekortjoc.HelpClasses.Station;
import joc.rejsekortjoc.Other.SaveSharedPreference;
import joc.rejsekortjoc.Other.StationPrice;
import joc.rejsekortjoc.R;

/**
 * Created by justinas on 4/18/17.
 */


public class checkInOutFragment  extends android.support.v4.app.Fragment {


    //beacons stuff
    private BeaconManager beaconManager;
    private BeaconRegion region = new BeaconRegion("ranged region", UUID.fromString("e3b54450-ab73-4d79-85d6-519eaf0f45d9"), null, null);


    //activity
    private TextView checkOutloc,checkInLoc, testView;
    private Button startTripBtn, resetBtn;


    private static UserDB mUserDB;


    //beacon handling
    private BeaconInfo checkedInLocation=null;
    private boolean checkInUpdated = false;
    private String checkInTime = null;

    private BeaconInfo checkedOutLocation = null;
    private static List<BeaconInfo> beaconList = new ArrayList<>();
    private Integer scanCount = 0;
    private Integer count = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.checkinout_fragment, container, false);

        checkOutloc = (TextView) v.findViewById(R.id.checkedOutLocation);
        checkInLoc = (TextView) v.findViewById(R.id.checkedInLocation);
        testView = (TextView) v.findViewById(R.id.testView1);
        startTripBtn = (Button) v.findViewById(R.id.startTrip);
        resetBtn = (Button) v.findViewById(R.id.resetTrip);

        mUserDB = mUserDB.get(getActivity());
        startTripBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetEverything();
            }

    });


        startTripBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //minimum trip cost is 15DKK, if it is less, user cannot start trip
                if (Double.parseDouble(SaveSharedPreference.getCredit(getActivity()))> 15){
                    beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
                        @Override
                        public void onServiceReady() {
                            beaconManager.startRanging(region);
                        }
                    });
                    startTripBtn.setEnabled(false);
                    Toast.makeText(getActivity().getApplicationContext(),"Trip has started...Looking for beacons", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(),"You have to have at least 15 DKK to start trip!", Toast.LENGTH_LONG).show();
                }

//            HistoryDB mHistoryDB = new HistoryDB();
//                mHistoryDB.addTrip(new History(1,2,999.0,SaveSharedPreference.getUserName(getActivity()),"2017-01-01 -12:12","2018-01-01-00"));
//                mHistoryDB.addTrip(new History(2,3,500,SaveSharedPreference.getUserName(getActivity()),"2018-01-01 -12:12","2018-01-01-00"));

                testView.setText("CREDIT: " + SaveSharedPreference.getCredit(getActivity()));

//                count = 0;



            }
        });


        //beacons....
        beaconManager = new BeaconManager(getActivity());

//// We want the beacons heartbeat to be set at 2 second.
        beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(2),
                2);
        beaconManager.setForegroundScanPeriod(TimeUnit.SECONDS.toMillis(2),
                2);

        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {
                count++;

                scanCount++;
                //after 4 scans, determine, which floor user is
                if (scanCount==4){

                    if (!beaconList.isEmpty()){startFilter();}

                    scanCount=0;
                    beaconList = new ArrayList<>();

                }
                //if receive a beacon, call method for filtering
                if (list.size()>0){
                    for (Beacon beacon : list){

                        //restriction just to floor 2 and 5
                        if (beacon.getMajor()==2 || beacon.getMajor() ==5){

                            //checkIn location is know, then dont read CheckIn location
                            if (checkedInLocation!=null){

                                if (checkedInLocation.getFloor() != beacon.getMajor()){
                                    ifExists(beacon);
                                }
                            }
                            else{
                                ifExists(beacon);

                            }


                        }
//                        if (beacon.getMajor()==5){
//
//                            checkOutloc.setText("Floor: " + beacon.getMajor() + " room: "+ beacon.getMinor() + " RSS " +beacon.getRssi() + " Counts: "+ scanCount);
//
//                        }
//                        if (beacon.getMajor()==2){
//
//
//                            checkInLoc.setText("Floor: " + beacon.getMajor() + " room: "+ beacon.getMinor() + " RSS " +beacon.getRssi() + " Counts: "+ scanCount);
//                        }

                    }


                }

            }
        });

        return v;
    }


//places unique beacons in list (uniqueness is based on Floor)
    private void ifExists(Beacon newBeacon){

        boolean found = false;
        Integer beaconIndex = -1;
        if(!beaconList.isEmpty()){

            for (int i= 0 ; i < beaconList.size();i++){

                //same floor exists in list, update count and add power
                if (beaconList.get(i).getFloor()== newBeacon.getMajor()){
                    found = true;
                    beaconIndex = i;

                }

            }
            //SPECIAL handling, due to concurrentModifactionExceptions
            //found same within array
            if (found && beaconIndex > -1){


                if (beaconIndex!=-1){

                    beaconList.get(beaconIndex).setCount(beaconList.get(beaconIndex).getCount()+1);
                    beaconList.get(beaconIndex).setPower(beaconList.get(beaconIndex).getPower()+newBeacon.getRssi());
                }


            }
             else if(!found){
                BeaconInfo beac = new BeaconInfo();
                beac.setArea(newBeacon.getMinor());
                beac.setCount(1);
                beac.setFloor(newBeacon.getMajor());
                beac.setPower(newBeacon.getRssi());
                beaconList.add(beac);

            }




        }
        //list empty, add beacon
        else {
            BeaconInfo beac = new BeaconInfo();
            beac.setArea(newBeacon.getMinor());
            beac.setCount(1);
            beac.setFloor(newBeacon.getMajor());
            beac.setPower(newBeacon.getRssi());
            beaconList.add(beac);

        }

    }

    private void startFilter(){

        List<BeaconInfo> emptyList = new ArrayList<>();
        emptyList = beaconList;
        //one beacon in list - means no filtering - checked-IN location is known
        if (emptyList.size()==1){

            whichLocation(emptyList.get(0));


        }
        //two different floor beacons found
        else if(emptyList.size()>1) {
            //find
            float maxPower =  (float)(emptyList.get(0).getPower() / emptyList.get(0).getCount());
            int index = 0;
            for(int i= 0 ; i<emptyList.size();i++){
//                        for (BeaconInfo beacon: emptyList){
                float beaconPower = (float)(emptyList.get(i).getPower() / emptyList.get(i).getCount());
                if (maxPower < beaconPower){
                    index = i;
                    maxPower = beaconPower;

                }


            }
            whichLocation(emptyList.get(index));
            testView.setText("Biggest power "+ emptyList.get(index).getFloor() + " count " + count);




        }


    }

    //method determining, checkIn or checkOut location
    private void whichLocation(BeaconInfo beacon){

        if (checkedInLocation==null && checkedOutLocation ==null && !checkInUpdated){
            updateLocation(beacon,"CheckIn");
            checkInTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        }
        else if (checkedInLocation!=null && checkedOutLocation==null && checkedInLocation.getFloor() != beacon.getFloor()){

            updateLocation(beacon,"CheckOut");

            //stations are known, charge user
            chargeUser(checkedInLocation.getFloor(),checkedOutLocation.getFloor());

        }

    }

    private void updateLocation(BeaconInfo beacon,String type){



        switch (type){

            case "CheckIn":
                checkInLoc.setText(beacon.getFloor() +" Floor"+ " count "+count);
                checkedInLocation=beacon;
                checkInUpdated = true;
            break;
            case "CheckOut":
                checkOutloc.setText(beacon.getFloor()  +" Floor"+ " count "+count);
                checkedOutLocation=beacon;
            break;

            default:
                System.out.println("No such type");
                break;

        }

    }

    private void chargeUser(Integer startStation, Integer endStation){

        for (Station station: StationPrice.stations){

            if (station.getStartStation() == startStation && station.getEndStation() == endStation){
Double tripPrice = station.getPrice();

                //update history table
                updateHistoryTable(startStation,endStation,tripPrice);

                if (mUserDB.chargeUser(SaveSharedPreference.getUserName(getActivity()),tripPrice)){

                    ((updateBalanceFragment.toActivity) getActivity()).updateBalance();

                    Toast.makeText(getActivity().getApplicationContext(),"Trip finished from station: " +startStation +" to " + endStation + ". Price: " + tripPrice, Toast.LENGTH_LONG).show();
                    resetEverything();
                    startTripBtn.setEnabled(true);

                }
            }
            System.out.println("Start: " + station.getStartStation() +" end: " + station.getEndStation()+ " price " +station.getPrice());
        }

    }

    private void updateHistoryTable(Integer start, Integer end, double price){

        HistoryDB mHistoryDB = new HistoryDB();
        mHistoryDB.addTrip(new History(start,end,price,SaveSharedPreference.getUserName(getActivity()),checkInTime,new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())));

    }

    private void resetEverything(){

        checkedInLocation=null;
        checkInUpdated = false;
        checkInTime = null;
        checkedOutLocation = null;
        checkInLoc.setText("reset");
        checkOutloc.setText("reset");
        count = 0;

    }





}
