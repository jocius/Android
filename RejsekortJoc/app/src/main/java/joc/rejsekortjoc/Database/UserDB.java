package joc.rejsekortjoc.Database;

import android.content.Context;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import joc.rejsekortjoc.HelpClasses.User;

/**
 * Created by justinas on 3/18/17.
 */


public class UserDB {

    private static Realm realm;
    private static UserDB sUserDB;


    public static UserDB get(Context context) {
        if (sUserDB == null) {
            realm = Realm.getDefaultInstance();
            sUserDB = new UserDB(context);
        }
        return sUserDB;
    }

    public OrderedRealmCollection<User> getUsersDB() {
        return realm.where(User.class).findAll();
    }

    public Boolean addUser(final User newUser) {

        User user = realm.where(User.class).equalTo("mUsername", newUser.getUsername()).findFirst();

        if (user != null) {
            return false;
        } else {
            // Not exist


            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(newUser);
                }});
            return true;
        }


    }

    public void deleteUser(User sUser) {
        final User user= sUser;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<User> rows=realm.where(User.class).equalTo("sUsername", user.getUsername()).findAll();
                if (rows.size() > 0) rows.get(0).deleteFromRealm();
            }});
    }
    // Fill database for testing purposes
    private UserDB(Context context) {
    /*Hack to add contents to an empty database */
//        addUser(new User("john", "john"));
//        addUser(new User("j", "j"));

    }
    public Boolean getUsers(String username, String password){


        // Build the query looking at all users:
        RealmQuery<User> query = realm.where(User.class);


// Add query conditions:
        query.equalTo("mUsername", username).equalTo("mPassword",password);
// Execute the query:
        RealmResults<User> result1 = query.findAll();

        if (result1.size()==0){return false;}
        return true;
    }
}
