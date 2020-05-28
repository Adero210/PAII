package ceti.edu.paii.activities.listening.speaking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Speaking_1_Activity extends AppCompatActivity {

    ImageButton play;
    MediaPlayer mp;
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

    private FloatingActionButton record;
    private TextView titulo;
    //////////////////////////////////////////////////
    private TextView tvxResult;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    ////////////////////////////////////////////////
    private StorageReference mAudioStorage;
    private  String numAletorio ="";
    private Button revisar;
    private Button continuar;
    private String curso;
    private String lesson;

    private String boceto = "2";


    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";
    private String respuestaFromBD = "";
    private String respuestaUser="";
    private MediaPlayer mediaPlayer,incorrect;

    final int REQUEST_PERMISSION_CODE = 1000;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_1_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis = getIntent().getStringExtra("calificacion");
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


        if(actHechas<=8){
            if (!checkPermissionFromDivice()) {
                requestPermission();
            }
            //Botones
            play = findViewById(R.id.play_pause_activity_speaking_1);
            record = findViewById(R.id.play_pause_recorder_activity_speaking_1);

            // se crea media player del audio a escuchar
            mp = new MediaPlayer();

            progressDialog =  new ProgressDialog(Speaking_1_Activity.this);

            mAudioStorage = FirebaseStorage.getInstance().getReference();

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
            incorrect = MediaPlayer.create(this,R.raw.wrong);
            revisar = findViewById(R.id.button_activity_speaking_1);
            continuar = findViewById(R.id.button_activity_con_speaking_1);

            //////////////////////////Speech to text ///////////////////////////////////////////////////
            tvxResult = findViewById(R.id.answer_speak);
            titulo = findViewById(R.id.titulo_speaking_1);
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {

                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    if(matches!=null){
                        respuestaUser = matches.get(0);
                        tvxResult.setText(":3");

                    }
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            });

            ////////////////////////////////////////////////////////////////////////////////////////////

            Log.i("curso",curso);
            numAletorio = "1";
            if(curso.equals("Inglés")){
                titulo.setText("Listen and Repeat");
            }else if(curso.equals("Italiano")){
                titulo.setText("Ascolta e ripeti");
            }

            int lessonint = Integer.parseInt(lesson);

            if(lessonint == 1) lessonint = 21;

            if(curso.equals("Italiano")){
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
            opsciones();


        }
        else {
            Intent i = new Intent(Speaking_1_Activity.this, ResumenActividad.class);
            String tipo = "Habla";
            i.putExtra("tipo",tipo);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
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
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {

                        JSONObject object =  jsonArray.getJSONObject(0);

                        JSONObject audios = object.getJSONObject("audio");

                        respuestaFromBD = object.getString("question" );
                        respuestaFromBD = respuestaFromBD.toLowerCase();

                        String audio = audios.getString("fileName");


                        for(int i = 0; i <= cadid.length; i++){
                            String aux = respuestaFromBD;
                            if(respuestaFromBD.equals(cadid[i])) {

                                Intent in = new Intent(Speaking_1_Activity.this, Speaking_1_Activity.class);
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

                        mAudioStorage.child("audiosAtividades").child(audio).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                try {
                                    mp.reset();
                                    mp.setDataSource(Speaking_1_Activity.this,uri);
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
                    Toast.makeText(Speaking_1_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);
                        Toast.makeText(Speaking_1_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("numberOfQuestions",numAle);
                params.put("lectionId", String.valueOf(lessonint2));
                params.put("sketch",boceto);
                params.put("typeName","Habla");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void opsciones() {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    //
                    mp.pause();
                } else {
                    //
                    mp.start();
                }
            }
        });


        record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        speechRecognizer.stopListening();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        tvxResult.setText("");

                        tvxResult.setText("LISTENING...");
                        speechRecognizer.startListening(speechRecognizerIntent);
                        break;
                }
                return false;
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
                    calis = String.valueOf(cali);

                    if(curso.equals("Ingles")){
                        Toast.makeText(Speaking_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Speaking_1_Activity.this,"corretta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    cali = cali + 0;
                    calis = String.valueOf(cali);

                    incorrect.start();
                    if(curso.equals("Ingles")){
                        Toast.makeText(Speaking_1_Activity.this,"wrong: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Speaking_1_Activity.this,"sbagliata: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
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
                String num ="";
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Speaking_1_Activity.this, Speaking_1_Activity.class);
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
                        Intent intent = new Intent(Speaking_1_Activity.this, Speaking_2_Activity.class);
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
                        Intent intent2 = new Intent(Speaking_1_Activity.this, Speaking_3_Activity.class);
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


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO

        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permisos dados",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Permisos NO dados",Toast.LENGTH_SHORT).show();
                }
            }
            break;

        }
    }

    private boolean checkPermissionFromDivice() {
        int write_external_storafe_result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);
        return write_external_storafe_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
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
