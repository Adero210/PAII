package ceti.edu.paii.activities.listening;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.grammar.Grammar_3_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Listening_3_Activity extends AppCompatActivity {

    ImageButton play_pause;
    MediaPlayer mp;


    private String numAletorio;
    private String boceto = "3";
    private StorageReference mAudioStorage;
    private String curso;
    private String lesson;

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";
    private String respuestaFromBD = "";
    private String respuestaUser ="";
    private MediaPlayer mediaPlayer,incorrect;

    private String preAux;
    private TextView titulo;
    private TextView pregunta;
    private Button opcUno;
    private Button opcDos;
    private Button opcTres;
    private Button opcCuatro;

    private int actHechas;
    private int cali;

    private String calis, actHechass;
    private Button calificar;
    private Button continuar;

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
        setContentView(R.layout.activity_listening_3_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechass = getIntent().getStringExtra("actividad");
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
        actHechas = Integer.parseInt(actHechass);


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



            titulo = findViewById(R.id.titulo_list_3);
            pregunta = findViewById(R.id.textview_act_listening_3);
            opcUno = findViewById(R.id.opcion1_activity_listening_3);
            opcDos = findViewById(R.id.opcion2_activity_listening_3);
            opcTres = findViewById(R.id.opcion3_activity_listening_3);
            opcCuatro = findViewById(R.id.opcion4_activity_listening_3);

            calificar = findViewById(R.id.button_activity_listening_3);
            continuar = findViewById(R.id.button_activity_con_listening_3);
            progressDialog = new ProgressDialog(Listening_3_Activity.this);

            mAudioStorage = FirebaseStorage.getInstance().getReference();

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            play_pause =  findViewById(R.id.play_pause_activity_listening_3);

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
            Log.i("aa", String.valueOf(cadid));



            botones();
        }else {
            Intent i = new Intent(Listening_3_Activity.this, ResumenActividad.class);
            String tipo = "Escucha";
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("tipo",tipo);
            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);
        }
    }

    private void botones() {
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    play_pause.setBackgroundResource(R.drawable.play);
                }else{
                    mp.start();
                    play_pause.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        opcUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opcUno.getText();
                String ora = preAux.replace("___",word);
                pregunta.setText(ora);
                respuestaUser = word;
            }
        });
        opcDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opcDos.getText();
                String ora = preAux.replace("___",word);
                pregunta.setText(ora);
                respuestaUser = word;
            }
        });
        opcTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opcTres.getText();
                String ora = preAux.replace("___",word);
                pregunta.setText(ora);
                respuestaUser = word;
            }
        });
        opcCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opcCuatro.getText();
                String ora = preAux.replace("___",word);
                pregunta.setText(ora);
                respuestaUser = word;
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actHechas++;
                actHechass = String.valueOf(actHechas);
                Log.i("aaaa",cadid[0]);


                String num ="";
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Listening_3_Activity.this, Listening_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",calis);
                        i.putExtra("actividad",actHechass);
                        i.putExtra("id0",cadid[0]);
                        i.putExtra("id1",cadid[1]);
                        i.putExtra("id2",cadid[2]);
                        i.putExtra("id3",cadid[3]);
                        i.putExtra("id4",cadid[4]);
                        i.putExtra("id5",cadid[5]);
                        i.putExtra("id6",cadid[6]);
                        i.putExtra("id7",cadid[7]);
                        i.putExtra("id8",cadid[8]);

                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Listening_3_Activity.this, Listening_3_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",calis);
                        intent.putExtra("actividad",actHechass);
                        intent.putExtra("id0",cadid[0]);
                        intent.putExtra("id1",cadid[1]);
                        intent.putExtra("id2",cadid[2]);
                        intent.putExtra("id3",cadid[3]);
                        intent.putExtra("id4",cadid[4]);
                        intent.putExtra("id5",cadid[5]);
                        intent.putExtra("id6",cadid[6]);
                        intent.putExtra("id7",cadid[7]);
                        intent.putExtra("id8",cadid[8]);

                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent2= new Intent(Listening_3_Activity.this, Listening_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("calificacion",calis);
                        intent2.putExtra("actividad",actHechass);
                        intent2.putExtra("id0",cadid[0]);
                        intent2.putExtra("id1",cadid[1]);
                        intent2.putExtra("id2",cadid[2]);
                        intent2.putExtra("id3",cadid[3]);
                        intent2.putExtra("id4",cadid[4]);
                        intent2.putExtra("id5",cadid[5]);
                        intent2.putExtra("id6",cadid[6]);
                        intent2.putExtra("id7",cadid[7]);
                        intent2.putExtra("id8",cadid[8]);

                        startActivity(intent2);
                        break;

                }

            }
        });
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calificar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                if(respuestaUser.equals(respuestaFromBD)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);

                    Toast.makeText(Listening_3_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
                    cali = cali + 0;
                    calis = String.valueOf(cali);

                    Toast.makeText(Listening_3_Activity.this,"Correct answer: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
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
                    Log.i("ahhhha","entre al try");
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {

                        JSONObject object =  jsonArray.getJSONObject(0);
                        JSONArray options = object.getJSONArray("options");

                        comun.Optionss opciones = comun.getOptions(options);

                        JSONObject jsonObject1audio = object.getJSONObject("audio");

                        String audio = jsonObject1audio.getString("fileName").trim();


                        preAux = object.getString("question" ).trim();
                        String preAux2 []= preAux.split("~");
                        preAux = preAux2[0]+" ___ " + preAux2[2];


                        respuestaFromBD = object.getString("correct");


                        for(int i = 0; i <= cadid.length; i++){
                            String aux = respuestaFromBD;
                            if(respuestaFromBD.equals(cadid[i])) {

                                Intent in = new Intent(Listening_3_Activity.this, Listening_3_Activity.class);
                                in.putExtra("curso", curso);
                                in.putExtra("lesson", lesson);
                                in.putExtra("calificacion", calis);
                                in.putExtra("actividad", actHechass);
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
                                    cadid[i] = respuestaFromBD;

                                    break;
                                }
                            }

                        }

                        Log.i("aaa",cadid[0]+cadid[1]);


                        progressDialog.dismiss();

                        String opcionA = opciones.opcA;
                        String opcionB = opciones.opcB;
                        String opcionC = opciones.opcC;
                        String opcionD = opciones.opcD;

                        pregunta.setText(preAux);

                        opcUno.setText(opcionA);
                        opcDos.setText(opcionB);
                        opcTres.setText(opcionC);
                        opcCuatro.setText(opcionD);

                        mAudioStorage.child("audiosAtividades").child(audio).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri1) {
                                try {

                                    mp.reset();
                                    mp.setDataSource(Listening_3_Activity.this,uri1);
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
                    Toast.makeText(Listening_3_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Listening_3_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();
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
