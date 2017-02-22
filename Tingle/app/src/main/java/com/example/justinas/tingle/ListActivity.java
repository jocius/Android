package com.example.justinas.tingle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    private ListView mListView;

    private static ThingsDB thingsDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        thingsDB= ThingsDB.get(this);


        mListView = (ListView) findViewById(R.id.thing_list_view);

        ArrayAdapter adapter = new ArrayAdapter<>(ListActivity.this,android.R.layout.simple_list_item_1, thingsDB.getThingsDB());
        mListView.setAdapter(adapter);

    }
}



