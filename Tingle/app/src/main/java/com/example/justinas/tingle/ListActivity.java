package com.example.justinas.tingle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activityListFragmentContainer);
        if (fragment == null) {
            fragment = new TingleListFragment();
            fm.beginTransaction()
                    .add(R.id.activityListFragmentContainer, fragment)
                    .commit(); }
        System.out.println("Orientation - portrait");

    }
}



