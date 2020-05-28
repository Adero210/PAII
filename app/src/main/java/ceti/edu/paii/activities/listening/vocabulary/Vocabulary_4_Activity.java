package ceti.edu.paii.activities.listening.vocabulary;

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
import ceti.edu.paii.activities.listening.writing.Writing_1_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Vocabulary_4_Activity extends AppCompatActivity {



    private TextView titulo;
    private TextView oracion;
    private Button opc1;
    private Button opc2;
    private Button opc3;
    private Button opc4;

    private Button opc1Sel;
    private Button opc2Sel;
    private Button opc3Sel;
    private Button opc4Sel;

    private Button calificar;
    private Button continuar;

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "genericAct.php";

    private String boceto = "4";

    private String respuestaFromBD = "";
    private String respuestaUser="";
    private String tipo;

    private MediaPlayer mediaPlayer,incorrect;

    private String curso;
    private String lesson;


    int actHechas, cali;
    private String b1,b2,b3,b4, calis, actHechasS;


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
    private int numerosPreuntas = 5;


    private FirebaseStorage storage;
    private StorageReference mAudioStorage;


    ImageButton play_pause;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_4_);

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


            progressDialog = new ProgressDialog(Vocabulary_4_Activity.this);

            storage = FirebaseStorage.getInstance();
            mAudioStorage = FirebaseStorage.getInstance().getReference();

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);
            play_pause = findViewById(R.id.play_pause_activity_vocabulary_4);

            titulo = findViewById(R.id.titulo_vocabulary_4);
            oracion = findViewById(R.id.textinput_vocabulary_4);

            opc1 = findViewById(R.id.opcion1_activity_vocabulary_4);
            opc2 = findViewById(R.id.opcion2_activity_vocabulary_4);
            opc3 = findViewById(R.id.opcion3_activity_vocabulary_4);
            opc4 = findViewById(R.id.opcion4_activity_vocabulary_4);

            opc1Sel = findViewById(R.id.opcion1_activity_sel_vocabulary_4);
            opc2Sel = findViewById(R.id.opcion2_activity_sel_vocabulary_4);
            opc3Sel = findViewById(R.id.opcion3_activity_sel_vocabulary_4);
            opc4Sel = findViewById(R.id.opcion4_activity_sel_vocabulary_4);


            calificar = findViewById(R.id.button_activity_vocabulary_4);
            continuar = findViewById(R.id.button_activity_con_vocabulary_4);


            mp = new MediaPlayer();

            Log.i("curso", curso);
            numAletorio = comun.aleatorio(numerosPreuntas);

            if(b4.contains(numAletorio)) {
                if (curso.equals("Ingles")) {
                    titulo.setText("choose the correct option");
                    oracion.setText("listen and choice");
                } else if (curso.equals("Italiano")) {
                    titulo.setText("scegli la traduzione opzione");
                    oracion.setText("ascolta e scegli");
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

                Intent i = new Intent(Vocabulary_4_Activity.this, Vocabulary_4_Activity.class);
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
            Intent i = new Intent(Vocabulary_4_Activity.this, ResumenActividad.class);
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

                                respuestaFromBD = object.getString("respuestac" + h);
                                String audio = object.getString("urlAudio" + h).trim();

                                String opcionA = object.getString("opcA" +h);
                                String opcionB = object.getString("opcB" +h);
                                String opcionC = object.getString("opcC" +h);
                                String opcionD = object.getString("opcD" +h);

                                opc1.setText(opcionA);
                                opc2.setText(opcionB);
                                opc3.setText(opcionC);
                                opc4.setText(opcionD);
                                opc1Sel.setText(opcionA);
                                opc2Sel.setText(opcionB);
                                opc3Sel.setText(opcionC);
                                opc4Sel.setText(opcionD);


                                mAudioStorage.child("audiosAtividades").child(audio).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        try {
                                            mp.setDataSource(Vocabulary_4_Activity.this,uri);
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

                    Toast.makeText(Vocabulary_4_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Vocabulary_4_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


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

    private void opciones() {
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

        opc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc1.setVisibility(View.INVISIBLE);
                opc2Sel.setVisibility(View.INVISIBLE);
                opc3Sel.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.INVISIBLE);

                opc3.setVisibility(View.VISIBLE);
                opc2.setVisibility(View.VISIBLE);
                opc4.setVisibility(View.VISIBLE);
                opc1Sel.setVisibility(View.VISIBLE);
                respuestaUser = (String) opc1Sel.getText();
            }
        });

        opc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc2.setVisibility(View.INVISIBLE);
                opc1Sel.setVisibility(View.INVISIBLE);
                opc3Sel.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.INVISIBLE);


                opc1.setVisibility(View.VISIBLE);
                opc3.setVisibility(View.VISIBLE);
                opc4.setVisibility(View.VISIBLE);
                opc2Sel.setVisibility(View.VISIBLE);
                respuestaUser = (String) opc2Sel.getText();
            }
        });

        opc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc3.setVisibility(View.INVISIBLE);
                opc2Sel.setVisibility(View.INVISIBLE);
                opc1Sel.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.INVISIBLE);


                opc1.setVisibility(View.VISIBLE);
                opc2.setVisibility(View.VISIBLE);
                opc4.setVisibility(View.VISIBLE);
                opc3Sel.setVisibility(View.VISIBLE);
                respuestaUser = (String) opc3Sel.getText();
            }
        });

        opc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc4.setVisibility(View.INVISIBLE);
                opc2Sel.setVisibility(View.INVISIBLE);
                opc1Sel.setVisibility(View.INVISIBLE);
                opc3Sel.setVisibility(View.INVISIBLE);


                opc1.setVisibility(View.VISIBLE);
                opc2.setVisibility(View.VISIBLE);
                opc3.setVisibility(View.VISIBLE);
                opc4Sel.setVisibility(View.VISIBLE);
                respuestaUser = (String) opc4Sel.getText();
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

                    if(curso.equals("Ingles")){
                        Toast.makeText(Vocabulary_4_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Vocabulary_4_Activity.this,"corretta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    incorrect.start();
                    cali = cali + 0;

                    if(curso.equals("Ingles")){
                        Toast.makeText(Vocabulary_4_Activity.this,"wrong",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Vocabulary_4_Activity.this,"sbagliata",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b4N = b4.replaceAll(numAletorio,"");
                actHechas++;
                String num ="";
                num = comun.aleatorio(4);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Vocabulary_4_Activity.this, Vocabulary_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        i.putExtra("boceto1",b1);
                        i.putExtra("boceto2",b2);
                        i.putExtra("boceto3",b3);
                        i.putExtra("boceto4",b4N);

                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Vocabulary_4_Activity.this, Vocabulary_2_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1);
                        intent.putExtra("boceto2",b2);
                        intent.putExtra("boceto3",b3);
                        intent.putExtra("boceto4",b4N);

                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Vocabulary_4_Activity.this, Vocabulary_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);

                        intent1.putExtra("calificacion",String.valueOf(cali));
                        intent1.putExtra("actividad",String.valueOf(actHechas));
                        intent1.putExtra("boceto1",b1);
                        intent1.putExtra("boceto2",b2);
                        intent1.putExtra("boceto3",b3);
                        intent1.putExtra("boceto4",b4N);

                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Vocabulary_4_Activity.this, Vocabulary_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);

                        intent2.putExtra("calificacion",String.valueOf(cali));
                        intent2.putExtra("actividad",String.valueOf(actHechas));
                        intent2.putExtra("boceto1",b1);
                        intent2.putExtra("boceto2",b2);
                        intent2.putExtra("boceto3",b3);
                        intent2.putExtra("boceto4",b4N);

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
    private String aleatorio(){
        // AleatoriSinRepeticion();
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
            num = String.valueOf(pos);
        }
        Log.i("Numeros",pCartas.toString());

        return num;
    }

    @Override
    public void onBackPressed(){
        return;
    }
}
