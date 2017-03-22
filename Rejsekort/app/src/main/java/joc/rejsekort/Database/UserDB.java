package joc.rejsekort.Database;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import android.content.Context;

import io.realm.RealmResults;
import joc.rejsekort.HelpClasses.User;

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
    public void addUser(User thing) {
        final User user= thing; realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(user);
            }});
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
}
