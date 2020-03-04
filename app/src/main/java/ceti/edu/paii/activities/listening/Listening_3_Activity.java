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

    Button play_pause;
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

    int actHechas, cali;

    private String calis, actHechass;
    private Button calificar;
    private Button continuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_3_);
        if(actHechas <= 8) {
            curso  = getIntent().getStringExtra("curso");
            lesson = getIntent().getStringExtra("lesson");
            calis   = getIntent().getStringExtra("calificacion");
            actHechass = getIntent().getStringExtra("actividad");

            cali = Integer.valueOf(calis);
            actHechas = Integer.valueOf(actHechass);

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
                String num ="";
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Listening_3_Activity.this, Listening_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Listening_3_Activity.this, Listening_3_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent2= new Intent(Listening_3_Activity.this, Listening_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("calificacion",String.valueOf(cali));
                        intent2.putExtra("actividad",String.valueOf(actHechas));
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

                    Toast.makeText(Listening_3_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
                    cali = cali + 0;

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
                        progressDialog.dismiss();

                        JSONObject object =  jsonArray.getJSONObject(0);
                        JSONArray options = object.getJSONArray("options");

                        comun.Optionss opciones = comun.getOptions(options);

                        JSONObject jsonObject1audio = object.getJSONObject("audio");

                        String audio = jsonObject1audio.getString("rutaAudio").trim();


                        preAux = object.getString("question" ).trim();
                        String preAux2 []= preAux.split("~");
                        preAux = preAux2[0]+" ___ " + preAux2[2];


                        respuestaFromBD = object.getString("correct");

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
