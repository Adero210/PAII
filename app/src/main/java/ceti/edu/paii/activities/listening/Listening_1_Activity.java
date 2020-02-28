package ceti.edu.paii.activities.listening;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.SeekBar;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.speaking.Speaking_1_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_2_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_3_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_1_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_1_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_3_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Listening_1_Activity extends AppCompatActivity {



    int actHechas, cali;

    private String b1,b2, calis, actHechasS;
    private  String numAletorio ="";


    private String tipo;
    private TextView titulo;

    private ImageView ima1;
    private ImageView ima2;
    private ImageView ima3;
    private ImageView ima4;

    private ImageView imap1;
    private ImageView imap2;
    private ImageView imap3;
    private ImageView imap4;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;

    private StorageReference mAudioStorage;

    private Button revisar;
    private Button continuar;

    private String curso;
    private String lesson;

    private int numPre = 5;


    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "writing3Act.php";
    private String respuestaFromBD = "";
    private String respuestaUser ="";
    private MediaPlayer mediaPlayer,incorrect;
    //AUDIO
    Button play_pause;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_1_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        b1 = getIntent().getStringExtra("boceto1");
        b2 = getIntent().getStringExtra("boceto2");
        tipo = getIntent().getStringExtra("tipo");


        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);

        if(actHechas<=8) {

            progressDialog = new ProgressDialog(Listening_1_Activity.this);

            mAudioStorage = FirebaseStorage.getInstance().getReference();

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            titulo = findViewById(R.id.titulo_listening_1);
            ima1 = findViewById(R.id.image1_activity_listening_1);
            ima2 = findViewById(R.id.image2_activity_listening_1);
            ima3 = findViewById(R.id.image3_activity_listening_1);
            ima4 = findViewById(R.id.image4_activity_listening_1);

            imap1 = findViewById(R.id.palomita1_listening_1);
            imap2 = findViewById(R.id.palomita2_listening_1);
            imap3 = findViewById(R.id.palomita3_listening_1);
            imap4 = findViewById(R.id.palomita4_listening_1);


            text1 = findViewById(R.id.text_L11);
            text2 = findViewById(R.id.text_L12);
            text3 = findViewById(R.id.text_L13);
            text4 = findViewById(R.id.text_L14);

            revisar = findViewById(R.id.button_activity_listening_1);
            continuar = findViewById(R.id.button_continuar_activity_listening_1);

            play_pause = findViewById(R.id.play_pause_activity_listening_1);

            imap1.setVisibility(View.GONE);
            imap2.setVisibility(View.GONE);
            imap3.setVisibility(View.GONE);
            imap4.setVisibility(View.GONE);


            mp = new MediaPlayer();

            numAletorio = comun.aleatorio(numPre);

            if(b1.contains(numAletorio)) {
                if (curso.equals("Ingles")) {
                    titulo.setText("choose the correct option");
                } else if (curso.equals("Italiano")) {
                    titulo.setText("scegli la traduzione opzione");
                }

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

                bringTheInfo(lessonint - 1, numAletorio);

                options();

            }else {

                if(b1.contains("0")||b1.contains("1")||b1.contains("2")||b1.contains("3")||b1.contains("4")) {
                    Intent i = new Intent(Listening_1_Activity.this, Listening_1_Activity.class);
                    i.putExtra("curso", curso);
                    i.putExtra("lesson", lesson);
                    i.putExtra("tipo",tipo);

                    i.putExtra("calificacion", String.valueOf(cali));
                    i.putExtra("actividad", String.valueOf(actHechas));
                    i.putExtra("boceto1", b1);
                    i.putExtra("boceto2", b2);
                    startActivity(i);
                }else{
                    Intent i = new Intent(Listening_1_Activity.this, Listening_4_Activity.class);
                    i.putExtra("curso", curso);
                    i.putExtra("lesson", lesson);
                    i.putExtra("tipo",tipo);

                    i.putExtra("calificacion", String.valueOf(cali));
                    i.putExtra("actividad", String.valueOf(actHechas));
                    i.putExtra("boceto1", b1);
                    i.putExtra("boceto2", b2);
                    startActivity(i);

                }

                }
        }else {
                Intent i = new Intent(Listening_1_Activity.this, ResumenActividad.class);
                i.putExtra("curso",curso);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);
                i.putExtra("calificacion", String.valueOf(cali));

                startActivity(i);
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

                                respuestaFromBD = object.getString("respuestac");
                                String imagenfrom1 = object.getString("urlImage0");
                                String imagenfrom2 = object.getString("urlImage1");
                                String imagenfrom3 = object.getString("urlImage2");
                                String imagenfrom4 = object.getString("urlImage3");

                                Glide.with(Listening_1_Activity.this)
                                        .load(imagenfrom1)
                                        .into(ima1);
                                Glide.with(Listening_1_Activity.this)
                                        .load(imagenfrom2)
                                        .into(ima2);
                                Glide.with(Listening_1_Activity.this)
                                        .load(imagenfrom3)
                                        .into(ima3);
                                Glide.with(Listening_1_Activity.this)
                                        .load(imagenfrom4)
                                        .into(ima4);


                                String tImagenfrom1 = object.getString("textImage0");
                                String tImagenfrom2 = object.getString("textImage1");
                                String tImagenfrom3 = object.getString("textImage2");
                                String tImagenfrom4 = object.getString("textImage3");

                                text1.setText(tImagenfrom1);
                                text2.setText(tImagenfrom2);
                                text3.setText(tImagenfrom3);
                                text4.setText(tImagenfrom4);


                                String audio = object.getString("urlAudio").trim();

                                mAudioStorage.child("audiosAtividades").child(audio).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri1) {
                                        try {

                                            mp.reset();
                                            mp.setDataSource(Listening_1_Activity.this,uri1);
                                            // mp.setLooping(true);
                                            // mp.seekTo(0);
                                            //totalTime = mp.getDuration();
                                            mp.prepareAsync();

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });


                            }

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Listening_1_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Listening_1_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("type","listening");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private void options() {
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    //
                    mp.pause();
                    play_pause.setBackgroundResource(R.drawable.play);
                } else {
                    //
                    mp.start();
                    play_pause.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        ima1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Listening_1_Activity.this, R.xml.image_click));

                imap1.setVisibility(View.VISIBLE);
                imap2.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);

                respuestaUser = (String) text1.getText();
            }
        });
        ima2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Listening_1_Activity.this, R.xml.image_click));
                imap2.setVisibility(View.VISIBLE);
                imap1.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text2.getText();
            }
        });
        ima3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Listening_1_Activity.this, R.xml.image_click));
                imap3.setVisibility(View.VISIBLE);
                imap1.setVisibility(View.GONE);
                imap2.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text3.getText();
            }
        });
        ima4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Listening_1_Activity.this, R.xml.image_click));
                imap4.setVisibility(View.VISIBLE);
                imap1.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap2.setVisibility(View.GONE);
                respuestaUser = (String) text4.getText();
            }
        });
        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revisar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                if(respuestaUser.equals(respuestaFromBD)){
                    cali = cali + 100;

                    mediaPlayer.start();
                    if(curso.equals("Ingles")){
                        Toast.makeText(Listening_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Listening_1_Activity.this,"corretta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    incorrect.start();
                    cali = cali + 0;

                    if(curso.equals("Ingles")){
                        Toast.makeText(Listening_1_Activity.this,"wrong",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Listening_1_Activity.this,"sbagliata",Toast.LENGTH_SHORT).show();
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
                num = comun.aleatorio(2);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Listening_1_Activity.this, Listening_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        i.putExtra("boceto1",b1N);
                        i.putExtra("boceto2",b2);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Listening_1_Activity.this, Listening_4_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1N);
                        intent.putExtra("boceto2",b2);
                        startActivity(intent);
                        break;


                }

            }
        });
    }


    @Override
    protected void onDestroy() {
        mp.stop();
        super.onDestroy();
    }


    @Override
    public void onBackPressed(){
        return;
    }
}

