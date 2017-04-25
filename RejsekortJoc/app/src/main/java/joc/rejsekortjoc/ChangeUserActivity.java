package joc.rejsekortjoc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import joc.rejsekortjoc.Fragments.ChangeUserFragment;
import joc.rejsekortjoc.Fragments.UserDataFragment;

/**
 * Created by justinas on 4/25/17.
 */


public class ChangeUserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_user_activity);



        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.changeUserFragmentContainer);
        if (fragment == null) {
            fragment = new ChangeUserFragment();
            fm.beginTransaction()
                    .add(R.id.changeUserFragmentContainer, fragment)
                    .commit(); }


        Fragment fragment2 = fm.findFragmentById(R.id.userDataInUserChangeFragment);
        if (fragment2 == null) {
            fragment2 = new UserDataFragment();
            fm.beginTransaction()
                    .add(R.id.userDataInUserChangeFragment, fragment2)
                    .commit(); }



    }




}