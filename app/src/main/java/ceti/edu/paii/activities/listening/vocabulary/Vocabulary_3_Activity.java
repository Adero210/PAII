package ceti.edu.paii.activities.listening.vocabulary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.speaking.Speaking_1_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_2_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_3_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;
import ceti.edu.paii.view.vocabularyThree;

public class Vocabulary_3_Activity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";


    int actHechas, cali;
    private String b1,b2,b3,b4, calis, actHechasS;


    private String curso;
    private String lesson;

    private String boceto = "3";

    private String pregunta = "";
    private String respuestaFromBD = "";
    private String respuestaSelected1 ="";
    private MediaPlayer mediaPlayer,incorrect;


    private String tipo;
    private int cartSel = 0;
    private int numPreguntas = 5;

    private TextView Titulo;

    private  String numAletorio ="";


    private  vocabularyThree opcionesres;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,cont;
    private String respuestaSelected2="";



    private int paCo=0;
    private int paIn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_3_);

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


        if(actHechas<=8) {

            progressDialog = new ProgressDialog(Vocabulary_3_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            Titulo = findViewById(R.id.titulo_vocabulary_3);
            btn0 = findViewById(R.id.opcion1_activity_vocabulary_3);
            btn1 = findViewById(R.id.opcion2_activity_vocabulary_3);
            btn2 = findViewById(R.id.opcion3_activity_vocabulary_3);
            btn3 = findViewById(R.id.opcion4_activity_vocabulary_3);
            btn4 = findViewById(R.id.opcion5_activity_vocabulary_3);
            btn5 = findViewById(R.id.opcion6_activity_vocabulary_3);
            btn6 = findViewById(R.id.opcion7_activity_vocabulary_3);
            btn7 = findViewById(R.id.opcion8_activity_vocabulary_3);
            btn8 = findViewById(R.id.opcion9_activity_vocabulary_3);
            btn9 = findViewById(R.id.opcion0_activity_vocabulary_3);

            cont = findViewById(R.id.button_activity_vocabulary_3);



            numAletorio = comun.aleatorio(numPreguntas);

            if(b3.contains(numAletorio)) {
                if (curso.equals("Ingles")) {
                    Titulo.setText("Listen and Repeat");
                } else if (curso.equals("Italiano")) {
                    Titulo.setText("Ascolta e ripeti");
                }


                int lessonint = Integer.parseInt(lesson);

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

                bringTheInfo(lessonint - 1, numAletorio);
                opciones();
            }else {

                Intent i = new Intent(Vocabulary_3_Activity.this, Vocabulary_3_Activity.class);
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

            }
        }
        else {
            Intent i = new Intent(Vocabulary_3_Activity.this, ResumenActividad.class);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("tipo",tipo);

            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);

        }

    }

    private void opciones() {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn0.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn0.getText();
                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn0.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn0.getText();
                    cartSel=1;
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn1.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn1.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn1.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn1.getText();

                    cartSel=1;

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn2.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn2.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn2.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn2.getText();

                    cartSel=1;

                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                btn3.setTextColor(Color.parseColor("#008000"));
                respuestaSelected2 = (String) btn3.getText();
                check(respuestaSelected1,respuestaSelected2);
            }else {
                btn3.setTextColor(Color.parseColor("#008000"));
                respuestaSelected1 = (String) btn3.getText();

                cartSel=1;
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn4.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn4.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn4.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn4.getText();
                    cartSel=1;

                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn5.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn5.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn5.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn5.getText();

                    cartSel=1;

                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn6.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn6.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn6.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn6.getText();

                    cartSel=1;

                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn7.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn7.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn7.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn7.getText();

                    cartSel=1;

                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn8.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn8.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn8.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn8.getText();

                    cartSel=1;

                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn9.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn9.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn9.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn9.getText();

                    cartSel=1;

                }
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paIn>3){
                    cali = cali+0;
                }else{
                    cali = cali+100;
                }


                String b3N = b3.replaceAll(numAletorio,"");
                actHechas++;
                String num ="";
                num = comun.aleatorio(4);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Vocabulary_3_Activity.this, Vocabulary_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        i.putExtra("boceto1",b1);
                        i.putExtra("boceto2",b2);
                        i.putExtra("boceto3",b3N);
                        i.putExtra("boceto4",b4);

                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Vocabulary_3_Activity.this, Vocabulary_2_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1);
                        intent.putExtra("boceto2",b2);
                        intent.putExtra("boceto3",b3N);
                        intent.putExtra("boceto4",b4);

                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Vocabulary_3_Activity.this, Vocabulary_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);

                        intent1.putExtra("calificacion",String.valueOf(cali));
                        intent1.putExtra("actividad",String.valueOf(actHechas));
                        intent1.putExtra("boceto1",b1);
                        intent1.putExtra("boceto2",b2);
                        intent1.putExtra("boceto3",b3N);
                        intent1.putExtra("boceto4",b4);

                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Vocabulary_3_Activity.this, Vocabulary_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);

                        intent2.putExtra("calificacion",String.valueOf(cali));
                        intent2.putExtra("actividad",String.valueOf(actHechas));
                        intent2.putExtra("boceto1",b1);
                        intent2.putExtra("boceto2",b2);
                        intent2.putExtra("boceto3",b3N);
                        intent2.putExtra("boceto4",b4);

                        startActivity(intent2);
                        break;

                }
            }
        });
    }

    private void check(String r1, String r2) {
        String pregunta1= opcionesres.getPreguntaUno();
        String respuesta1 = opcionesres.getRespuestaUno();
        String pregunta2= opcionesres.getPreguntaDos();
        String respuesta2 = opcionesres.getRespuestaDos();
        String pregunta3= opcionesres.getPreguntaTres();
        String respuesta3 = opcionesres.getRespuestaTres();
        String pregunta4= opcionesres.getPreguntaCuatro();
        String respuesta4= opcionesres.getRespuestaCuatro();
        String pregunta5= opcionesres.getPreguntaCinco();
        String respuesta5 = opcionesres.getRespuestaCinco();


        if(paCo<=5) {

            if (pregunta1.equals(r1)) {
                if (respuesta1.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);

                }
            }else if(pregunta2.equals(r1)){
                if (respuesta2.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(pregunta3.equals(r1)){
                if (respuesta3.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(pregunta4.equals(r1)){
                if (respuesta4.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(pregunta5.equals(r1)){
                if (respuesta5.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta1.equals(r1)) {
                if (pregunta1.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta2.equals(r1)) {
                if (pregunta2.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta3.equals(r1)) {
                if (pregunta3.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta4.equals(r1)) {
                if (pregunta4.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta5.equals(r1)) {
                if (pregunta5.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }

            cartSel = 0;

        }else{

            btn0.setEnabled(false);
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);
            btn5.setEnabled(false);
            btn6.setEnabled(false);
            btn7.setEnabled(false);
            btn8.setEnabled(false);
            btn9.setEnabled(false);
        }

    }


    private void bringTheInfo(final Integer lessonint2, final String numAle) {

        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ACTR2, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String numfilas = jsonObject.getString("filas");
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("actr2");

                    int numFilas = Integer.parseInt(numfilas);

                    if(success.equals("1")){
                        progressDialog.dismiss();
                        for(int i = 0 ; i < jsonArray.length();i++){

                            JSONObject object =  jsonArray.getJSONObject(i);

                            for(int h = 0; h < numFilas; h++) {

                                pregunta = object.getString("pregunta" + h).trim();
                                respuestaFromBD = object.getString("respuestac" + h);

                                Log.i("vocabulary2","pregunta: " + pregunta);
                                Log.i("vocabulary2","respuesta: " + respuestaFromBD);


                            }
                            String preguntas[] = pregunta.split(",");

                            String respuestas[] = respuestaFromBD.split(",");
                            opcionesres = new vocabularyThree(preguntas[0],respuestas[0],preguntas[1],respuestas[1],preguntas[2],respuestas[2],preguntas[3],respuestas[3],preguntas[4],respuestas[4]);

                            String kk = pregunta+","+respuestaFromBD;
                            String words[] = kk.split(",");

                            Log.i("vocabulary2","kk: " + kk);


                            String[] r = new String[10];
                            // AleatoriSinRepeticion();
                            int pos, y = 0;
                            int nCartas = 10;
                            Stack<Integer> pCartas = new Stack<Integer>();
                            for (int x = 0; x < nCartas; x++) {
                                pos = (int) Math.floor(Math.random() * nCartas);
                                while (pCartas.contains(pos)) {
                                    pos = (int) Math.floor(Math.random() * nCartas);
                                }
                                r[pos] = words[y];
                                pCartas.push(pos);
                                y = y + 1;
                            }

                            btn0.setText(r[9]);
                            btn1.setText(r[0]);
                            btn2.setText(r[1]);
                            btn3.setText(r[2]);
                            btn4.setText(r[3]);
                            btn5.setText(r[4]);
                            btn6.setText(r[5]);
                            btn7.setText(r[6]);
                            btn8.setText(r[7]);
                            btn9.setText(r[8]);

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Vocabulary_3_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Vocabulary_3_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("boceto",boceto);
                params.put("type","vocabulary");


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onBackPressed(){
        return;
    }
}
