package joc.rejsekortjoc;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by justinas on 3/18/17.
 */

public class RealmApp extends Application {
    @Override
public void onCreate() {
    super.onCreate();

        Realm.init(this);

//        RealmConfiguration config = new RealmConfiguration
//                .Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();


}
}

