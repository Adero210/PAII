package ceti.edu.paii.activities.listening.writing;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.EditText;
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
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_1_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_2_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_3_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_4_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Writing_1_Activity extends AppCompatActivity {

    private TextView  oracion;
    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";

    private String boceto = "3";

    private String respuestaFromBD = "";
    private EditText respuestaUser;
    private MediaPlayer mediaPlayer,incorrect;

    private Button calificar;
    private Button continuar;

    private String curso;
    private String lesson;

    int actHechas, cali;
    private String calis, actHechasS;

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

    private  String numAletorio ="";

    private FirebaseStorage storage;
    private StorageReference mAudioStorage;

    private ImageButton play_pause;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_1_);

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


        if(actHechas<=8) {

            progressDialog = new ProgressDialog(Writing_1_Activity.this);

            storage = FirebaseStorage.getInstance();
            mAudioStorage = FirebaseStorage.getInstance().getReference();

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);
            oracion = findViewById(R.id.textview_1_activity_writing_1);
            respuestaUser = findViewById(R.id.editText_1_activity_writing_1);

            calificar = findViewById(R.id.button_activity_Writing_1);
            continuar = findViewById(R.id.button_continuar_activity_writing_1);

            play_pause = findViewById(R.id.play_pause_1_activity_writing_1);

            mp = new MediaPlayer();

            numAletorio = "1";
            if (curso.equals("English")) {
                oracion.setText("Listen and repeat");
            } else if (curso.equals("Italiano")) {
                oracion.setText("Ascolta e ripeti");
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
            opciones();

        }else {
            Intent i = new Intent(Writing_1_Activity.this, ResumenActividad.class);
            String tipo = "Escritura";
            i.putExtra("tipo",tipo);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);
        }
    }

    private void opciones() {
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calificar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                //String fre = String.valueOf(respuestaUser.getText());
                String ans = String.valueOf(respuestaUser.getText());
                String resMay = respuestaFromBD.toUpperCase();
                String resMin = respuestaFromBD.toLowerCase();

                if(ans.equals(respuestaFromBD)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);

                    Toast.makeText(Writing_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else if(ans.equals(resMay)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);

                    Toast.makeText(Writing_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else if(ans.equals(resMin)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);

                    Toast.makeText(Writing_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
                    cali = cali + 0;
                    calis = String.valueOf(cali);

                    Toast.makeText(Writing_1_Activity.this,"Correct answer: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                        Intent i = new Intent(Writing_1_Activity.this, Writing_1_Activity.class);
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
                        i.putExtra("id8",cadid[8]);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Writing_1_Activity.this, Writing_2_Activity.class);
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
                        intent.putExtra("id8",cadid[8]);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Writing_1_Activity.this, Writing_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("calificacion",calis);
                        intent1.putExtra("actividad",actHechasS);
                        intent1.putExtra("id0",cadid[0]);
                        intent1.putExtra("id1",cadid[1]);
                        intent1.putExtra("id2",cadid[2]);
                        intent1.putExtra("id3",cadid[3]);
                        intent1.putExtra("id4",cadid[4]);
                        intent1.putExtra("id5",cadid[5]);
                        intent1.putExtra("id6",cadid[6]);
                        intent1.putExtra("id7",cadid[7]);
                        intent1.putExtra("id8",cadid[8]);
                        startActivity(intent1);
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

                    Log.i("ahhhha","entre al try");

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {
                        Log.i("ahhhha","success");
                        JSONObject object =  jsonArray.getJSONObject(0);

                        respuestaFromBD = object.getString("correct");

                        JSONObject audioRoute = object.getJSONObject("audio");


                        String audio = audioRoute.getString("fileName").trim();

                        for(int i = 0; i <= cadid.length; i++){
                            String aux = respuestaFromBD;
                            if(respuestaFromBD.equals(cadid[i])) {

                                Intent in = new Intent(Writing_1_Activity.this, Writing_1_Activity.class);
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
                                    cadid[i] = respuestaFromBD;

                                    break;
                                }
                            }

                        }

                        Log.i("aaa",cadid[0]+cadid[1]);


                        progressDialog.dismiss();

                        Log.i("RESPUESTACORRECTA",audio+respuestaFromBD);
                        mAudioStorage.child("audiosAtividades").child(audio).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                try {
                                    mp.setDataSource(Writing_1_Activity.this,uri);
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
                    Toast.makeText(Writing_1_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);
                        Toast.makeText(Writing_1_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("numberOfQuestions",numAle);
                params.put("lectionId", String.valueOf(lessonint2));
                params.put("sketch",boceto);
                params.put("typeName","Escritura");
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
