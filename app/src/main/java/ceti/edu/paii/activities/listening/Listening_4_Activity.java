package ceti.edu.paii.activities.listening;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.speaking.Speaking_1_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_4_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Listening_4_Activity extends AppCompatActivity {

    private TextView titulo;

    private Button opc1;
    private Button opc2;
    private Button opc3;
    private Button opc4;

    private Button opc1Sel;
    private Button opc2Sel;
    private Button opc3Sel;
    private Button opc4Sel;

    private  String numAletorio ="";

    private Button calificar;
    private Button continuar;

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";

    private String boceto = "4";

    private String respuestaFromBD = "";
    private String respuestaUser ="";
    private MediaPlayer mediaPlayer,incorrect;

    private String curso;
    private String lesson;

    private FirebaseStorage storage;
    private StorageReference mAudioStorage;

    int actHechas, cali;

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

    private String bto;
    protected String cWord;
    private String wWord;
    private ProgressDialog calificacion;

    String[] cadid = new String[30];
    String calis;
    String actHechass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_4_);
        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
       calis = getIntent().getStringExtra("calificacion");
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


        if(actHechas<=8) {


            play_pause =  findViewById(R.id.play_pause_activity_listening_4);

            progressDialog = new ProgressDialog(Listening_4_Activity.this);

            storage = FirebaseStorage.getInstance();
            mAudioStorage = FirebaseStorage.getInstance().getReference();

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            titulo = findViewById(R.id.titulo_listening_4);

            opc1 = findViewById(R.id.opcion1_activity_listening_4);
            opc2 = findViewById(R.id.opcion2_activity_listening_4);
            opc3 = findViewById(R.id.opcion3_activity_listening_4);
            opc4 = findViewById(R.id.opcion4_activity_listening_4);

            opc1Sel = findViewById(R.id.opcion1_activity_sel_listening_4);
            opc2Sel = findViewById(R.id.opcion2_activity_sel_listening_4);
            opc3Sel = findViewById(R.id.opcion3_activity_sel_listening_4);
            opc4Sel = findViewById(R.id.opcion4_activity_sel_listening_4);


            calificar = findViewById(R.id.button_activity_listening_4);
            continuar = findViewById(R.id.button_activity_con_listening_4);


            mp = new MediaPlayer();

            Log.i("curso", curso);
            numAletorio = "1";

                if (curso.equals("English")) {
                    titulo.setText("Choose the correct option");
                    bto = "Continue";
                    cWord = "Correct!";
                    wWord = "Wrong: ";
                } else if (curso.equals("Italiano")) {
                    titulo.setText("Scegli la traduzione opzione");
                    bto = "Continua";
                    cWord = "Corretto!";
                    wWord = "Strizzare: ";
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

            calificacion =  new ProgressDialog(Listening_4_Activity.this);
            calificacion.setButton(AlertDialog.BUTTON_NEGATIVE, bto, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    progressDialog.dismiss();
                    actHechas++;
                    actHechass = String.valueOf(actHechas);
                    Log.i("aaaa",cadid[0]);
                    String num;
                    num = comun.aleatorio(3);
                    Log.i("numeroRamdon",num);
                    switch (num){
                        case "0":
                            Intent i = new Intent(Listening_4_Activity.this, Listening_1_Activity.class);
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
                            i.putExtra("id8", cadid[8]);

                            startActivity(i);
                            break;

                        case "1":
                            Intent intent = new Intent(Listening_4_Activity.this, Listening_3_Activity.class);
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
                            intent.putExtra("id8", cadid[8]);

                            startActivity(intent);
                            break;

                        case "2":
                            Intent intent2= new Intent(Listening_4_Activity.this, Listening_4_Activity.class);
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
                            intent2.putExtra("id8", cadid[8]);

                            startActivity(intent2);
                            break;

                    }
                }
            });
            calificacion.setCancelable(false);

                bringTheInfo(lessonint - 1, numAletorio);
                opciones();

            }else {
                Intent i = new Intent(Listening_4_Activity.this, ResumenActividad.class);
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


                        JSONObject object =  jsonArray.getJSONObject(0);

                        JSONArray options = object.getJSONArray("options");

                        comun.Optionss opciones = comun.getOptions(options);

                        JSONObject jsonObject1audio = object.getJSONObject("audio");
                        String audio = jsonObject1audio.getString("fileName").trim();


                        respuestaFromBD = object.getString("correct");


                        for(int i = 0; i <= cadid.length; i++){
                            String aux = respuestaFromBD;
                            if(respuestaFromBD.equals(cadid[i])) {

                                Intent in = new Intent(Listening_4_Activity.this, Listening_4_Activity.class);
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
                        Log.i("aaaa",cadid[0] + cadid[1]);

                        String opcionA = opciones.opcA;
                        String opcionB = opciones.opcB;
                        String opcionC = opciones.opcC;
                        String opcionD = opciones.opcD;


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
                                            mp.reset();
                                            mp.setDataSource(Listening_4_Activity.this,uri);
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

                    Toast.makeText(Listening_4_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Listening_4_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


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

    private void opciones() {
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
                if(respuestaUser.equals(respuestaFromBD)){
                    calificacion.setMessage(cWord);
                    calificacion.setProgressStyle(5);
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);
                    calificacion.show();
                }else{
                    incorrect.start();
                    calificacion.setMessage(wWord + respuestaFromBD);
                    calificacion.setProgressStyle(2);
                    incorrect.start();
                    cali = cali + 0;
                    calis = String.valueOf(cali);
                    calificacion.show();
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
