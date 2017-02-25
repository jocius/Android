package com.example.justinas.tingle;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by justinas on 2/25/17.
 */

public class TingleListFragment extends Fragment {

    private ListView mListView;

    private static ThingsDB thingsDB;
    private ArrayAdapter adapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //thingsDB= ThingsDB.get(getActivity());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activitylistfragment, container, false);

        thingsDB= ThingsDB.get(getActivity());


        mListView = (ListView) v.findViewById(R.id.thing_list_view);

         adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, thingsDB.getThingsDB());
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {


                showPopUp(position);

            }



        });
        return v;
    }

    void showPopUp(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete this item from List?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                 thingsDB.deleteThing(position);
                        Toast.makeText(getActivity(),"Item is deleted from the list", Toast.LENGTH_LONG).show();

                        adapter.notifyDataSetChanged();
                        adapter.notifyDataSetInvalidated();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void stateChange() { adapter.notifyDataSetChanged(); }
    }
