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
import android.widget.ImageButton;
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
import ceti.edu.paii.view.Activities_Activity;
import ceti.edu.paii.view.ResumenActividad;

public class Listening_1_Activity extends AppCompatActivity {

    int actHechas, cali;

    private String calis, actHechasS;
    private String numAletorio;

    private TextView titulo;

    private String boceto = "2";
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

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";
    private String respuestaFromBD = "";
    private String respuestaUser ="";
    private MediaPlayer mediaPlayer,incorrect;
    //AUDIO
    ImageButton play_pause;
    MediaPlayer mp;

    private String id0;
    private String id1;
    private String id2;
    private String id3;
    private String id4;
    private String id5;
    private String id6;
    private String id7;
    private String id8;

    String[] cadid = new String[30];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_1_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        id0 = getIntent().getStringExtra("id0");
        id1 = getIntent().getStringExtra("id1");
        id2 = getIntent().getStringExtra("id2");
        id3 = getIntent().getStringExtra("id3");
        id4 = getIntent().getStringExtra("id4");
        id5 = getIntent().getStringExtra("id5");
        id6 = getIntent().getStringExtra("id6");
        id7 = getIntent().getStringExtra("id7");
        id8 = getIntent().getStringExtra("id8");

        cali = Integer.parseInt(calis);
        actHechas = Integer.parseInt(actHechasS);
        cadid[0] = id0;
        cadid[1] = id1;
        cadid[2] = id2;
        cadid[3] = id3;
        cadid[4] = id4;
        cadid[5] = id5;
        cadid[6] = id6;
        cadid[7] = id7;
        cadid[8] = id8;



        if(actHechas <= 8) {


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

            numAletorio = "1";

            if (curso.equals("English")) {
                titulo.setText("Choose the correct option");
            } else if (curso.equals("Italiano")) {
                titulo.setText("Scegli la traduzione opzione");
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
            Intent i = new Intent(Listening_1_Activity.this, ResumenActividad.class);
            String tipo = "Escucha";

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
                    Log.i("ahhhha","entre al try");

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {
                        Log.i("ahhhha","success");

                        JSONObject object =  jsonArray.getJSONObject(0);

                        String question = object.getString("question");
                        respuestaFromBD = object.getString("correct");

                        JSONObject jsonObject1audio = object.getJSONObject("audio");

                        String audio = jsonObject1audio.getString("fileName").trim();
                       // String audio = "1.2.10_01-24-2020 12:16:15";


                        JSONArray jsonArray1 = object.getJSONArray("images");

                        String imagenfrom1 = null;

                        String imagenfrom2 = null;
                        String imagenfrom3 = null;
                        String imagenfrom4 = null;

                        String textfrom1 = null;

                        String textfrom2 = null;
                        String textfrom3 = null;
                        String textfrom4 = null;

                        for (int i = 0; i < jsonArray1.length(); i++) {

                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("image");

                            if(i==0){
                                imagenfrom1 = jsonObject2.getString("imageRoute");
                                textfrom1 = jsonObject2.getString("value");
                            }
                            if(i==1){
                                imagenfrom2 = jsonObject2.getString("imageRoute");
                                textfrom2 = jsonObject2.getString("value");

                            }if(i==2){
                                imagenfrom3 = jsonObject2.getString("imageRoute");
                                textfrom3 = jsonObject2.getString("value");

                            }if(i==3){
                                imagenfrom4 = jsonObject2.getString("imageRoute");
                                textfrom4 = jsonObject2.getString("value");

                            }

                        }


                        for(int i = 0; i <= cadid.length; i++){
                            String aux = question;
                            if(question.equals(cadid[i])) {

                                Intent in = new Intent(Listening_1_Activity.this, Listening_1_Activity.class);
                                in.putExtra("curso", curso);
                                in.putExtra("lesson", lesson);
                                in.putExtra("calificacion", calis);
                                in.putExtra("actividad", actHechasS);
                                in.putExtra("id0", cadid[0]);
                                in.putExtra("id1", cadid[1]);
                                in.putExtra("id2", cadid[2]);
                                in.putExtra("id3", cadid[3]);
                                in.putExtra("id4", cadid[4]);
                                in.putExtra("id5", cadid[5]);
                                in.putExtra("id6", cadid[6]);
                                in.putExtra("id7", cadid[7]);
                                in.putExtra("id8", cadid[8]);

                                startActivity(in);
                            }

                            if (cadid[i].equals("0")) {
                                cadid[i] = aux;
                                if (cadid[i + 1].equals("0")) {
                                    cadid[i] = question;
                                    break;
                                }
                            }


                        }

                        Log.i("aaa",cadid[0]+cadid[1]);


                        progressDialog.dismiss();

                        text1.setText(textfrom1);
                        text2.setText(textfrom2);
                        text3.setText(textfrom3);
                        text4.setText(textfrom4);


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



                        mAudioStorage.child("audiosAtividades").child(audio).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri1) {
                                try {
                                    Log.i("uri",String.valueOf(uri1));
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
                params.put("numberOfQuestions",numAle);
                params.put("lectionId", String.valueOf(lessonint2));
                params.put("sketch",boceto);
                params.put("typeName","Escucha");
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
                    calis = String.valueOf(cali);

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
                    calis = String.valueOf(cali);

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
                actHechas++;
                actHechasS = String.valueOf(actHechas);
                Log.i("aaaa",cadid[0]);
                String num;
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Listening_1_Activity.this, Listening_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",calis);
                        i.putExtra("actividad",actHechasS);
                        i.putExtra("id0",cadid[0]);
                        i.putExtra("id1",cadid[1]);
                        i.putExtra("id2",cadid[2]);
                        i.putExtra("id3",cadid[3]);
                        i.putExtra("id4",cadid[4]);
                        i.putExtra("id5",cadid[5]);
                        i.putExtra("id6",cadid[6]);
                        i.putExtra("id7",cadid[7]);
                        i.putExtra("id8", cadid[8]);

                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Listening_1_Activity.this, Listening_3_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",calis);
                        intent.putExtra("actividad",actHechasS);
                        intent.putExtra("id0",cadid[0]);
                        intent.putExtra("id1",cadid[1]);
                        intent.putExtra("id2",cadid[2]);
                        intent.putExtra("id3",cadid[3]);
                        intent.putExtra("id4",cadid[4]);
                        intent.putExtra("id5",cadid[5]);
                        intent.putExtra("id6",cadid[6]);
                        intent.putExtra("id7",cadid[7]);
                        intent.putExtra("id8", cadid[8]);

                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent2= new Intent(Listening_1_Activity.this, Listening_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("calificacion",calis);
                        intent2.putExtra("actividad",actHechasS);
                        intent2.putExtra("id0",cadid[0]);
                        intent2.putExtra("id1",cadid[1]);
                        intent2.putExtra("id2",cadid[2]);
                        intent2.putExtra("id3",cadid[3]);
                        intent2.putExtra("id4",cadid[4]);
                        intent2.putExtra("id5",cadid[5]);
                        intent2.putExtra("id6",cadid[6]);
                        intent2.putExtra("id7",cadid[7]);
                        intent2.putExtra("id8", cadid[8]);

                        startActivity(intent2);
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

