package joc.rejsekortjoc.Fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import joc.rejsekortjoc.Database.HistoryDB;
import joc.rejsekortjoc.HelpClasses.History;
import joc.rejsekortjoc.Other.SaveSharedPreference;
import joc.rejsekortjoc.R;

/**
 * Created by justinas on 4/25/17.
 */

public class TripHistoryFragment extends android.support.v4.app.Fragment {

    private ListView mListView;

    private static HistoryDB mHistoryDB;
    private ArrayAdapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //thingsDB= ThingsDB.get(getActivity());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trip_history_list_fragment, container, false);

        mHistoryDB = new HistoryDB();
        History history = new History();

        mListView = (ListView) v.findViewById(R.id.tripHistory_list_view);

        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, history.toList(mHistoryDB.getTrips(SaveSharedPreference.getUserName(getActivity()))));
        mListView.setAdapter(adapter);

        return v;
    }



    public void stateChange() {
        //for this part it would be enough just adapter.notifyDataSetChanged();, however for some reasons,
        //it doesn't, there fore i have to reset adapter...
        History history = new History();
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, history.toList(mHistoryDB.getTrips(SaveSharedPreference.getUserName(getActivity()))));
        mListView.setAdapter(adapter);
        adapter.notifyDataSetInvalidated();
        adapter.notifyDataSetChanged();
    }
}
