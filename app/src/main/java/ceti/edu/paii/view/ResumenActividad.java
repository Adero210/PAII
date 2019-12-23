package ceti.edu.paii.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ceti.edu.paii.R;

public class ResumenActividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_actividad);
    }



    @Override
    public void onBackPressed(){
        return;
    }
}
