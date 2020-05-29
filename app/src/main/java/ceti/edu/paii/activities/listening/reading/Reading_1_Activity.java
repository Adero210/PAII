package ceti.edu.paii.activities.listening.reading;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
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

    int actHechas, cali;
    private String calis, actHechasS;

    private  String numAletorio;


    private String bto;
    protected String cWord;
    private String wWord;
    private ProgressDialog calificacion;

    private StorageReference mImageStorage;

    private String boceto = "2";

    private  String res = "NO", resCheck ="";

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_1_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);

        if(actHechas<=8) {


            progressDialog = new ProgressDialog(Reading_1_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);


            descriptionTextView = findViewById(R.id.description_text_activity_Reading_1);
            oracion = findViewById(R.id.oracion_activity_reading_1);
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

            numAletorio = "1";
            if (curso.equals("English")) {
                oracion.setText("select the correct answer");
                bto = "Continue";
                cWord = "Correct!";
                wWord = "Wrong: ";
            } else if (curso.equals("Italiano")) {
                oracion.setText("seleziona la risposta corretta");
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


            calificacion =  new ProgressDialog(Reading_1_Activity.this);
            calificacion.setButton(AlertDialog.BUTTON_NEGATIVE, bto, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    progressDialog.dismiss();
                    actHechas++;
                    String num;
                    num = comun.aleatorio(4);
                    Log.i("numeroRamdon",num);
                    switch (num){
                        case "0":
                            Intent i = new Intent(Reading_1_Activity.this, Reading_1_Activity.class);
                            i.putExtra("curso",curso);
                            i.putExtra("lesson",lesson);
                            i.putExtra("calificacion",String.valueOf(cali));
                            i.putExtra("actividad",String.valueOf(actHechas));
                            startActivity(i);
                            break;
                        case "1":
                            Intent intent = new Intent(Reading_1_Activity.this, Reading_1_Activity.class);
                            intent.putExtra("curso",curso);
                            intent.putExtra("lesson",lesson);
                            intent.putExtra("calificacion",String.valueOf(cali));
                            intent.putExtra("actividad",String.valueOf(actHechas));
                            startActivity(intent);
                            break;
                        case "2":
                            Intent intent1 = new Intent(Reading_1_Activity.this, Reading_4_Activity.class);
                            intent1.putExtra("curso",curso);
                            intent1.putExtra("lesson",lesson);
                            intent1.putExtra("calificacion",String.valueOf(cali));
                            intent1.putExtra("actividad",String.valueOf(actHechas));
                            startActivity(intent1);
                            break;
                        case "3":
                            Intent intent2 = new Intent(Reading_1_Activity.this, Reading_4_Activity.class);
                            intent2.putExtra("curso",curso);
                            intent2.putExtra("lesson",lesson);
                            intent2.putExtra("calificacion",String.valueOf(cali));
                            intent2.putExtra("actividad",String.valueOf(actHechas));
                            startActivity(intent2);
                            break;
                    }
                }
            });
            calificacion.setCancelable(false);

            Log.i("DATAFROMSQL", "Curso" + curso);
            Log.i("DATAFROMSQL", "success" + (lessonint - 1));
            bringTheInfo(lessonint - 1, numAletorio);
            opciones();

        }else {
            Intent i = new Intent(Reading_1_Activity.this, ResumenActividad.class);
            String tipo = "Lectura";
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
                String x;
                revisar.setVisibility(View.INVISIBLE);
                switch (res){
                    case "A":
                        x = opcA2.getText().toString();
                        if(resCheck.equals(x)){
                            calificacion.setMessage(cWord);
                            calificacion.setProgressStyle(5);
                            mediaPlayer.start();
                            cali = cali + 100;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        else{
                            calificacion.setMessage(wWord + resCheck);
                            calificacion.setProgressStyle(2);
                            incorrect.start();
                            cali = cali + 0;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        break;
                    case "B":
                        x = opcB2.getText().toString();
                        if(resCheck.equals(x)){
                            calificacion.setMessage(cWord);
                            calificacion.setProgressStyle(5);
                            mediaPlayer.start();
                            cali = cali + 100;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        else{
                            calificacion.setMessage(wWord + resCheck);
                            calificacion.setProgressStyle(2);
                            incorrect.start();
                            cali = cali + 0;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        break;
                    case "C":
                        x = opcC2.getText().toString();
                        if(resCheck.equals(x)){
                            calificacion.setMessage(cWord);
                            calificacion.setProgressStyle(5);
                            mediaPlayer.start();
                            cali = cali + 100;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        else{
                            calificacion.setMessage(wWord + resCheck);
                            calificacion.setProgressStyle(2);
                            incorrect.start();
                            cali = cali + 0;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        break;
                    case "D":
                        x = opcD2.getText().toString();
                        if(resCheck.equals(x)){
                            calificacion.setMessage(cWord);
                            calificacion.setProgressStyle(5);
                            mediaPlayer.start();
                            cali = cali + 100;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        else{
                            calificacion.setMessage(wWord + resCheck);
                            calificacion.setProgressStyle(2);
                            incorrect.start();
                            cali = cali + 0;
                            calis = String.valueOf(cali);
                            calificacion.show();
                        }
                        break;
                    case "NO":
                        x = opcA2.getText().toString();
                        calificacion.setMessage(wWord);
                        calificacion.setProgressStyle(2);
                        incorrect.start();
                        cali = cali + 0;
                        calis = String.valueOf(cali);
                        calificacion.show();
                        break;
                    default:
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
                        progressDialog.dismiss();

                        JSONObject object =  jsonArray.getJSONObject(0);
                        JSONArray options = object.getJSONArray("options");

                        comun.Optionss opciones = comun.getOptions(options);

                        resCheck = object.getString("correct").trim();

                        String pregunta = object.getString("question").trim();

                        JSONObject jsonObject1 = object.getJSONObject("images");
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("image");


                        String opcionA = opciones.opcA;
                        String opcionB = opciones.opcB;
                        String opcionC = opciones.opcC;
                        String opcionD = opciones.opcD;



                        String Imagen = jsonObject2.getString("imageRoute");
                        Log.i("DATAFROMSQL", "success" + Imagen);
                        Glide.with(Reading_1_Activity.this)
                                .load(Imagen)
                                .into(imagenVIew);
                        descriptionTextView.setText(pregunta);
                        opcA.setText(opcionA);
                        opcB.setText(opcionB);
                        opcC.setText(opcionC);
                        opcD.setText(opcionD);
                        opcA2.setText(opcionA);
                        opcB2.setText(opcionB);
                        opcC2.setText(opcionC);
                        opcD2.setText(opcionD);

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
                params.put("numberOfQuestions",numAle);
                params.put("lectionId", String.valueOf(lessonint2));
                params.put("sketch",boceto);
                params.put("typeName","Lectura");
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