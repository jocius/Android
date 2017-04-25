package joc.rejsekortjoc.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import joc.rejsekortjoc.AccountActivity;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.MenuActivity;
import joc.rejsekortjoc.Other.SaveSharedPreference;
import joc.rejsekortjoc.R;

/**
 * Created by justinas on 4/25/17.
 */

public class ChangeUserFragment extends android.support.v4.app.Fragment  {


    private TextView pswField;
    private Button updateBtn;
    private UserDB mUserDB;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.change_user_fragment, container, false);

        pswField =(TextView) v.findViewById(R.id.updateNewPsw);
        updateBtn = (Button) v.findViewById(R.id.updateInfoBtn);

        mUserDB = UserDB.get(getActivity());
        updateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (pswField.getText().length()>0){

                    mUserDB.editUser(SaveSharedPreference.getUserName(getActivity()), pswField.getText().toString());
                    Toast.makeText(getActivity(),"Password Changed", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getActivity(),MenuActivity.class);
                    getActivity().startActivity(intent);

                    pswField.setText("");
                }
                else {
                    Toast.makeText(getActivity(),"Password cannot be less than 3 characters", Toast.LENGTH_LONG).show();

                }
            }

        });








        return v;
    }


}
