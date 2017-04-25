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
        list.add(new Station(2,5,20.0));
        list.add(new Station(5,2,30.0));
        list.add(new Station(2,3,20.0));
        list.add(new Station(2,4,20.0));
        list.add(new Station(5,4,5.0));
        list.add(new Station(5,3,22.0));
        list.add(new Station(5,2,23.0));
        list.add(new Station(5,1,24.0));
        list.add(new Station(4,5,6.0));

        return list;

    }
}
