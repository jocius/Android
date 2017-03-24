package joc.rejsekortjoc.Database;

import android.content.Context;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import joc.rejsekortjoc.HelpClasses.Bank;
import joc.rejsekortjoc.HelpClasses.User;

/**
 * Created by justinas on 3/18/17.
 */


public class BankDB {

    private static Realm realm;
    private static BankDB mBankDB;


    public static BankDB get(Context context) {
        if (mBankDB == null) {
            realm = Realm.getDefaultInstance();
            mBankDB = new BankDB(context);
        }
        return mBankDB;
    }



    private void addUser(final Bank newAcc) {


        Bank user = realm.where(Bank.class).equalTo("mCardNo", newAcc.getCardNo()).findFirst();

        if (user != null) {

        } else {


            // Not exist
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(newAcc);
                }
            });

        }

    }

    // Fill database for testing purposes
    private BankDB(Context context) {
    /*Hack to add contents to an empty database */
        addUser(new Bank("John", "Johnson",0,1111,1111));
        addUser(new Bank("Ben", "Benson",10000,2222,2222));
        addUser(new Bank("Anne", "Regsen",500,4444,2222));
        addUser(new Bank("Blue", "Redson",70,3333,3333));

    }
    public String transferMoney(int cardNo, int secNo, double credit, String username){

        //checking if user information correct and has enough money, for requested amount
        if(checkIfExists(cardNo,secNo,credit)){

            Bank acc = realm.where(Bank.class).equalTo("mCardNo", cardNo).findFirst();
            User user = realm.where(User.class).equalTo("mUsername",username).findFirst();

            //if user and account is found, minus from a bank, and add to rejsekort user
            if (acc != null && user!=null) {
                realm.beginTransaction();
                acc.setCredit(acc.getCredit()-credit);
                user.setCredit(user.getCredit()+credit);
                realm.commitTransaction();
                return "OK";
            }
        }
        else{

            return "failBankInfo";
        }
        return "failTrans";

    }

    private Boolean checkIfExists(int cardNo, int secNo, double credit){


        // Build the query looking at all users:
        RealmQuery<Bank> query = realm.where(Bank.class);


//Checking if cardno and security number are matched and if the user has enough money
        query.equalTo("mCardNo", cardNo).equalTo("mSecNo",secNo).greaterThan("mCredit",credit);
//// Execute the query:
        RealmResults<Bank> result1 = query.findAll();

        if (result1.size()==0){return false;}
    return true;
    }
}
