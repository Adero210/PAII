package ceti.edu.paii.activities.listening.vocabulary;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.grammar.Grammar_1_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_3_Activity;
import ceti.edu.paii.comun.comun;

public class Vocabulary_1_Activity extends AppCompatActivity {

    private TextView oracion;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;

    private Button revisar;
    private Button continuar;

    private String curso;
    private String lesson;

    private ImageView imap1;
    private ImageView imap2;
    private ImageView imap3;
    private ImageView imap4;

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/vocabulary1Act.php";
    private String respuestaFromBD = "";
    private String respuestaUser;
    private MediaPlayer mediaPlayer,incorrect;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_1_);

        progressDialog =  new ProgressDialog(Vocabulary_1_Activity.this);

        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);

        mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
        incorrect = MediaPlayer.create(this,R.raw.wrong);

        oracion = findViewById(R.id.titulo_vocabulary_1);
        img1 = findViewById(R.id.image_1_vocabulary_1);
        img2 = findViewById(R.id.image_2_vocabulary_1);
        img3 = findViewById(R.id.image_3_vocabulary_1);
        img4 = findViewById(R.id.image_4_vocabulary_1);

        imap1 = findViewById(R.id.palomita1_voca_1);
        imap2 = findViewById(R.id.palomita2_voca_1);
        imap3 = findViewById(R.id.palomita3_voca_1);
        imap4 = findViewById(R.id.palomita4_voca_1);

        text1 = findViewById(R.id.textimage_1_vocabulary_1);
        text2 = findViewById(R.id.textimage_2_vocabulary_1);
        text3 = findViewById(R.id.textimage_3_vocabulary_1);
        text4 = findViewById(R.id.textimage_4_vocabulary_1);

        imap1.setVisibility(View.GONE);
        imap2.setVisibility(View.GONE);
        imap3.setVisibility(View.GONE);
        imap4.setVisibility(View.GONE);

        revisar = findViewById(R.id.button_activity_vocabulary_1);
        continuar = findViewById(R.id.button_activity_con_vocabulary_1);

        curso = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");

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
        opciones();
    }

    private void opciones() {

        img1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap1.setVisibility(View.VISIBLE);
                imap2.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text1.getText();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap2.setVisibility(View.VISIBLE);
                imap1.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text2.getText();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap3.setVisibility(View.VISIBLE);
                imap2.setVisibility(View.GONE);
                imap1.setVisibility(View.GONE);
                imap4.setVisibility(View.GONE);
                respuestaUser = (String) text3.getText();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Vocabulary_1_Activity.this, R.xml.image_click));
                imap4.setVisibility(View.VISIBLE);
                imap2.setVisibility(View.GONE);
                imap3.setVisibility(View.GONE);
                imap1.setVisibility(View.GONE);
                respuestaUser = (String) text4.getText();
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
                        Toast.makeText(Vocabulary_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Vocabulary_1_Activity.this,"corretta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    incorrect.start();
                    if(curso.equals("Ingles")){
                        Toast.makeText(Vocabulary_1_Activity.this,"wrong",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Vocabulary_1_Activity.this,"sbagliata",Toast.LENGTH_SHORT).show();
                    }
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

                                String pregunta = object.getString("pregunta").trim();
                                respuestaFromBD = object.getString("respuestac");
                                String imagenfrom1 = object.getString("urlImage0");
                                String imagenfrom2 = object.getString("urlImage1");
                                String imagenfrom3 = object.getString("urlImage2");
                                String imagenfrom4 = object.getString("urlImage3");

                                String tImagenfrom1 = object.getString("textoImage0");
                                String tImagenfrom2 = object.getString("textoImage1");
                                String tImagenfrom3 = object.getString("textoImage2");
                                String tImagenfrom4 = object.getString("textoImage3");

                                text1.setText(tImagenfrom1);
                                text2.setText(tImagenfrom2);
                                text3.setText(tImagenfrom3);
                                text4.setText(tImagenfrom4);


                                oracion.setText(pregunta);

                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom1)
                                        .into(img1);
                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom2)
                                        .into(img2);
                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom3)
                                        .into(img3);
                                Glide.with(Vocabulary_1_Activity.this)
                                        .load(imagenfrom4)
                                        .into(img4);

                            }

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Vocabulary_1_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Vocabulary_1_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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

    /*@Override
    public void onBackPressed(){
        return;
    }*/
}
