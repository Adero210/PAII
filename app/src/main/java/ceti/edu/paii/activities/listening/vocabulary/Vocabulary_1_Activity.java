package ceti.edu.paii.activities.listening.vocabulary;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.grammar.Grammar_1_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_1_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_2_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_3_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_3_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Vocabulary_1_Activity extends AppCompatActivity {

    private TextView oracion;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;

    private Button revisar;
    private Button continuar;

    private String curso;
    private String lesson;

    private ImageView imap1;
    private ImageView imap2;
    private ImageView imap3;
    private ImageView imap4;

    private String tipo;

    int actHechas, cali;
    private String b1,b2,b3,b4, calis, actHechasS;

    private  String numAletorio ="";

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/vocabulary1Act.php";
    private String respuestaFromBD = "";
    private String respuestaUser="";
    private MediaPlayer mediaPlayer,incorrect;
    private int numerosPreuntas = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_1_);

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

            progressDialog = new ProgressDialog(Vocabulary_1_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            oracion = findViewById(R.id.titulo_vocabulary_1);
            img1 = findViewById(R.id.image_1_vocabulary_1);
            img2 = findViewById(R.id.image_2_vocabulary_1);
            img3 = findViewById(R.id.image_3_vocabulary_1);
            img4 = findViewById(R.id.image_4_vocabulary_1);

            imap1 = findViewById(R.id.palomita1_voca_1);
            imap2 = findViewById(R.id.palomita2_voca_1);
            imap3 = findViewById(R.id.palomita3_voca_1);
            imap4 = findViewById(R.id.palomita4_voca_1);

            text1 = findViewById(R.id.textimage_1_vocabulary_1);
            text2 = findViewById(R.id.textimage_2_vocabulary_1);
            text3 = findViewById(R.id.textimage_3_vocabulary_1);
            text4 = findViewById(R.id.textimage_4_vocabulary_1);

            imap1.setVisibility(View.GONE);
            imap2.setVisibility(View.GONE);
            imap3.setVisibility(View.GONE);
            imap4.setVisibility(View.GONE);


            revisar = findViewById(R.id.button_activity_vocabulary_1);
            continuar = findViewById(R.id.button_activity_con_vocabulary_1);


            numAletorio = comun.aleatorio(numerosPreuntas);

            if(b1.contains(numAletorio)) {
                if (curso.equals("Ingles")) {
                    oracion.setText("Listen and Repeat");
                } else if (curso.equals("Italiano")) {
                    oracion.setText("Ascolta e ripeti");
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

                Intent i = new Intent(Vocabulary_1_Activity.this, Vocabulary_1_Activity.class);
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

        } else {
            Intent i = new Intent(Vocabulary_1_Activity.this, ResumenActividad.class);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("tipo",tipo);

            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);

        }
    }

    private void opciones() {

        img1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap1.setVisibility(View.VISIBLE);
                imap2.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text1.getText();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap2.setVisibility(View.VISIBLE);
                imap1.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text2.getText();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap3.setVisibility(View.VISIBLE);
                imap2.setVisibility(View.GONE);
                imap1.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text3.getText();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap4.setVisibility(View.VISIBLE);
                imap2.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap1.setVisibility(View.GONE);
                respuestaUser = (String) text4.getText();
            }
        });
        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revisar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                if(respuestaUser.equals(respuestaFromBD)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    if(curso.equals("Ingles")){
                        Toast.makeText(Vocabulary_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Vocabulary_1_Activity.this,"corretta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    incorrect.start();
                    cali = cali + 0;

                    if(curso.equals("Ingles")){
                        Toast.makeText(Vocabulary_1_Activity.this,"wrong",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Vocabulary_1_Activity.this,"sbagliata",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b1N = b1.replaceAll(numAletorio,"");
                actHechas++;
                String num ="";
                num = comun.aleatorio(4);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Vocabulary_1_Activity.this, Vocabulary_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        i.putExtra("boceto1",b1N);
                        i.putExtra("boceto2",b2);
                        i.putExtra("boceto3",b3);
                        i.putExtra("boceto4",b4);

                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Vocabulary_1_Activity.this, Vocabulary_2_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1N);
                        intent.putExtra("boceto2",b2);
                        intent.putExtra("boceto3",b3);
                        intent.putExtra("boceto4",b4);

                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Vocabulary_1_Activity.this, Vocabulary_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);

                        intent1.putExtra("calificacion",String.valueOf(cali));
                        intent1.putExtra("actividad",String.valueOf(actHechas));
                        intent1.putExtra("boceto1",b1N);
                        intent1.putExtra("boceto2",b2);
                        intent1.putExtra("boceto3",b3);
                        intent1.putExtra("boceto4",b4);

                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Vocabulary_1_Activity.this, Vocabulary_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);

                        intent2.putExtra("calificacion",String.valueOf(cali));
                        intent2.putExtra("actividad",String.valueOf(actHechas));
                        intent2.putExtra("boceto1",b1N);
                        intent2.putExtra("boceto2",b2);
                        intent2.putExtra("boceto3",b3);
                        intent2.putExtra("boceto4",b4);

                        startActivity(intent2);
                        break;

                }
            }
        });
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

                                String pregunta = object.getString("pregunta").trim();
                                respuestaFromBD = object.getString("respuestac");
                                String imagenfrom1 = object.getString("urlImage0");
                                String imagenfrom2 = object.getString("urlImage1");
                                String imagenfrom3 = object.getString("urlImage2");
                                String imagenfrom4 = object.getString("urlImage3");

                                String tImagenfrom1 = object.getString("textoImage0");
                                String tImagenfrom2 = object.getString("textoImage1");
                                String tImagenfrom3 = object.getString("textoImage2");
                                String tImagenfrom4 = object.getString("textoImage3");

                                text1.setText(tImagenfrom1);
                                text2.setText(tImagenfrom2);
                                text3.setText(tImagenfrom3);
                                text4.setText(tImagenfrom4);


                                oracion.setText(pregunta);

                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom1)
                                        .into(img1);
                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom2)
                                        .into(img2);
                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom3)
                                        .into(img3);
                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom4)
                                        .into(img4);

                            }

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Vocabulary_1_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Vocabulary_1_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
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
