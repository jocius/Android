package joc.rejsekortjoc.Database;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import joc.rejsekortjoc.HelpClasses.History;
import joc.rejsekortjoc.HelpClasses.User;

/**
 * Created by justinas on 4/25/17.
 */

public class HistoryDB {

    private static Realm realm = Realm.getDefaultInstance();
    private static HistoryDB mHistoryDB;

    public  HistoryDB () {
        if (mHistoryDB == null) {
            //realm = Realm.getDefaultInstance();
          //  mHistoryDB = new HistoryDB(context);
        }
       // return mHistoryDB;

    }
    // Fill database for testing purposes
    private HistoryDB(Context context) {
    /*Hack to add contents to an empty database */
//        addUser(new User("john", "john"));
//        addUser(new User("j", "j"));

    }

    public void addTrip(final History history) {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(history);
                }});



    }
    public List<History> getTrips(String username){

        List<History> trips = realm.where(History.class).equalTo("user",username).findAll();


        return trips;
    }
}


