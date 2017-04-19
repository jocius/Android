package joc.airport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.recognition.packets.Beacon;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button start;
    private TextView mInput;
    private List<Beacon> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.button1);
        mInput = (TextView) findViewById(R.id.hello);



        final MyApplication app  = new MyApplication();
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
             list = app.startIt();
                updateUI(list.get(0).toString());
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }

    public void updateUI(String input){

        mInput.setText(input);
    }


}
