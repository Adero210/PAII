package ceti.edu.paii.activities.listening.reading;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ceti.edu.paii.R;

public class Reading_2_Activity extends AppCompatActivity {

    Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_2_);

        buttonback = findViewById(R.id.button_back_activity_Reading_2);


        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Reading_Paragraph_Activity.class);
                startActivity(i);
            }
        });

    }
}
