package ceti.edu.paii.activities.listening.writing;

import android.app.ProgressDialog;
import android.content.Context;
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
import ceti.edu.paii.comun.comun;

public class Writing_1_Activity extends AppCompatActivity {

    private TextView  oracionText;
    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";

    private String boceto = "1";

    private String respuestaFromBD = "";
    private EditText respuestaUser;
    private MediaPlayer mediaPlayer,incorrect;

    private Button calificar;

    private FirebaseStorage storage;
    private StorageReference mAudioStorage;

    private Button continuar;

    Button play_pause;
    MediaPlayer mp;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_1_);

        progressDialog =  new ProgressDialog(Writing_1_Activity.this);

        storage = FirebaseStorage.getInstance();
        mAudioStorage = FirebaseStorage.getInstance().getReference();

        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);

        mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
        incorrect = MediaPlayer.create(this,R.raw.wrong);
        oracionText = findViewById(R.id.textview_1_activity_writing_1);
        respuestaUser = findViewById(R.id.editText_1_activity_writing_1);

        calificar = findViewById(R.id.button_activity_Writing_1);
        continuar = findViewById(R.id.button_continuar_activity_writing_1);

        play_pause = findViewById(R.id.play_pause_1_activity_writing_1);

        mp = new MediaPlayer();

        String curso = getIntent().getStringExtra("curso");
        String lesson = getIntent().getStringExtra("lesson");

        String numAletorio = aleatorio();

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
                    Toast.makeText(Writing_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();

                }else if(ans.equals(resMay)){

                    mediaPlayer.start();
                    Toast.makeText(Writing_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();

                }else if(ans.equals(resMin)){
                    mediaPlayer.start();
                    Toast.makeText(Writing_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
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

                                String pregunta = object.getString("pregunta" + h).trim();
                                respuestaFromBD = object.getString("respuestac" + h);
                                String audio = object.getString("urlAudio" + h).trim();

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

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

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
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("boceto",boceto);
                params.put("type","writing");


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

    /*  @Override
    public void onBackPressed(){
        return;
    }*/

}
