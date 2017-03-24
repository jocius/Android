package joc.rejsekortjoc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import joc.rejsekortjoc.Fragments.UserDataFragment;
import joc.rejsekortjoc.Other.SaveSharedPreference;

public class MenuActivity extends FragmentActivity {

    private Button mLoggOut;
    private Button mAddMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
            ifUserLoggedIn();

        mLoggOut = (Button) findViewById(R.id.loggOut);
        mLoggOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//clear out everything
                SaveSharedPreference.clearUserName(getApplicationContext());
                Intent intent = new Intent(MenuActivity.this,LoginActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });

        mAddMoney = (Button)findViewById(R.id.addMoney);
        mAddMoney.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,AccountActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.mainMenuFragment);
        if (fragment == null) {
            fragment = new UserDataFragment();
            fm.beginTransaction()
                    .add(R.id.mainMenuFragment, fragment)
                    .commit(); }



    }

    //overriding method due to persistent login, so that user pressed back button, wont be able to go back, if he logged out
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first


      ifUserLoggedIn();
    }

    private void ifUserLoggedIn(){

        if(SaveSharedPreference.getUserName(MenuActivity.this).length() == 0)
        {

            //redirect to login activity
            Intent intent = new Intent(MenuActivity.this,LoginActivity.class);
            MenuActivity.this.startActivity(intent);
        }
        else
        {
            // Stay at the current activity.
        }


    }
}
