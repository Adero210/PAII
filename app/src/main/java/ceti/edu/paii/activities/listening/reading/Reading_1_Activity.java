package ceti.edu.paii.activities.listening.reading;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.actividades;

public class Reading_1_Activity extends AppCompatActivity {

    private MediaPlayer mediaPlayer,incorrect;
    private ImageView imagenVIew;
    private TextView descriptionTextView;
    private Button opcA,opcA2, opcB,opcB2,
            opcC, opcC2,opcD,opcD2, nextCal;

    private StorageReference mImageStorage;

    private String boceto = "2";


    private  String res = "NO", resCheck ="";


    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_1_);

        progressDialog =  new ProgressDialog(Reading_1_Activity.this);

        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);

        mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
        incorrect = MediaPlayer.create(this,R.raw.wrong);


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


        nextCal = findViewById(R.id.button_activity_Reading_1);

        opcA2.setVisibility(View.INVISIBLE);
        opcB2.setVisibility(View.INVISIBLE);
        opcC2.setVisibility(View.INVISIBLE);
        opcD2.setVisibility(View.INVISIBLE);



        String curso = getIntent().getStringExtra("curso");
        String lesson = getIntent().getStringExtra("lesson");

        String numAletorio = aleatorio();

        int lessonint = Integer.parseInt(lesson);

        if(curso.equals("Curso Italiano")){
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

        Log.i("DATAFROMSQL", "Curso" + curso);
        Log.i("DATAFROMSQL", "success" + (lessonint - 1));

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

        nextCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String x ="";

                switch (res){
                    case "A":
                         x = opcA2.getText().toString();

                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();

                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();

                        }
                        break;


                    case "B":
                         x = opcB2.getText().toString();

                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();
                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();

                        }
                        break;

                    case "C":
                         x = opcC2.getText().toString();

                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();

                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();

                        }
                        break;

                    case "D":
                         x = opcD2.getText().toString();

                        if(resCheck.equals(x)){
                            Toast.makeText(Reading_1_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                            mediaPlayer.start();

                        }
                        else{
                            Toast.makeText(Reading_1_Activity.this,"INCORRECT " +
                                    "\n Correct: " + resCheck,Toast.LENGTH_SHORT).show();
                            incorrect.start();
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

    private String aleatorio(){
        // AleatoriSinRepeticion();
        String num = "";
        int pos;
        int nCartas = 3;
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
}
