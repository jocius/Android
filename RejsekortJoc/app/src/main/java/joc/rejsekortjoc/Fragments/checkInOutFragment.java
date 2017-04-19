package joc.rejsekortjoc.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import joc.rejsekortjoc.Database.BankDB;
import joc.rejsekortjoc.Other.SaveSharedPreference;
import joc.rejsekortjoc.R;

/**
 * Created by justinas on 4/18/17.
 */


public class checkInOutFragment  extends android.support.v4.app.Fragment {






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.checkinout_fragment, container, false);









        return v;
    }




}
