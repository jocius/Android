package joc.rejsekort.HelpClasses;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by justinas on 3/18/17.
 */

public class User extends RealmObject {

    @PrimaryKey
    @Required
    private String username;
    @Required
    private String password;

    public User() {
        username="";
        password="";

    }
    public User(String username, String password) {
        username = username;
        password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
