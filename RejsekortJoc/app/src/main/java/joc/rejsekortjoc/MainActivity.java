package joc.rejsekortjoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.HelpClasses.User;

public class MainActivity extends AppCompatActivity {


    private Button toRegister;
    private Button toLogin;
    private TextView sUsername, sPassword;
//temp
    private static UserDB mUserDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        mUserDB = UserDB.get(this);


        sPassword = (TextView) findViewById(R.id.password);
        sUsername = (TextView) findViewById(R.id.username);
        toRegister = (Button) findViewById(R.id.toRegister);
        toLogin = (Button) findViewById(R.id.login);

        toRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(mUserDB.getUsers(sUsername.getText().toString().trim(),sPassword.getText().toString().trim())){

                    //go to main window
                }
            }
        });


    }
}
