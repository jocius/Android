package joc.rejsekortjoc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.HelpClasses.User;
import joc.rejsekortjoc.Other.SaveSharedPreference;
import joc.rejsekortjoc.R;

/**
 * Created by justinas on 3/24/17.
 */

public class UserDataFragment  extends android.support.v4.app.Fragment {


    private TextView mUsername,mCredit;
    private static UserDB mUserDB;

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


        return v;
    }

    private String getCredit(){

        User user = mUserDB.getUser(SaveSharedPreference.getUserName(getActivity()));
        if(user!=null){

            return Double.toString(user.getCredit());
        }

        return "";
    }
    public void updateCredit() { mCredit.setText(getCredit()); }
}
