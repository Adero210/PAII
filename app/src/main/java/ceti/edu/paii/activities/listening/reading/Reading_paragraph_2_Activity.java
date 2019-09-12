package ceti.edu.paii.activities.listening.reading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ceti.edu.paii.R;

public class Reading_paragraph_2_Activity extends AppCompatActivity {
    private Button buttoncont;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_paragraph_2_);


        buttoncont = findViewById(R.id.button_activity_reading_parrafo_2);


        buttoncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Reading_3_Activity.class);
                startActivity(i);
            }
        });
    }
}
