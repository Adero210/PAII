package ceti.edu.paii.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.writing.Writing_1_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.fragment.HomeFragment;

public class ResumenActividad extends AppCompatActivity {

    private int cali;
    private String calis;

    private String curso;
    private String lesson;

    private TextView textCal;
    private TextView numCal;
    private String tipo;


    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_actividad);
        calis = getIntent().getStringExtra("calificacion");
        cali  = Integer.valueOf(calis);
        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        tipo = getIntent().getStringExtra("tipo");
        textCal = findViewById(R.id.text_cal);
        numCal = findViewById(R.id.num_cal);
        back = findViewById(R.id.back);


        int lessonint = Integer.valueOf(lesson);

        int l = lessonint-1;

        if (curso.equals("Ingles")) {
            back.setText("back");
            textCal.setText("Score");

        } else if (curso.equals("Italiano")) {
            back.setText("ritorno");
            textCal.setText("punteggio");
        }

        Double cali2 = Double.valueOf((cali/80));
        String score = String.valueOf(cali2);

        Log.i("rederescrtedeca",score+ tipo
                + String.valueOf(l)+ comun.userNameLec );

        numCal.setText(score);
        opcBotones();
    }

    private void opcBotones() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResumenActividad.this, ContainerActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onBackPressed(){
        return;
    }
}
