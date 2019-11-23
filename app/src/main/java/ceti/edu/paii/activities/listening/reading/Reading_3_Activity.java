package ceti.edu.paii.activities.listening.reading;

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

public class Reading_3_Activity extends AppCompatActivity {

    private Button buttonback;
    private TextView textpregunta1;
    private TextView textpregunta3;
    private TextView textpregunta2;
    private TextView textpregunta4;
    private TextView textpregunta5;

    private ImageView cOpc1;
    private ImageView iOpc1;
    private ImageView cOpc2;
    private ImageView iOpc2;
    private ImageView cOpc3;
    private ImageView iOpc3;
    private ImageView cOpc4;
    private ImageView iOpc4;
    private ImageView cOpc5;
    private ImageView iOpc5;

    private String respuestac1 = "";
    private String respuestac2 = "";
    private String respuestac3 = "";
    private String respuestac4 = "";
    private String respuestac5 = "";
    private String respuestac6 = "";
    private String respuestac7 = "";
    private String respuestac8 = "";
    private String respuestac9 = "";
    private String respuestac10 = "";


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
    
    private ImageView incorrectQ1;
    private ImageView incorrectQ2;
    private ImageView incorrectQ3;
    private ImageView incorrectQ4;
    private ImageView incorrectQ5;
    

    private Button revisar;
    private Button continuar;
    
    private String respuestaCorrectaFrom1 = "";
    private String respuestaCorrectaFrom2 = "";
    private String respuestaCorrectaFrom3 = "";
    private String respuestaCorrectaFrom4 = "";
    private String respuestaCorrectaFrom5 = "";

    private int correctas = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_3_);

        textpregunta1 = findViewById(R.id.pregunta_1_reading_3);
        textpregunta2 = findViewById(R.id.pregunta_2_reading_3);
        textpregunta3 = findViewById(R.id.pregunta_3_reading_3);
        textpregunta4 = findViewById(R.id.pregunta_4_reading_3);
        textpregunta5 = findViewById(R.id.pregunta_5_reading_3);

        cOpc1 = findViewById(R.id.correct_opc1);
        cOpc2 = findViewById(R.id.correct_opc2);
        cOpc3 = findViewById(R.id.correct_opc3);
        cOpc4 = findViewById(R.id.correct_opc4);
        cOpc5 = findViewById(R.id.correct_opc5);

        iOpc1 = findViewById(R.id.incorrect_opc1);
        iOpc2 = findViewById(R.id.incorrect_opc2);
        iOpc3 = findViewById(R.id.incorrect_opc3);
        iOpc4 = findViewById(R.id.incorrect_opc4);
        iOpc5 = findViewById(R.id.incorrect_opc5);
        
        true1 = findViewById(R.id.reading_radio_buton_1_3);
        true2 = findViewById(R.id.reading_radio_buton_3_3);
        true3 = findViewById(R.id.reading_radio_buton_5_3);
        true4 = findViewById(R.id.reading_radio_buton_7_3);
        true5 = findViewById(R.id.reading_radio_buton_9_3);
        
        false1 = findViewById(R.id.reading_radio_buton_2_3);
        false2 = findViewById(R.id.reading_radio_buton_4_3);
        false3 = findViewById(R.id.reading_radio_buton_6_3);
        false4 = findViewById(R.id.reading_radio_buton_8_3);
        false5 = findViewById(R.id.reading_radio_buton_0_3);

        correctQ1 = findViewById(R.id.correct_opc1);
        correctQ2 = findViewById(R.id.correct_opc2);
        correctQ3 = findViewById(R.id.correct_opc3);
        correctQ4 = findViewById(R.id.correct_opc4);
        correctQ5 = findViewById(R.id.correct_opc5);
        
        incorrectQ1 = findViewById(R.id.incorrect_opc1);
        incorrectQ2 = findViewById(R.id.incorrect_opc2);
        incorrectQ3 = findViewById(R.id.incorrect_opc3);
        incorrectQ4 = findViewById(R.id.incorrect_opc4);
        incorrectQ5 = findViewById(R.id.incorrect_opc5);




        revisar = findViewById(R.id.button_activity_Reading_3);
        continuar = findViewById(R.id.button_continuar_activity_Reading_3);

        buttonback = findViewById(R.id.button_back_activity_Reading_3);


        String pregunta1 = getIntent().getStringExtra("pregunta1");
        String pregunta2 = getIntent().getStringExtra("pregunta2");
        String pregunta3 = getIntent().getStringExtra("pregunta3");
        String pregunta4 = getIntent().getStringExtra("pregunta4");
        String pregunta5 = getIntent().getStringExtra("pregunta5");

        String pregunta6 = getIntent().getStringExtra("pregunta6");
        String pregunta7 = getIntent().getStringExtra("pregunta7");
        String pregunta8 = getIntent().getStringExtra("pregunta8");
        String pregunta9 = getIntent().getStringExtra("pregunta9");
        String pregunta10 = getIntent().getStringExtra("pregunta10");

        respuestac1 = getIntent().getStringExtra("respuesta1c");
        respuestac2 = getIntent().getStringExtra("respuesta2c");
        respuestac3 = getIntent().getStringExtra("respuesta3c");
        respuestac4 = getIntent().getStringExtra("respuesta4c");
        respuestac5 = getIntent().getStringExtra("respuesta5c");

        respuestac6 = getIntent().getStringExtra("respuesta6c");
        respuestac7 = getIntent().getStringExtra("respuesta7c");
        respuestac8 = getIntent().getStringExtra("respuesta8c");
        respuestac9 = getIntent().getStringExtra("respuesta9c");
        respuestac10 = getIntent().getStringExtra("respuesta10c");

        int pos;
        int nCartas = 10;
        Stack<Integer> pCartas = new Stack<Integer>();
        for (int i = 0; i < nCartas; i++) {
            pos = (int) Math.floor(Math.random() * nCartas);
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nCartas);
            }

            pCartas.push(pos);
        }

        Log.i("Numeros", pCartas.toString());

        String n[] = pCartas.toString().split(" ");
        n[0] = n[0].replace("[", "");

        Log.i("numeroshhh", n[0]);
        Log.i("numeroshhh", n[1]);
        Log.i("numeroshhh", n[2]);
        Log.i("numeroshhh", n[3]);
        Log.i("numeroshhh", n[4]);

        Log.i("preguntashhh",respuestac1);


        Log.i("preguntashhh",pregunta1);
        Log.i("preguntashhh",pregunta2);
        Log.i("preguntashhh",pregunta3);
        Log.i("preguntashhh",pregunta4);
        Log.i("preguntashhh",pregunta5);
        Log.i("preguntashhh",pregunta6);
        Log.i("preguntashhh",pregunta7);
        Log.i("preguntashhh",pregunta8);
        Log.i("preguntashhh",pregunta9);
        Log.i("preguntashhh",pregunta10);

        setPreguntaUno(n[0], pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10);
        setPreguntaDos(n[1], pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10);
        setPreguntaTres(n[2], pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10);
        setPreguntaCuatro(n[3], pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10);
        setPreguntaCinco(n[4], pregunta1, pregunta2, pregunta3, pregunta4, pregunta5, pregunta6, pregunta7, pregunta8, pregunta9, pregunta10);





        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checarPregunta1(respuestaCorrectaFrom1);
                checarPregunta2(respuestaCorrectaFrom2);
                checarPregunta3(respuestaCorrectaFrom3);
                checarPregunta4(respuestaCorrectaFrom4);
                checarPregunta5(respuestaCorrectaFrom5);

                Toast.makeText(Reading_3_Activity.this,"Correctas: " + correctas,Toast.LENGTH_SHORT).show();

                buttonback.setVisibility(View.GONE);
                revisar.setVisibility(View.GONE);
                continuar.setVisibility(View.VISIBLE);


            }
        });


    }

    private void checarPregunta5(String respuestaCorrectaFrom5) {
        String resCorr;
        String resInc;

        switch (respuestaCorrectaFrom5){
            case "1":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac1)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac1)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "2":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac2)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac2)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "3":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac3)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac3)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "4":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac4)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac4)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "5":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac5)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac5)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "6":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac6)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac6)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "7":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac7)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac7)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "8":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac8)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac8)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "9":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac9)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac9)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "10":
                if(true5.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac10)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }else if(false5.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac10)){
                        correctQ5.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ5.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }

    private void checarPregunta4(String respuestaCorrectaFrom4) {
        String resCorr;
        String resInc;

        switch (respuestaCorrectaFrom4){
            case "1":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac1)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac1)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "2":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac2)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac2)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "3":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac3)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac3)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "4":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac4)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac4)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "5":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac5)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac5)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "6":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac6)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac6)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "7":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac7)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac7)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "8":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac8)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac8)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "9":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac9)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac9)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "10":
                if(true4.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac10)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }else if(false4.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac10)){
                        correctQ4.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ4.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }

    private void checarPregunta3(String respuestaCorrectaFrom3) {
        String resCorr;
        String resInc;

        switch (respuestaCorrectaFrom3){
            case "1":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac1)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac1)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "2":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac2)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac2)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "3":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac3)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac3)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "4":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac4)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac4)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "5":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac5)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac5)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "6":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac6)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac6)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "7":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac7)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac7)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "8":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac8)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac8)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "9":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac9)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac9)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "10":
                if(true3.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac10)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }else if(false3.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac10)){
                        correctQ3.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ3.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }

    private void checarPregunta2(String respuestaCorrectaFrom2) {
        String resCorr;
        String resInc;

        switch (respuestaCorrectaFrom2){
            case "1":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac1)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac1)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "2":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac2)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac2)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "3":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac3)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac3)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "4":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac4)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac4)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "5":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac5)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac5)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "6":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac6)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac6)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "7":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac7)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac7)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "8":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac8)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac8)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "9":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac9)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac9)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "10":
                if(true2.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac10)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;
                    }
                    else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }else if(false2.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac10)){
                        correctQ2.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ2.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }

    private void checarPregunta1(String respuestaCorrectaFrom1) {
        String resCorr;
        String resInc;

        switch (respuestaCorrectaFrom1){
            case "1":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac1)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac1)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;


                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "2":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac2)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac2)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "3":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac3)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac3)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;


                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "4":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac4)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac4)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;


                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "5":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac5)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac5)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;


                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "6":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac6)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac6)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "7":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac7)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac7)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;


                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "8":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac8)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac8)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;


                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "9":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac9)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac9)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;


                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case "10":
                if(true1.isChecked()){
                    resCorr = "True";
                    if (resCorr.equals(respuestac10)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }
                    else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }else if(false1.isChecked()){
                    resInc = "False";
                    if(resInc.equals(respuestac10)){
                        correctQ1.setVisibility(View.VISIBLE);
                        correctas++;

                    }else{
                        incorrectQ1.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }

    }

    private void setPreguntaCinco(String s, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String pregunta6, String pregunta7, String pregunta8, String pregunta9, String pregunta10) {

        switch (s){
            case "0,":
                textpregunta5.setText(pregunta1);
                respuestaCorrectaFrom5 = "1";
                break;
            case "1,":
                textpregunta5.setText(pregunta2);
                respuestaCorrectaFrom5 = "2";

                break;
            case "2,":
                textpregunta5.setText(pregunta3);
                respuestaCorrectaFrom5 = "3";

                break;
            case "3,":
                textpregunta5.setText(pregunta4);
                respuestaCorrectaFrom5 = "4";

                break;
            case "4,":
                textpregunta5.setText(pregunta5);
                respuestaCorrectaFrom5 = "5";

            case "5,":
                textpregunta5.setText(pregunta6);
                respuestaCorrectaFrom5 = "6";

                break;
            case "6,":
                textpregunta5.setText(pregunta7);
                respuestaCorrectaFrom5 = "7";

                break;
            case "7,":
                textpregunta5.setText(pregunta8);
                respuestaCorrectaFrom5 = "8";
                break;
            case "8,":
                textpregunta5.setText(pregunta9);
                respuestaCorrectaFrom5 = "9";
                break;
            case "9,":
                textpregunta5.setText(pregunta10);
                respuestaCorrectaFrom5 = "10";
                break;
        }
    }

    private void setPreguntaCuatro(String s, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String pregunta6, String pregunta7, String pregunta8, String pregunta9, String pregunta10) {

        switch (s){
            case "0,":
                textpregunta4.setText(pregunta1);
                respuestaCorrectaFrom4 = "1";
                break;
            case "1,":
                textpregunta4.setText(pregunta2);
                respuestaCorrectaFrom4 = "2";

                break;
            case "2,":
                textpregunta4.setText(pregunta3);
                respuestaCorrectaFrom4 = "3";

                break;
            case "3,":
                textpregunta4.setText(pregunta4);
                respuestaCorrectaFrom4 = "4";

                break;
            case "4,":
                textpregunta4.setText(pregunta5);
                respuestaCorrectaFrom4 = "5";

            case "5,":
                textpregunta4.setText(pregunta6);
                respuestaCorrectaFrom4 = "6";

                break;
            case "6,":
                textpregunta4.setText(pregunta7);
                respuestaCorrectaFrom4 = "7";

                break;
            case "7,":
                textpregunta4.setText(pregunta8);
                respuestaCorrectaFrom4 = "8";
                break;
            case "8,":
                textpregunta4.setText(pregunta9);
                respuestaCorrectaFrom4 = "9";
                break;
            case "9,":
                textpregunta4.setText(pregunta10);
                respuestaCorrectaFrom4 = "10";
                break;
        }
    }

    private void setPreguntaTres(String s, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String pregunta6, String pregunta7, String pregunta8, String pregunta9, String pregunta10) {

        switch (s){
            case "0,":
                textpregunta3.setText(pregunta1);
                respuestaCorrectaFrom3 = "1";
                break;
            case "1,":
                textpregunta3.setText(pregunta2);
                respuestaCorrectaFrom3 = "2";

                break;
            case "2,":
                textpregunta3.setText(pregunta3);
                respuestaCorrectaFrom3 = "3";

                break;
            case "3,":
                textpregunta3.setText(pregunta4);
                respuestaCorrectaFrom3 = "4";

                break;
            case "4,":
                textpregunta3.setText(pregunta5);
                respuestaCorrectaFrom3 = "5";

            case "5,":
                textpregunta3.setText(pregunta6);
                respuestaCorrectaFrom3 = "6";

                break;
            case "6,":
                textpregunta3.setText(pregunta7);
                respuestaCorrectaFrom3 = "7";

                break;
            case "7,":
                textpregunta3.setText(pregunta8);
                respuestaCorrectaFrom3 = "8";
                break;
            case "8,":
                textpregunta3.setText(pregunta9);
                respuestaCorrectaFrom3 = "9";
                break;
            case "9,":
                textpregunta3.setText(pregunta10);
                respuestaCorrectaFrom3 = "10";
                break;
        }
    }

    private void setPreguntaDos(String s, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String pregunta6, String pregunta7, String pregunta8, String pregunta9, String pregunta10) {

        switch (s){
            case "0,":
                textpregunta2.setText(pregunta1);
                respuestaCorrectaFrom2 = "1";
                break;
            case "1,":
                textpregunta2.setText(pregunta2);
                respuestaCorrectaFrom2 = "2";

                break;
            case "2,":
                textpregunta2.setText(pregunta3);
                respuestaCorrectaFrom2 = "3";

                break;
            case "3,":
                textpregunta2.setText(pregunta4);
                respuestaCorrectaFrom2 = "4";

                break;
            case "4,":
                textpregunta2.setText(pregunta5);
                respuestaCorrectaFrom2 = "5";

            case "5,":
                textpregunta2.setText(pregunta6);
                respuestaCorrectaFrom2 = "6";

                break;
            case "6,":
                textpregunta2.setText(pregunta7);
                respuestaCorrectaFrom2 = "7";

                break;
            case "7,":
                textpregunta2.setText(pregunta8);
                respuestaCorrectaFrom2 = "8";
                break;
            case "8,":
                textpregunta2.setText(pregunta9);
                respuestaCorrectaFrom2 = "9";
                break;
            case "9,":
                textpregunta2.setText(pregunta10);
                respuestaCorrectaFrom2 = "10";
                break;
        }
    }

    private void setPreguntaUno(String s, String pregunta1, String pregunta2, String pregunta3, String pregunta4, String pregunta5, String pregunta6, String pregunta7, String pregunta8, String pregunta9, String pregunta10) {

        switch (s){
            case "0,":
                textpregunta1.setText(pregunta1);
                respuestaCorrectaFrom1 = "1";
                break;
            case "1,":
                textpregunta1.setText(pregunta2);
                respuestaCorrectaFrom1 = "2";

                break;
            case "2,":
                textpregunta1.setText(pregunta3);
                respuestaCorrectaFrom1 = "3";

                break;
            case "3,":
                textpregunta1.setText(pregunta4);
                respuestaCorrectaFrom1 = "4";

                break;
            case "4,":
                textpregunta1.setText(pregunta5);
                respuestaCorrectaFrom1 = "5";

            case "5,":
                textpregunta1.setText(pregunta6);
                respuestaCorrectaFrom1 = "6";

                break;
            case "6,":
                textpregunta1.setText(pregunta7);
                respuestaCorrectaFrom1 = "7";

                break;
            case "7,":
                textpregunta1.setText(pregunta8);
                respuestaCorrectaFrom1 = "8";
                break;
            case "8,":
                textpregunta1.setText(pregunta9);
                respuestaCorrectaFrom1 = "9";
                break;
            case "9,":
                textpregunta1.setText(pregunta10);
                respuestaCorrectaFrom1 = "10";
                break;

        }
    }

}
