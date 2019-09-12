package ceti.edu.paii.activities.listening.reading;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ceti.edu.paii.R;

public class Reading_3_Activity extends AppCompatActivity {
    Button buttonback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_3_);


        buttonback = findViewById(R.id.button_back_activity_Reading_3);


        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Reading_paragraph_2_Activity.class);
                startActivity(i);
            }
        });


    }

}
