package joc.rejsekortjoc;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import joc.rejsekortjoc.Database.BankDB;
import joc.rejsekortjoc.Fragments.UserDataFragment;
import joc.rejsekortjoc.Other.SaveSharedPreference;

public class AccountActivity extends AppCompatActivity {

    private Button mAdd;
    private TextView mSum,mSecNo,mCardNo;
    private static BankDB mBankDB;
    private Fragment mUserDataAccFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ifUserLoggedIn();
        mBankDB = BankDB.get(this);

        mSum = (TextView) findViewById(R.id.sumMoney);
        mSecNo = (TextView) findViewById(R.id.secCode);
        mCardNo = (TextView) findViewById(R.id.cardNo);

        mAdd = (Button) findViewById(R.id.addToAccountBtn);

        mAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((mSum.getText().length() > 0) && (mSecNo.getText().length() > 3) && (mCardNo.getText().length() > 3)) {

                    int money = Integer.parseInt( mSum.getText().toString() );
                    int cardNo = Integer.parseInt( mCardNo.getText().toString() );
                    int secNo = Integer.parseInt( mSecNo.getText().toString() );
                    String usr = SaveSharedPreference.getUserName(AccountActivity.this);


                    switch (mBankDB.transferMoney(cardNo,secNo,money,usr)){

                        case "OK":
                            Toast.makeText(getApplicationContext(),"Transaction is complete", Toast.LENGTH_LONG).show();
                            mSum.setText("");
                            mSecNo.setText("");
                            mCardNo.setText("");
                            updateFragment();
                            break;
                        case "failBankInfo":
                            Toast.makeText(getApplicationContext(),"Card or/and security numbers are incorrect, or there is not enough money in bank account", Toast.LENGTH_LONG).show();
                            break;
                        case "failTrans":
                            Toast.makeText(getApplicationContext(),"Transaction is failed", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            System.out.println("default...");
                            break;
                    }

                }


            }
        });


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.accountFragment);
        if (fragment == null) {
            fragment = new UserDataFragment();
            fm.beginTransaction()
                    .add(R.id.accountFragment, fragment)
                    .commit(); }

    }

    private void ifUserLoggedIn(){

        if(SaveSharedPreference.getUserName(AccountActivity.this).length() == 0)
        {

            //redirect to login activity
            Intent intent = new Intent(AccountActivity.this,LoginActivity.class);
            AccountActivity.this.startActivity(intent);
        }
        else
        {
            // Stay at the current activity.
        }



    }


    public void updateFragment() {
        FragmentManager fm2 = getSupportFragmentManager();
        mUserDataAccFragment = fm2.findFragmentById(R.id.accountFragment);
        ((UserDataFragment) mUserDataAccFragment).updateCredit();

    }
}
