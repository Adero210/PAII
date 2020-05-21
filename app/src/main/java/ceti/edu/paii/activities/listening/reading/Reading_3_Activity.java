package ceti.edu.paii.activities.listening.reading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Reading_3_Activity extends AppCompatActivity {

    private TextView textPregunta1;
    private TextView textPregunta3;
    private TextView textPregunta2;
    private TextView textPregunta4;
    private TextView textPregunta5;


    private String respuestac1 = "";
    private String respuestac2 = "";
    private String respuestac3 = "";
    private String respuestac4 = "";
    private String respuestac5 = "";


    private RadioButton true1;
    private RadioButton true2;
    private RadioButton true3;
    private RadioButton true4;
    private RadioButton true5;

    private RadioButton false1;
    private RadioButton false2;
    private RadioButton false3;
    private RadioButton false4;
    private RadioButton false5;
    
    private ImageView correctQ1;
    private ImageView correctQ2;
    private ImageView correctQ3;
    private ImageView correctQ4;
    private ImageView correctQ5;

    private static String URL_ACTR2 = comun.URL + "getActivity.php";

    private String boceto = "4";

    private ImageView incorrectQ1;
    private ImageView incorrectQ2;
    private ImageView incorrectQ3;
    private ImageView incorrectQ4;
    private ImageView incorrectQ5;
    

    private Button revisar;
    private Button continuar;

    private String curso;
    private String lesson;

    private  String numAletorio;

    int actHechas, cali;
    private String  calis, actHechasS;

    private ProgressDialog progressDialog;

    private int correctas = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_3_);
        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);
        if(actHechas<=8) {
            progressDialog = new ProgressDialog(Reading_3_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);




            numAletorio = "5";

            int lessonint = Integer.parseInt(lesson);
            if(lessonint == 1) lessonint = 21;


            if (curso.equals("Italiano")) {
                switch (lesson) {

                    case "1":
                        lessonint = 11;
                        break;
                    case "2":
                        lessonint = 12;
                        break;
                    case "3":
                        lessonint = 13;
                        break;
                    case "4":
                        lessonint = 14;
                        break;
                    case "5":
                        lessonint = 15;
                        break;
                    case "6":
                        lessonint = 16;
                        break;
                    case "7":
                        lessonint = 17;
                        break;
                    case "8":
                        lessonint = 18;
                        break;
                    case "9":
                        lessonint = 19;
                        break;
                    case "10":
                        lessonint = 20;
                        break;
                }
            }

            Log.i("DATAFROMSQL", "Curso" + curso);
            Log.i("DATAFROMSQL", "success" + (lessonint - 1));
            bringTheInfo(lessonint - 1, numAletorio);
            opcions();


        }else {
        Intent i = new Intent(Reading_3_Activity.this, ResumenActividad.class);
        String tipo = "Lectura";
        i.putExtra("curso",curso);
        i.putExtra("lesson",lesson);
        i.putExtra("tipo",tipo);
        i.putExtra("calificacion", String.valueOf(cali));
        startActivity(i);

    }

    }

    private void opcions() {
    }

    private void bringTheInfo(int i, String numAletorio) {
    }


    @Override
    public void onBackPressed(){
        return;
    }
}
