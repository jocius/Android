package joc.rejsekort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import joc.rejsekort.Database.UserDB;
import joc.rejsekort.HelpClasses.User;

public class RegisterActivity extends AppCompatActivity {

    private Button register;
    private TextView username, password;

    //Database of things - singleton
    private static UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) register.findViewById(R.id.submit);
        username = (TextView)username.findViewById(R.id.regUsername);
        password = (TextView) password.findViewById(R.id.regUsername);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((username.getText().length() > 0) && (password.getText().length() > 0)) {
                    User newUser = new User(
                            username.getText().toString().trim(),
                            password.getText().toString().trim());
                    userDB.addUser(newUser);
                    username.setText("");
                    password.setText("");
                }
            }
        });

    }
}
