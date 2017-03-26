package joc.rejsekortjoc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import joc.rejsekortjoc.Fragments.UserDataFragment;
import joc.rejsekortjoc.Fragments.updateBalanceFragment;

public class AccountActivity extends FragmentActivity implements updateBalanceFragment.toActivity{

    private Fragment fragmentUserData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);



        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.userDataInAccFragment);
        if (fragment == null) {
            fragment = new UserDataFragment();
            fm.beginTransaction()
                    .add(R.id.userDataInAccFragment, fragment)
                    .commit(); }

        FragmentManager fm2 = getSupportFragmentManager();
        Fragment fragment2 = fm.findFragmentById(R.id.updateAccFragment);
        if (fragment2 == null) {
            fragment2 = new updateBalanceFragment();
            fm2.beginTransaction()
                    .add(R.id.updateAccFragment, fragment2)
                    .commit(); }

    }

    @Override
    public void updateBalance() {
        FragmentManager fm2 = getSupportFragmentManager();
        fragmentUserData= fm2.findFragmentById(R.id.userDataInAccFragment);
        ((UserDataFragment) fragmentUserData).updateBalance();
    }


}
