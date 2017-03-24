package joc.rejsekortjoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import joc.rejsekortjoc.Database.BankDB;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.Other.SaveSharedPreference;

public class AccountActivity extends AppCompatActivity {

    private Button mAdd;
    private TextView mSum,mSecNo,mCardNo;
    private static BankDB mBankDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

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

                    String z = mBankDB.transferMoney(cardNo,secNo,money,usr);
                    switch (z){

                        case "OK":
                            Toast.makeText(getApplicationContext(),"Transaction is complete", Toast.LENGTH_LONG).show();
                            mSum.setText("");
                            mSecNo.setText("");
                            mCardNo.setText("");
                        case "failBankInfo":
                            Toast.makeText(getApplicationContext(),"Card or/and security numbers are incorrect, or there is not enough money in bank account", Toast.LENGTH_LONG).show();
                        case "failTrans":
                            Toast.makeText(getApplicationContext(),"Transaction is failed", Toast.LENGTH_LONG).show();
                        default:
                            System.out.println("default...");
                    }

                }


            }
        });
    }
}
