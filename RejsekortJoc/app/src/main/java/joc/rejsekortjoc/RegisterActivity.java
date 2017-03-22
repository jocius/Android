package joc.rejsekortjoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import joc.rejsekortjoc.Database.UserDB;
import joc.rejsekortjoc.HelpClasses.User;

public class RegisterActivity extends AppCompatActivity {

    private Button sSubmit;
    private TextView sUsername, sPassword;
    private static UserDB mUserDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sSubmit = (Button) findViewById(R.id.submit);
        sUsername = (TextView) findViewById(R.id.newUsername);
        sPassword = (TextView) findViewById(R.id.newPassword);

        mUserDB = UserDB.get(this);

        sSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((sUsername.getText().length() > 0) && (sPassword.getText().length() > 0)) {
                    User newUser = new User(
                            sUsername.getText().toString().trim(),
                            sPassword.getText().toString().trim());

                    if(mUserDB.addUser(newUser)){

                        Toast.makeText(getApplicationContext(),"User has been registered", Toast.LENGTH_LONG).show();
                        sUsername.setText("");
                        sPassword.setText("");
                    }
                    else{

                        Toast.makeText(getApplicationContext(),"Username already exists", Toast.LENGTH_LONG).show();

                    }




                }
            }
        });
    }
}
