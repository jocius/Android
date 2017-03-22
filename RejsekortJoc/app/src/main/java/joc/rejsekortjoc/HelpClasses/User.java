package joc.rejsekortjoc.HelpClasses;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by justinas on 3/18/17.
 */

public class User extends RealmObject {

    @PrimaryKey

    private String mUsername=null;

    private String mPassword=null;

    public User() {
        mUsername ="";
        mPassword ="";

    }
    public User(String username, String password) {
        mUsername = username;
        mPassword = password;

    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }



    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }


}
