package joc.rejsekortjoc;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;


import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import joc.rejsekortjoc.Fragments.UserDataFragment;
import joc.rejsekortjoc.Fragments.checkInOutFragment;
import joc.rejsekortjoc.Fragments.updateBalanceFragment;
import joc.rejsekortjoc.Other.SaveSharedPreference;

public class MenuActivity extends FragmentActivity implements updateBalanceFragment.toActivity {



    private Fragment fragmentUserData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
            ifUserLoggedIn();








        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.userDataInMenuFragment);
        if (fragment == null) {
            fragment = new UserDataFragment();
            fm.beginTransaction()
                    .add(R.id.userDataInMenuFragment, fragment)
                    .commit(); }

        FragmentManager fm2 = getSupportFragmentManager();
        Fragment fragment2 = fm.findFragmentById(R.id.chekInOutFragment);
        if (fragment2 == null) {
            fragment2 = new checkInOutFragment();
            fm.beginTransaction()
                    .add(R.id.chekInOutFragment, fragment2)
                    .commit(); }



    }

    //overriding method due to persistent login, so that user pressed back button, wont be able to go back, if he logged out
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        updateBalance();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);


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

        }


    }
    //empty, to disable back button
    @Override
    public void onBackPressed() {

    }

    @Override
    public void updateBalance() {

        FragmentManager fm2 = getSupportFragmentManager();
        fragmentUserData= fm2.findFragmentById(R.id.userDataInMenuFragment);

        if(fragmentUserData!=null){((UserDataFragment) fragmentUserData).updateBalance();}

    }

}
