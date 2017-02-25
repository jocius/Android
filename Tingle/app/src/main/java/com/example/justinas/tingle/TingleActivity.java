package com.example.justinas.tingle;


import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class TingleActivity extends FragmentActivity implements TingleFragment.toActivity { // GUI variables

private Button mShowList;
    private Fragment fragmentListLand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mShowList = (Button)findViewById(R.id.showList);
            mShowList.setVisibility(View.INVISIBLE);

        }
        else {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.fragment_container);
            if (fragment == null) {
                fragment = new TingleFragment();
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit(); }
            System.out.println("Orientation - portrait");
        }


    }


    @Override
    public void stateChange() {
        FragmentManager fm2 = getSupportFragmentManager();
        fragmentListLand= fm2.findFragmentById(R.id.listActivityFragment);
        ((TingleListFragment) fragmentListLand).stateChange();
    }
}