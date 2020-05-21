package ceti.edu.paii.activities.listening.reading;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;

import static android.view.View.GONE;

public class Reading_2_Activity extends AppCompatActivity {

    private Button buttonback;
    private TextView textpregunta1;
    private TextView textpregunta3;
    private TextView textpregunta2;

    private Button btnopcA1;
    private Button btnopcB1;
    private Button btnopcC1;
    private Button btnopcD1;

    private Button btnCheckopcA1;
    private Button btnCheckopcB1;
    private Button btnCheckopcC1;
    private Button btnCheckopcD1;

    private Button btnopcA2;
    private Button btnopcB2;
    private Button btnopcC2;
    private Button btnopcD2;

    private Button btnCheckopcA2;
    private Button btnCheckopcB2;
    private Button btnCheckopcC2;
    private Button btnCheckopcD2;

    private Button btnopcA3;
    private Button btnopcB3;
    private Button btnopcC3;
    private Button btnopcD3;

    private Button btnCheckopcA3;
    private Button btnCheckopcB3;
    private Button btnCheckopcC3;
    private Button btnCheckopcD3;

    private Button revisar;
    private Button continuar;

    private String curso;
    private String lesson;

    private String tipo;

    int actHechas, cali;
    private String b1,b2,b3,b4, calis, actHechasS;

    private String respuestaSys1 = "";
    private String respuestaSys2 = "";
    private String respuestaSys3 = "";

    private String respuestaSel1 = "";
    private String respuestaSel2 = "";
    private String respuestaSel3 = "";

    private String respuestac1 = "";
    private String respuestac2 = "";
    private String respuestac3 = "";
    private String respuestac4 = "";
    private String respuestac5 = "";

    private MediaPlayer mediaPlayer,incorrect;

    private int correctas = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_2_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        b1 = getIntent().getStringExtra("boceto1");
        b2 = getIntent().getStringExtra("boceto2");
        b3 = getIntent().getStringExtra("boceto3");
        b4 = getIntent().getStringExtra("boceto4");
        tipo = getIntent().getStringExtra("tipo");
        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);


        mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
        incorrect = MediaPlayer.create(this,R.raw.wrong);

        textpregunta1 = findViewById(R.id.prgunta_1_reading_2);
        textpregunta2 = findViewById(R.id.prgunta_2_reading_2);
        textpregunta3 = findViewById(R.id.prgunta_3_reading_2);

        btnopcA1 = findViewById(R.id.opcion1_1_activity_Reading_2);
        btnopcB1 = findViewById(R.id.opcion2_1_activity_Reading_2);
        btnopcC1 = findViewById(R.id.opcion3_1_activity_Reading_2);
        btnopcD1 = findViewById(R.id.opcion4_1_activity_Reading_2);

        btnCheckopcA1 = findViewById(R.id.opcion1_1_check_activity_Reading_2);
        btnCheckopcB1 = findViewById(R.id.opcion2_1_check_activity_Reading_2);
        btnCheckopcC1 = findViewById(R.id.opcion3_1_check_activity_Reading_2);
        btnCheckopcD1 = findViewById(R.id.opcion4_1_check_activity_Reading_2);

        btnopcA2 = findViewById(R.id.opcion1_2_activity_Reading_2);
        btnopcB2 = findViewById(R.id.opcion2_2_activity_Reading_2);
        btnopcC2 = findViewById(R.id.opcion3_2_activity_Reading_2);
        btnopcD2 = findViewById(R.id.opcion4_2_activity_Reading_2);

        btnCheckopcA2 = findViewById(R.id.opcion1_2_check_activity_Reading_2);
        btnCheckopcB2 = findViewById(R.id.opcion2_2_check_activity_Reading_2);
        btnCheckopcC2 = findViewById(R.id.opcion3_2_check_activity_Reading_2);
        btnCheckopcD2 = findViewById(R.id.opcion4_2_check_activity_Reading_2);

        btnopcA3 = findViewById(R.id.opcion1_3_activity_Reading_2);
        btnopcB3 = findViewById(R.id.opcion2_3_activity_Reading_2);
        btnopcC3 = findViewById(R.id.opcion3_3_activity_Reading_2);
        btnopcD3 = findViewById(R.id.opcion4_3_activity_Reading_2);

        btnCheckopcA3 = findViewById(R.id.opcion1_3_check_activity_Reading_2);
        btnCheckopcB3 = findViewById(R.id.opcion2_3_check_activity_Reading_2);
        btnCheckopcC3 = findViewById(R.id.opcion3_3_check_activity_Reading_2);
        btnCheckopcD3 = findViewById(R.id.opcion4_3_check_activity_Reading_2);

        revisar = findViewById(R.id.button_activity_Reading_2);
        continuar = findViewById(R.id.button_continuar_activity_Reading_2);

        buttonback = findViewById(R.id.button_back_activity_Reading_2);



        String pregunta1 = getIntent().getStringExtra("pregunta1");
        String pregunta2 = getIntent().getStringExtra("pregunta2");
        String pregunta3 = getIntent().getStringExtra("pregunta3");
        String pregunta4 = getIntent().getStringExtra("pregunta4");
        String pregunta5 = getIntent().getStringExtra("pregunta5");

        respuestac1 = getIntent().getStringExtra("respuesta1c");
        respuestac2 = getIntent().getStringExtra("respuesta2c");
        respuestac3 = getIntent().getStringExtra("respuesta3c");
        respuestac4 = getIntent().getStringExtra("respuesta4c");
        respuestac5 = getIntent().getStringExtra("respuesta5c");


        String opcA1 = getIntent().getStringExtra("OpcA1");
        String opcA2 = getIntent().getStringExtra("OpcA2");
        String opcA3 = getIntent().getStringExtra("OpcA3");
        String opcA4 = getIntent().getStringExtra("OpcA4");
        String opcA5 = getIntent().getStringExtra("OpcA5");

        String opcB1 = getIntent().getStringExtra("OpcB1");
        String opcB2 = getIntent().getStringExtra("OpcB2");
        String opcB3 = getIntent().getStringExtra("OpcB3");
        String opcB4 = getIntent().getStringExtra("OpcB4");
        String opcB5 = getIntent().getStringExtra("OpcB5");

        String opcC1 = getIntent().getStringExtra("OpcC1");
        String opcC2 = getIntent().getStringExtra("OpcC2");
        String opcC3 = getIntent().getStringExtra("OpcC3");
        String opcC4 = getIntent().getStringExtra("OpcC4");
        String opcC5 = getIntent().getStringExtra("OpcC5");

        String opcD1 = getIntent().getStringExtra("OpcD1");
        String opcD2 = getIntent().getStringExtra("OpcD2");
        String opcD3 = getIntent().getStringExtra("OpcD3");
        String opcD4 = getIntent().getStringExtra("OpcD4");
        String opcD5 = getIntent().getStringExtra("OpcD5");



        String num = "";

        int pos;
        int nCartas = 5;
        Stack< Integer > pCartas = new Stack < Integer > ();
        for (int i = 0; i < nCartas ; i++) {
            pos = (int) Math.floor(Math.random() * nCartas );
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nCartas );
            }

            pCartas.push(pos);
        }

        Log.i("Numeros",pCartas.toString());

        String n [] = pCartas.toString().split(" ");
        n[0] = n[0].replace("[","");

        Log.i("numeroshhh",n[0]);
        Log.i("numeroshhh",n[1]);

        setPreguntaUno(n[0],pregunta1,pregunta2,pregunta3,pregunta4,pregunta5,opcA1,opcB1,opcC1,opcD1,opcA2,opcB2,opcC2,opcD2,opcA3,opcB3,opcC3,opcD3,opcA4,opcB4,opcC4,opcD4,opcA5,opcB5,opcC5,opcD5);
        setPreguntados(n[1],pregunta1,pregunta2,pregunta3,pregunta4,pregunta5,opcA1,opcB1,opcC1,opcD1,opcA2,opcB2,opcC2,opcD2,opcA3,opcB3,opcC3,opcD3,opcA4,opcB4,opcC4,opcD4,opcA5,opcB5,opcC5,opcD5);
        setPreguntTres(n[2],pregunta1,pregunta2,pregunta3,pregunta4,pregunta5,opcA1,opcB1,opcC1,opcD1,opcA2,opcB2,opcC2,opcD2,opcA3,opcB3,opcC3,opcD3,opcA4,opcB4,opcC4,opcD4,opcA5,opcB5,opcC5,opcD5);

        int cursoInt = Integer.parseInt(lesson);

        if(cursoInt > 6 && cursoInt < 10 && cursoInt > 16){
            buttonback.setVisibility(GONE);
        }

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        setButtons();

    }

    private void setButtons() {
        btnopcA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnopcA1.setVisibility(GONE);
                btnopcB1.setVisibility(View.VISIBLE);
                btnopcC1.setVisibility(View.VISIBLE);
                btnopcD1.setVisibility(View.VISIBLE);

                btnCheckopcA1.setVisibility(View.VISIBLE);
                btnCheckopcB1.setVisibility(GONE);
                btnCheckopcC1.setVisibility(GONE);
                btnCheckopcD1.setVisibility(GONE);
                respuestaSel1 = "A";

            }
        });
        btnopcB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcB1.setVisibility(GONE);
                btnopcA1.setVisibility(View.VISIBLE);
                btnopcC1.setVisibility(View.VISIBLE);
                btnopcD1.setVisibility(View.VISIBLE);

                btnCheckopcB1.setVisibility(View.VISIBLE);
                btnCheckopcA1.setVisibility(GONE);
                btnCheckopcC1.setVisibility(GONE);
                btnCheckopcD1.setVisibility(GONE);
                respuestaSel1 = "B";


            }
        });

        btnopcC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcC1.setVisibility(GONE);
                btnopcA1.setVisibility(View.VISIBLE);
                btnopcB1.setVisibility(View.VISIBLE);
                btnopcD1.setVisibility(View.VISIBLE);

                btnCheckopcC1.setVisibility(View.VISIBLE);
                btnCheckopcA1.setVisibility(GONE);
                btnCheckopcB1.setVisibility(GONE);
                btnCheckopcD1.setVisibility(GONE);
                respuestaSel1 = "C";


            }
        });

        btnopcD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcD1.setVisibility(GONE);
                btnopcA1.setVisibility(View.VISIBLE);
                btnopcC1.setVisibility(View.VISIBLE);
                btnopcB1.setVisibility(View.VISIBLE);

                btnCheckopcD1.setVisibility(View.VISIBLE);
                btnCheckopcA1.setVisibility(GONE);
                btnCheckopcC1.setVisibility(GONE);
                btnCheckopcB1.setVisibility(GONE);
                respuestaSel1 = "D";

            }
        });

        btnopcA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnopcA2.setVisibility(GONE);
                btnopcB2.setVisibility(View.VISIBLE);
                btnopcC2.setVisibility(View.VISIBLE);
                btnopcD2.setVisibility(View.VISIBLE);

                btnCheckopcA2.setVisibility(View.VISIBLE);
                btnCheckopcB2.setVisibility(GONE);
                btnCheckopcC2.setVisibility(GONE);
                btnCheckopcD2.setVisibility(GONE);

                respuestaSel2 = "A";


            }
        });
        btnopcB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcB2.setVisibility(GONE);
                btnopcA2.setVisibility(View.VISIBLE);
                btnopcC2.setVisibility(View.VISIBLE);
                btnopcD2.setVisibility(View.VISIBLE);

                btnCheckopcB2.setVisibility(View.VISIBLE);
                btnCheckopcA2.setVisibility(GONE);
                btnCheckopcC2.setVisibility(GONE);
                btnCheckopcD2.setVisibility(GONE);
                respuestaSel2 = "B";


            }
        });

        btnopcC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcC2.setVisibility(GONE);
                btnopcA2.setVisibility(View.VISIBLE);
                btnopcB2.setVisibility(View.VISIBLE);
                btnopcD2.setVisibility(View.VISIBLE);

                btnCheckopcC2.setVisibility(View.VISIBLE);
                btnCheckopcA2.setVisibility(GONE);
                btnCheckopcB2.setVisibility(GONE);
                btnCheckopcD2.setVisibility(GONE);
                respuestaSel2 = "C";


            }
        });

        btnopcD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcD2.setVisibility(GONE);
                btnopcA2.setVisibility(View.VISIBLE);
                btnopcC2.setVisibility(View.VISIBLE);
                btnopcB2.setVisibility(View.VISIBLE);

                btnCheckopcD2.setVisibility(View.VISIBLE);
                btnCheckopcA2.setVisibility(GONE);
                btnCheckopcC2.setVisibility(GONE);
                btnCheckopcB2.setVisibility(GONE);
                respuestaSel2 = "D";


            }
        });

        btnopcA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnopcA3.setVisibility(GONE);
                btnopcB3.setVisibility(View.VISIBLE);
                btnopcC3.setVisibility(View.VISIBLE);
                btnopcD3.setVisibility(View.VISIBLE);

                btnCheckopcA3.setVisibility(View.VISIBLE);
                btnCheckopcB3.setVisibility(GONE);
                btnCheckopcC3.setVisibility(GONE);
                btnCheckopcD3.setVisibility(GONE);
                respuestaSel3 = "A";


            }
        });
        btnopcB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcB3.setVisibility(GONE);
                btnopcA3.setVisibility(View.VISIBLE);
                btnopcC3.setVisibility(View.VISIBLE);
                btnopcD3.setVisibility(View.VISIBLE);

                btnCheckopcB3.setVisibility(View.VISIBLE);
                btnCheckopcA3.setVisibility(GONE);
                btnCheckopcC3.setVisibility(GONE);
                btnCheckopcD3.setVisibility(GONE);
                respuestaSel3 = "B";


            }
        });

        btnopcC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcC3.setVisibility(GONE);
                btnopcA3.setVisibility(View.VISIBLE);
                btnopcB3.setVisibility(View.VISIBLE);
                btnopcD3.setVisibility(View.VISIBLE);

                btnCheckopcC3.setVisibility(View.VISIBLE);
                btnCheckopcA3.setVisibility(GONE);
                btnCheckopcB3.setVisibility(GONE);
                btnCheckopcD3.setVisibility(GONE);
                respuestaSel3 = "C";


            }
        });

        btnopcD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnopcD3.setVisibility(GONE);
                btnopcA3.setVisibility(View.VISIBLE);
                btnopcC3.setVisibility(View.VISIBLE);
                btnopcB3.setVisibility(View.VISIBLE);

                btnCheckopcD3.setVisibility(View.VISIBLE);
                btnCheckopcA3.setVisibility(GONE);
                btnCheckopcC3.setVisibility(GONE);
                btnCheckopcB3.setVisibility(GONE);
                respuestaSel3 = "D";


            }
        });

        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                revisarPreguntaUno();
                revisarPreguntaDos();
                revisarPreguntaTres();

                Log.i("correctas", String.valueOf(correctas));

                if(correctas == 3){
                    mediaPlayer.start();
                    cali = cali + 100;
                }else {
                    incorrect.start();
                    cali = cali + 0;

                }

                revisar.setVisibility(GONE);
                buttonback.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actHechas++;
                String num ="";
                num = comun.aleatorio(4);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Reading_2_Activity.this, Reading_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);
                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        i.putExtra("boceto1",b1);
                        i.putExtra("boceto2",b2);
                        i.putExtra("boceto3",b3);
                        i.putExtra("boceto4",b4);
                        startActivity(i);
                        break;
                    case "1":
                        Intent intent = new Intent(Reading_2_Activity.this, Reading_Paragraph_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);
                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1);
                        intent.putExtra("boceto2",b2);
                        intent.putExtra("boceto3",b3);
                        intent.putExtra("boceto4",b4);
                        startActivity(intent);
                        break;
                    case "2":
                        Intent intent1 = new Intent(Reading_2_Activity.this, Reading_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);
                        intent1.putExtra("calificacion",String.valueOf(cali));
                        intent1.putExtra("actividad",String.valueOf(actHechas));
                        intent1.putExtra("boceto1",b1);
                        intent1.putExtra("boceto2",b2);
                        intent1.putExtra("boceto3",b3);
                        intent1.putExtra("boceto4",b4);
                        startActivity(intent1);
                        break;
                    case "3":
                        Intent intent2 = new Intent(Reading_2_Activity.this, Reading_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);
                        intent2.putExtra("calificacion",String.valueOf(cali));
                        intent2.putExtra("actividad",String.valueOf(actHechas));
                        intent2.putExtra("boceto1",b1);
                        intent2.putExtra("boceto2",b2);
                        intent2.putExtra("boceto3",b3);
                        intent2.putExtra("boceto4",b4);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }


    private void revisarPreguntaTres() {


        String opA = btnCheckopcA3.getText().toString();
        String opB = btnCheckopcB3.getText().toString();
        String opC = btnCheckopcC3.getText().toString();
        String opD = btnCheckopcD3.getText().toString();
        switch (respuestaSel3){
            case "A":
                switch (respuestaSys3){
                    case "1":
                        if(opA.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opA.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opA.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opA.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opA.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "B":
                switch (respuestaSys3){
                    case "1":
                        if(opB.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opB.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opB.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opB.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opB.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "C":
                switch (respuestaSys3){
                    case "1":
                        if(opC.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opC.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opC.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opC.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opC.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "D":
                switch (respuestaSys3){
                    case "1":
                        if(opD.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");
                        }
                        break;
                    case "2":
                        if(opD.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opD.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opD.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opD.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;
        }
    }

    private void revisarPreguntaDos() {

        String opA = btnCheckopcA2.getText().toString();
        String opB = btnCheckopcB2.getText().toString();
        String opC = btnCheckopcC2.getText().toString();
        String opD = btnCheckopcD2.getText().toString();
        switch (respuestaSel2){
            case "A":
                switch (respuestaSys2){
                    case "1":
                        if(opA.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opA.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opA.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opA.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opA.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "B":
                switch (respuestaSys2){
                    case "1":
                        if(opB.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opB.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opB.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opB.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opB.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "C":
                switch (respuestaSys2){
                    case "1":
                        if(opC.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opC.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opC.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opC.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opC.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "D":
                switch (respuestaSys2){
                    case "1":
                        if(opD.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");
                     }
                        break;
                    case "2":
                        if(opD.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opD.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opD.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opD.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;
        }
    }

    private void revisarPreguntaUno() {

        String opA = btnCheckopcA1.getText().toString();
        String opB = btnCheckopcB1.getText().toString();
        String opC = btnCheckopcC1.getText().toString();
        String opD = btnCheckopcD1.getText().toString();

        switch (respuestaSel1){
            case "A":
                switch (respuestaSys1){
                    case "1":
                        if(opA.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opA.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opA.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opA.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opA.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "B":
                switch (respuestaSys1){
                    case "1":
                        if(opB.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opB.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opB.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opB.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opB.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "C":
                switch (respuestaSys1){
                    case "1":
                        if(opC.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opC.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opC.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opC.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opC.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;

            case "D":
                switch (respuestaSys1){
                    case "1":
                        if(opD.equals(respuestac1)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "2":
                        if(opD.equals(respuestac2)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "3":
                        if(opD.equals(respuestac3)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "4":
                        if(opD.equals(respuestac4)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                    case "5":
                        if(opD.equals(respuestac5)){

                            Log.i("Correcta","Correcta");
                            correctas++;
                        }else{
                            Log.i("InCorrecta","inCorrecta");

                        }
                        break;
                }
                break;
        }

    }

    private void setPreguntTres(String s, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String opcA1, String opcB1, String opcC1, String opcD1
            , String opcA2, String opcB2, String opcC2, String opcD2, String opcA3, String opcB3, String opcC3, String opcD3, String opcA4, String opcB4, String opcC4, String opcD4
            , String opcA5, String opcB5, String opcC5, String opcD5) {
        switch (s){

            case "0,":
                textpregunta3.setText(pregunta1);
                btnopcA3.setText(opcA1);
                btnopcB3.setText(opcB1);
                btnopcC3.setText(opcC1);
                btnopcD3.setText(opcD1);
                btnCheckopcA3.setText(opcA1);
                btnCheckopcB3.setText(opcB1);
                btnCheckopcC3.setText(opcC1);
                btnCheckopcD3.setText(opcD1);
                respuestaSys3 = "1";

                break;
            case "1,":
                textpregunta3.setText(pregunta2);
                btnopcA3.setText(opcA2);
                btnopcB3.setText(opcB2);
                btnopcC3.setText(opcC2);
                btnopcD3.setText(opcD2);
                btnCheckopcA3.setText(opcA2);
                btnCheckopcB3.setText(opcB2);
                btnCheckopcC3.setText(opcC2);
                btnCheckopcD3.setText(opcD2);
                respuestaSys3 = "2";

                break;
            case "2,":
                textpregunta3.setText(pregunta3);
                btnopcA3.setText(opcA3);
                btnopcB3.setText(opcB3);
                btnopcC3.setText(opcC3);
                btnopcD3.setText(opcD3);
                btnCheckopcA3.setText(opcA3);
                btnCheckopcB3.setText(opcB3);
                btnCheckopcC3.setText(opcC3);
                btnCheckopcD3.setText(opcD3);
                respuestaSys3 = "3";

                break;
            case "3,":
                textpregunta3.setText(pregunta4);
                btnopcA3.setText(opcA4);
                btnopcB3.setText(opcB4);
                btnopcC3.setText(opcC4);
                btnopcD3.setText(opcD4);
                btnCheckopcA3.setText(opcA4);
                btnCheckopcB3.setText(opcB4);
                btnCheckopcC3.setText(opcC4);
                btnCheckopcD3.setText(opcD4);
                respuestaSys3 = "4";

                break;
            case "4,":
                textpregunta3.setText(pregunta5);
                btnopcA3.setText(opcA5);
                btnopcB3.setText(opcB5);
                btnopcC3.setText(opcC5);
                btnopcD3.setText(opcD5);
                btnCheckopcA3.setText(opcA5);
                btnCheckopcB3.setText(opcB5);
                btnCheckopcC3.setText(opcC5);
                btnCheckopcD3.setText(opcD5);
                respuestaSys3 = "5";

                break;

        }
    }

    private void setPreguntados(String s, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String opcA1, String opcB1, String opcC1, String opcD1
            , String opcA2, String opcB2, String opcC2, String opcD2, String opcA3, String opcB3, String opcC3, String opcD3, String opcA4, String opcB4, String opcC4, String opcD4
            , String opcA5, String opcB5, String opcC5, String opcD5) {
        switch (s){

            case "0,":
                textpregunta2.setText(pregunta1);
                btnopcA2.setText(opcA1);
                btnopcB2.setText(opcB1);
                btnopcC2.setText(opcC1);
                btnopcD2.setText(opcD1);
                btnCheckopcA2.setText(opcA1);
                btnCheckopcB2.setText(opcB1);
                btnCheckopcC2.setText(opcC1);
                btnCheckopcD2.setText(opcD1);
                respuestaSys2 = "1";

                break;
            case "1,":
                textpregunta2.setText(pregunta2);
                btnopcA2.setText(opcA2);
                btnopcB2.setText(opcB2);
                btnopcC2.setText(opcC2);
                btnopcD2.setText(opcD2);
                btnCheckopcA2.setText(opcA2);
                btnCheckopcB2.setText(opcB2);
                btnCheckopcC2.setText(opcC2);
                btnCheckopcD2.setText(opcD2);
                respuestaSys2 = "2";

                break;
            case "2,":
                textpregunta2.setText(pregunta3);
                btnopcA2.setText(opcA3);
                btnopcB2.setText(opcB3);
                btnopcC2.setText(opcC3);
                btnopcD2.setText(opcD3);
                btnCheckopcA2.setText(opcA3);
                btnCheckopcB2.setText(opcB3);
                btnCheckopcC2.setText(opcC3);
                btnCheckopcD2.setText(opcD3);
                respuestaSys2 = "3";

                break;
            case "3,":
                textpregunta2.setText(pregunta4);
                btnopcA2.setText(opcA4);
                btnopcB2.setText(opcB4);
                btnopcC2.setText(opcC4);
                btnopcD2.setText(opcD4);
                btnCheckopcA2.setText(opcA4);
                btnCheckopcB2.setText(opcB4);
                btnCheckopcC2.setText(opcC4);
                btnCheckopcD2.setText(opcD4);
                respuestaSys2 = "4";

                break;
            case "4,":
                textpregunta2.setText(pregunta5);
                btnopcA2.setText(opcA5);
                btnopcB2.setText(opcB5);
                btnopcC2.setText(opcC5);
                btnopcD2.setText(opcD5);
                btnCheckopcA2.setText(opcA5);
                btnCheckopcB2.setText(opcB5);
                btnCheckopcC2.setText(opcC5);
                btnCheckopcD2.setText(opcD5);
                respuestaSys2 = "5";

                break;

        }
    }

    private void setPreguntaUno(String s1, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String s, String opcA1, String opcB1, String opcC1, String opcD1
            , String opcA2, String opcB2, String opcC2, String opcD2, String opcA3, String opcB3, String opcC3, String opcD3, String opcA4, String opcB4, String opcC4, String opcD4
            , String opcA5, String opcB5, String opcC5, String opcD5) {
        switch (s1){

            case "0,":
                textpregunta1.setText(pregunta1);
                btnopcA1.setText(opcA1);
                btnopcB1.setText(opcB1);
                btnopcC1.setText(opcC1);
                btnopcD1.setText(opcD1);
                btnCheckopcA1.setText(opcA1);
                btnCheckopcB1.setText(opcB1);
                btnCheckopcC1.setText(opcC1);
                btnCheckopcD1.setText(opcD1);
                respuestaSys1 = "1";


                break;
            case "1,":
                textpregunta1.setText(pregunta2);
                btnopcA1.setText(opcA2);
                btnopcB1.setText(opcB2);
                btnopcC1.setText(opcC2);
                btnopcD1.setText(opcD2);
                btnCheckopcA1.setText(opcA2);
                btnCheckopcB1.setText(opcB2);
                btnCheckopcC1.setText(opcC2);
                btnCheckopcD1.setText(opcD2);

                respuestaSys1 = "2";

                break;
            case "2,":
                textpregunta1.setText(pregunta3);
                btnopcA1.setText(opcA3);
                btnopcB1.setText(opcB3);
                btnopcC1.setText(opcC3);
                btnopcD1.setText(opcD3);
                btnCheckopcA1.setText(opcA3);
                btnCheckopcB1.setText(opcB3);
                btnCheckopcC1.setText(opcC3);
                btnCheckopcD1.setText(opcD3);
                respuestaSys1 = "3";
                break;
            case "3,":
                textpregunta1.setText(pregunta4);
                btnopcA1.setText(opcA4);
                btnopcB1.setText(opcB4);
                btnopcC1.setText(opcC4);
                btnopcD1.setText(opcD4);
                btnCheckopcA1.setText(opcA4);
                btnCheckopcB1.setText(opcB4);
                btnCheckopcC1.setText(opcC4);
                btnCheckopcD1.setText(opcD4);
                respuestaSys1 = "4";
                break;
            case "4,":
                textpregunta1.setText(s);
                btnopcA1.setText(opcA5);
                btnopcB1.setText(opcB5);
                btnopcC1.setText(opcC5);
                btnopcD1.setText(opcD5);
                btnCheckopcA1.setText(opcA5);
                btnCheckopcB1.setText(opcB5);
                btnCheckopcC1.setText(opcC5);
                btnCheckopcD1.setText(opcD5);
                respuestaSys1 = "5";
                break;
        }
    }

    @Override
    public void onBackPressed(){
        return;
    }

}