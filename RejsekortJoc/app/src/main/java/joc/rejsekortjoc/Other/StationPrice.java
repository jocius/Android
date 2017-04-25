package joc.rejsekortjoc.Other;

import java.util.ArrayList;
import java.util.List;

import joc.rejsekortjoc.HelpClasses.Station;

/**
 * Created by justinas on 4/22/17.
 */

public class StationPrice {

    public static List<Station> stations = populateList();


    private static List<Station> populateList(){

        List<Station> list = new ArrayList<>();

        list.add(new Station(2,1,2.0));
        list.add(new Station(2,3,7.0));
        list.add(new Station(2,4,8.0));
        list.add(new Station(2,5,15.0));

        list.add(new Station(5,4,5.0));
        list.add(new Station(5,3,12.0));
        list.add(new Station(5,2,15.0));
        list.add(new Station(5,1,13.0));

        list.add(new Station(4,5,6.0));
        list.add(new Station(4,3,6.0));
        list.add(new Station(4,2,6.0));
        list.add(new Station(4,1,6.0));

        return list;

    }
}
