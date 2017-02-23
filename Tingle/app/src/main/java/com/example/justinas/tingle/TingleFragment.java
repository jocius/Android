package com.example.justinas.tingle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by justinas on 2/23/17.
 */

public class TingleFragment extends Fragment {

    private Button addThing, showThing;

    private TextView lastAdded;
    private TextView newWhat, newWhere;
    private static ThingsDB thingsDB;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thingsDB= ThingsDB.get(getActivity());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tingle, container, false);

        //Accessing GUI element
        lastAdded= (TextView) v.findViewById(R.id.last_thing);
        updateUI();


        showThing = (Button) v.findViewById(R.id.showList);
        showThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), ListActivity.class);
                startActivity(i);
            }
        });



        addThing= (Button) v.findViewById(R.id.add_button);
// Textfields for describing a thing
        newWhat= (TextView) v.findViewById(R.id.what_text);
        newWhere= (TextView) v.findViewById(R.id.where_text);
        // view products click event
        addThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((newWhat.getText().length()>0) && (newWhere.getText().length()>0 )){


                    thingsDB.addThing(new Thing( newWhat.getText().toString(), newWhere.getText().toString()));

                    newWhat.setText("");
                    newWhere.setText("");
                    updateUI();
                }
            }
        });


        return v;
    }


    //// This method fill a few things into ThingsDB for testing
//private void fillThingsDB() {
//    thingsDB.add(new Thing("Android Pnone", "Desk"));
//    thingsDB.add(new Thing("Big Nerd book", "Desk"));
//}
    private void updateUI(){
        int s= thingsDB.size();
        if (s>0) lastAdded.setText(thingsDB.get(s-1).toString());
    }
}
