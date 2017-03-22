package joc.rejsekortjoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.Other.SaveSharedPreference;

public class LoginActivity extends AppCompatActivity {


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

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               String userName =  sUsername.getText().toString().trim();
                if(mUserDB.getUsers(userName,sPassword.getText().toString().trim())){


                    //Save username to preferences for persistent login
                    SaveSharedPreference ss = new SaveSharedPreference();
                    ss.setUserName(getApplicationContext(),userName);

                    //go to main menu
                    Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
                    LoginActivity.this.startActivity(intent);
                }
                else{

                    Toast.makeText(getApplicationContext(),"Username or/and password does not exists", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
