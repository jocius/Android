package joc.rejsekortjoc.HelpClasses;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by justinas on 3/18/17.
 */

public class Bank extends RealmObject {


    private String mName=null;
    private String mSurname=null;

    public double getCredit() {
        return mCredit;
    }

    public void setCredit(double credit) {
        mCredit = credit;
    }

    private double mCredit = 0.00;

    public int getCardNo() {
        return mCardNo;
    }

    @PrimaryKey
    private int mCardNo = 0;
    private int mSecNo =0;

    public Bank(){}

    public Bank(String name, String surname,double credit,int cardNo,int secNo) {
        mName = name;
        mSurname = surname;
        mCredit = credit;
        mCardNo = cardNo;
        mSecNo = secNo;



    }



}
