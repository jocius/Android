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

public class updateBalanceFragment  extends android.support.v4.app.Fragment {


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






        return v;
    }




}
