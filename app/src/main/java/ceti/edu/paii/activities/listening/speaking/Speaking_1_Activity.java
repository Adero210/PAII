package ceti.edu.paii.activities.listening.speaking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.Listening_4_Activity;
import ceti.edu.paii.comun.comun;

public class Speaking_1_Activity extends AppCompatActivity {

    Button play;
    MediaPlayer mp;

    private FloatingActionButton record;

    private TextView titulo;
    //////////////////////////////////////////////////
    private TextView tvxResult;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    ////////////////////////////////////////////////

    private StorageReference mAudioStorage;

    private Button revisar;
    private Button continuar;

    private String curso;
    private String lesson;

    private String boceto = "1";

    private int numerosPreuntas = 5;



    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";
    private String respuestaFromBD = "";
    private String respuestaUser="";
    private MediaPlayer mediaPlayer,incorrect;


    final int REQUEST_PERMISSION_CODE = 1000;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_1_);
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
                  // tvxResult.setText(matches.get(0));
                   respuestaUser = matches.get(0);
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


        curso = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");

        Log.i("curso",curso);
        String numAletorio = comun.aleatorio(numerosPreuntas);

        if(curso.equals("Ingles")){
            titulo.setText("Listen and Repeat");
        }else if(curso.equals("Italiano")){
            titulo.setText("Ascolta e ripeti");
        }

        int lessonint = Integer.parseInt(lesson);

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

                        }
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
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("boceto",boceto);
                params.put("type","speaking");

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
                        tvxResult.setText("yOU WILL SEE THE INPUT HERE");
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
                    if(curso.equals("Ingles")){
                        Toast.makeText(Speaking_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Speaking_1_Activity.this,"corretta",Toast.LENGTH_SHORT).show();
                    }
                }else{
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

}
