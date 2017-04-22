package joc.rejsekortjoc.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import joc.rejsekortjoc.AccountActivity;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.HelpClasses.User;
import joc.rejsekortjoc.LoginActivity;
import joc.rejsekortjoc.Other.SaveSharedPreference;
import joc.rejsekortjoc.R;

/**
 * Created by justinas on 3/24/17.
 */

public class UserDataFragment  extends android.support.v4.app.Fragment {


    private TextView mUsername,mCredit;
    private Button mPopButton,mLoggOut,mAddMoney;
    private static UserDB mUserDB;
    private PopupMenu popup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.userdata_fragment, container, false);

        mUserDB = mUserDB.get(getActivity());
        mUsername =(TextView) v.findViewById(R.id.usersUsernameTxt);
        mCredit =(TextView) v.findViewById(R.id.usersCreditTxt);

        mCredit.setText(getCredit());
        mUsername.setText(SaveSharedPreference.getUserName(getActivity()));


        //pop up menu
        mPopButton = (Button)v.findViewById(R.id.popButton);
        mPopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                popup = new PopupMenu(getActivity(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.popmenu, popup.getMenu());
                popup.show();



                //pop up action listeners
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.loggOut:
                            loggOut();
                                return true;
                            case R.id.addMoney:
                            toAddMoney();
                                item.setVisible(false);
                                return true;
                            default:
                                return false;
                        }
                    }



                });
            }
        });





        return v;
    }




    private String getCredit(){

        User user = mUserDB.getUser(SaveSharedPreference.getUserName(getActivity()));
        if(user!=null){

            return Double.toString(user.getCredit());
        }

        return "";
    }

    private void loggOut(){
        SaveSharedPreference.clearPref(getActivity().getApplicationContext());

                Intent intent = new Intent(getActivity(),LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);


    }
    private void toAddMoney(){

        Intent intent = new Intent(getActivity(),AccountActivity.class);
        getActivity().startActivity(intent);


    }

    public void updateBalance() { mCredit.setText(getCredit()); }
}
