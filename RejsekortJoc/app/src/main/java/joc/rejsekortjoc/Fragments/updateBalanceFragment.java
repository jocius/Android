package joc.rejsekortjoc.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import joc.rejsekortjoc.AccountActivity;
import joc.rejsekortjoc.Database.BankDB;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.HelpClasses.User;
import joc.rejsekortjoc.LoginActivity;
import joc.rejsekortjoc.Other.SaveSharedPreference;
import joc.rejsekortjoc.R;


/**
 * Created by justinas on 3/24/17.
 */

public class updateBalanceFragment  extends android.support.v4.app.Fragment {


    private Button mAdd;
    private TextView mSum,mSecNo,mCardNo;
    private static BankDB mBankDB;
    private Fragment mUserDataAccFragment;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.update_balance_fragment, container, false);

     //   ifUserLoggedIn();
        mBankDB = BankDB.get(getActivity());

        mSum = (TextView) v.findViewById(R.id.sumMoney);
        mSecNo = (TextView) v.findViewById(R.id.secCode);
        mCardNo = (TextView) v.findViewById(R.id.cardNo);

        mAdd = (Button) v.findViewById(R.id.addToAccountBtn);

        mAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((mSum.getText().length() > 0) && (mSecNo.getText().length() > 3) && (mCardNo.getText().length() > 3)) {

                    int money = Integer.parseInt( mSum.getText().toString() );
                    int cardNo = Integer.parseInt( mCardNo.getText().toString() );
                    int secNo = Integer.parseInt( mSecNo.getText().toString() );
                    String usr = SaveSharedPreference.getUserName(getActivity());


                    switch (mBankDB.transferMoney(cardNo,secNo,money,usr)){

                        case "OK":
                            Toast.makeText(getActivity().getApplicationContext(),"Transaction is complete", Toast.LENGTH_LONG).show();
                            mSum.setText("");
                            mSecNo.setText("");
                            mCardNo.setText("");
                            updateFragment();
                            break;
                        case "failBankInfo":
                            Toast.makeText(getActivity().getApplicationContext(),"Card or/and security numbers are incorrect, or there is not enough money in bank account", Toast.LENGTH_LONG).show();
                            break;
                        case "failTrans":
                            Toast.makeText(getActivity().getApplicationContext(),"Transaction is failed", Toast.LENGTH_LONG).show();
                            break;
                        default:
                            System.out.println("default...");
                            break;
                    }

                }


            }
        });







        return v;
    }
    private void ifUserLoggedIn(){

        if(SaveSharedPreference.getUserName(getActivity()).length() == 0)
        {

            //redirect to login activity
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            getActivity().startActivity(intent);
        }
        else
        {
            // Stay at the current activity.
        }



    }


    public void updateFragment() {
//        FragmentManager fm2 = getSupportFragmentManager();
//        mUserDataAccFragment = fm2.findFragmentById(R.id.userDataInAccFragment);
//        ((UserDataFragment) mUserDataAccFragment).updateCredit();

    }




}
