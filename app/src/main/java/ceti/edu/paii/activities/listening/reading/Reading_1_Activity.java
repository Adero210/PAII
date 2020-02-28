package ceti.edu.paii.activities.listening.reading;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_1_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_2_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_3_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_4_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.actividades;
import ceti.edu.paii.view.ResumenActividad;

public class Reading_1_Activity extends AppCompatActivity {

    private TextView oracion;


    private MediaPlayer mediaPlayer,incorrect;
    private ImageView imagenVIew;
    private TextView descriptionTextView;
    private Button opcA,opcA2, opcB,opcB2,
            opcC, opcC2,opcD,opcD2;

    private Button revisar;
    private Button continuar;

    private String curso;
    private String lesson;

    private String tipo;

    int actHechas, cali;
    private String b1,b2,b3,b4, calis, actHechasS;

    private  String numAletorio ="";


    private StorageReference mImageStorage;

    private String boceto = "2";

    private int numerosPreuntas = 5;

    private  String res = "NO", resCheck ="";

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "genericAct.php";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_1_);

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
        oracion = findViewById(R.id.oracion_activity_reading_1);

        if(actHechas<=8) {


            progressDialog = new ProgressDialog(Reading_1_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);


            descriptionTextView = findViewById(R.id.description_text_activity_Reading_1);
            imagenVIew = findViewById(R.id.image_activity_Reading_1);
            opcA = findViewById(R.id.opcion1_activity_Reading_1);
            opcA2 = findViewById(R.id.opcion1_activity_Reading_1_1);
            opcB = findViewById(R.id.opcion2_activity_Reading_1);
            opcB2 = findViewById(R.id.opcion2_activity_Reading_1_1);
            opcC = findViewById(R.id.opcion3_activity_Reading_1);
            opcC2 = findViewById(R.id.opcion3_activity_Reading_1_1);
            opcD = findViewById(R.id.opcion4_activity_Reading_1);
            opcD2 = findViewById(R.id.opcion4_activity_Reading_1_1);


            revisar = findViewById(R.id.button_activity_Reading_1);
            continuar = findViewById(R.id.button_activity_con_reading_1);


            opcA2.setVisibility(View.INVISIBLE);
            opcB2.setVisibility(View.INVISIBLE);
            opcC2.setVisibility(View.INVISIBLE);
            opcD2.setVisibility(View.INVISIBLE);

            numAletorio = comun.aleatorio(numerosPreuntas);

            if(b1.contains(numAletorio)) {
                if (curso.equals("Ingles")) {
                    oracion.setText("select the correct answer");
                } else if (curso.equals("Italiano")) {
                    oracion.setText("seleziona la risposta corretta");
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

                Log.i("DATAFROMSQL", "Curso" + curso);
                Log.i("DATAFROMSQL", "success" + (lessonint - 1));
                bringTheInfo(lessonint - 1, numAletorio);
                opciones();
            }else {

                Intent i = new Intent(Reading_1_Activity.this, Reading_1_Activity.class);
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
        }else {
            Intent i = new Intent(Reading_1_Activity.this, ResumenActividad.class);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("tipo",tipo);

            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);

        }


    }

    private void opciones() {
        opcA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcA.setVisibility(View.INVISIBLE);
                opcB.setVisibility(View.VISIBLE);
                opcD.setVisibility(View.VISIBLE);
                opcC.setVisibility(View.VISIBLE);


                opcC2.setVisibility(View.INVISIBLE);
                opcD2.setVisibility(View.INVISIBLE);
                opcB2.setVisibility(View.INVISIBLE);
                opcA2.setVisibility(View.VISIBLE);
                res = "A";

            }
        });

        opcA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcA.setVisibility(View.VISIBLE);
                opcA2.setVisibility(View.INVISIBLE);
                res = "NO";
            }
        });

        opcB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcB.setVisibility(View.INVISIBLE);
                opcA.setVisibility(View.VISIBLE);
                opcD.setVisibility(View.VISIBLE);
                opcC.setVisibility(View.VISIBLE);


                opcC2.setVisibility(View.INVISIBLE);
                opcA2.setVisibility(View.INVISIBLE);
                opcD2.setVisibility(View.INVISIBLE);
                opcB2.setVisibility(View.VISIBLE);
                res = "B";

            }
        });

        opcB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcB.setVisibility(View.VISIBLE);
                opcB2.setVisibility(View.INVISIBLE);
                res = "NO";
            }
        });

        opcC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                opcA.setVisibility(View.VISIBLE);
                opcB.setVisibility(View.VISIBLE);
                opcD.setVisibility(View.VISIBLE);
                opcC.setVisibility(View.INVISIBLE);


                opcC2.setVisibility(View.VISIBLE);

                opcA2.setVisibility(View.INVISIBLE);
                opcB2.setVisibility(View.INVISIBLE);
                opcD2.setVisibility(View.INVISIBLE);
                res = "C";

            }
        });

        opcC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcC.setVisibility(View.VISIBLE);
                opcC2.setVisibility(View.INVISIBLE);
                res = "NO";
            }
        });

        opcD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcA.setVisibility(View.VISIBLE);
                opcB.setVisibility(View.VISIBLE);
                opcC.setVisibility(View.VISIBLE);
                opcA2.setVisibility(View.INVISIBLE);
                opcB2.setVisibility(View.INVISIBLE);
                opcC2.setVisibility(View.INVISIBLE);
                opcD.setVisibility(View.INVISIBLE);
                opcD2.setVisibility(View.VISIBLE);
                res = "D";

            }
        });

        opcD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcD.setVisibility(View.VISIBLE);
                opcD2.setVisibility(View.INVISIBLE);
                res = "NO";
            }
        });

        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x ="";
                revisar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                switch (res){
                    case "A":
                        x = opcA2.getText().toString();
                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();
                            cali = cali + 100;
                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();
                            cali = cali + 0;
                        }
                        break;
                    case "B":
                        x = opcB2.getText().toString();
                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();
                            cali = cali + 100;
                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();
                            cali = cali + 0;
                        }
                        break;
                    case "C":
                        x = opcC2.getText().toString();
                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();
                            cali = cali + 100;
                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();
                            cali = cali + 0;
                        }
                        break;
                    case "D":
                        x = opcD2.getText().toString();
                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();
                            cali = cali + 100;
                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();
                            cali = cali + 0;
                        }
                        break;
                    case "NO":
                        x = opcA2.getText().toString();
                        Toast.makeText(Reading_1_Activity.this,"contesta",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b1N = b1.replaceAll(numAletorio,"");
                actHechas++;
                String num ="";
                num = comun.aleatorio(4);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Reading_1_Activity.this, Reading_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);
                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        i.putExtra("boceto1",b1N);
                        i.putExtra("boceto2",b2);
                        i.putExtra("boceto3",b3);
                        i.putExtra("boceto4",b4);
                        startActivity(i);
                        break;
                    case "1":
                        Intent intent = new Intent(Reading_1_Activity.this, Reading_Paragraph_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);
                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1N);
                        intent.putExtra("boceto2",b2);
                        intent.putExtra("boceto3",b3);
                        intent.putExtra("boceto4",b4);
                        startActivity(intent);
                        break;
                    case "2":
                        Intent intent1 = new Intent(Reading_1_Activity.this, Reading_paragraph_2_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);
                        intent1.putExtra("calificacion",String.valueOf(cali));
                        intent1.putExtra("actividad",String.valueOf(actHechas));
                        intent1.putExtra("boceto1",b1N);
                        intent1.putExtra("boceto2",b2);
                        intent1.putExtra("boceto3",b3);
                        intent1.putExtra("boceto4",b4);
                        startActivity(intent1);
                        break;
                    case "3":
                        Intent intent2 = new Intent(Reading_1_Activity.this, Reading_4_Activity.class);
                        intent2.putExtra("curso",curso);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);
                        intent2.putExtra("calificacion",String.valueOf(cali));
                        intent2.putExtra("actividad",String.valueOf(actHechas));
                        intent2.putExtra("boceto1",b1N);
                        intent2.putExtra("boceto2",b2);
                        intent2.putExtra("boceto3",b3);
                        intent2.putExtra("boceto4",b4);
                        startActivity(intent2);
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

                                String opcArec = object.getString("opcA" + h).trim();
                                String opcBrec = object.getString("opcB" + h).trim();
                                String opcCrec = object.getString("opcC" + h).trim();
                                String opcDrec = object.getString("opcD" + h).trim();
                                String Imagen = object.getString("urlImage" + h);
                                Log.i("DATAFROMSQL", "success" + Imagen);
                                resCheck = object.getString("respuestac" + h).trim();
                                Glide.with(Reading_1_Activity.this)
                                        .load(Imagen)
                                        .into(imagenVIew);
                                descriptionTextView.setText(pregunta);
                                opcA.setText(opcArec);
                                opcB.setText(opcBrec);
                                opcC.setText(opcCrec);
                                opcD.setText(opcDrec);
                                opcA2.setText(opcArec);
                                opcB2.setText(opcBrec);
                                opcC2.setText(opcCrec);
                                opcD2.setText(opcDrec);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());
                    // progressBar.setVisibility(View.GONE);
                    Toast.makeText(Reading_1_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  progressBar.setVisibility(View.GONE);
                        Toast.makeText(Reading_1_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("boceto",boceto);
                params.put("type","reading");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onBackPressed(){
        return;
    }
}