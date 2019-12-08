package ceti.edu.paii.activities.listening.grammar;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.writing.Writing_1_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_2_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_3_Activity;
import ceti.edu.paii.comun.comun;

public class Grammar_3_Activity extends AppCompatActivity {

    private TextView titulo;
    private TextView oracion;
    private Button opc1;
    private Button opc2;
    private Button opc3;
    private Button opc4;
    private Button calificar;
    private Button continuar;



    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";

    private String boceto = "3";

    private String pregunta = "";
    private String respuestaFromBD = "";
    private String respuestaSelected ="";
    private MediaPlayer mediaPlayer,incorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_3_);

        progressDialog =  new ProgressDialog(Grammar_3_Activity.this);

        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);

        mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
        incorrect = MediaPlayer.create(this,R.raw.wrong);

        titulo = findViewById(R.id.titulo_grammar_3);
        oracion = findViewById(R.id.oracion_activity_grammar_3);
        opc1 = findViewById(R.id.opcion1_activity_grammar_3);
        opc2 = findViewById(R.id.opcion2_activity_grammar_3);
        opc3 = findViewById(R.id.opcion3_activity_grammar_3);
        opc4 = findViewById(R.id.opcion4_activity_grammar_3);
        calificar = findViewById(R.id.button_activity_grammar_3);
        continuar = findViewById(R.id.button_continuar_activity_grammar_3);

        String curso = getIntent().getStringExtra("curso");
        String lesson = getIntent().getStringExtra("lesson");

        Log.i("curso",curso);
        String numAletorio = aleatorio();

        if(curso.equals("Ingles")){
            titulo.setText("find the correct verb");
        }else if(curso.equals("Italiano")){
            titulo.setText("trova il verbo corretto");
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

        opciones();
    }

    private void opciones() {
        opc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opc1.getText();
                String ora = pregunta.replace("___",word);
                oracion.setText(ora);
                respuestaSelected = word;
            }
        });
        opc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opc2.getText();
                String ora = pregunta.replace("___",word);
                oracion.setText(ora);
                respuestaSelected = word;
            }
        });
        opc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opc3.getText();
                String ora = pregunta.replace("___",word);
                oracion.setText(ora);
                respuestaSelected = word;
            }
        });
        opc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word  = (String) opc4.getText();
                String ora = pregunta.replace("___",word);
                oracion.setText(ora);
                respuestaSelected = word;
            }
        });

        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calificar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                if(respuestaSelected.equals(respuestaFromBD)){
                    mediaPlayer.start();
                    Toast.makeText(Grammar_3_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
                    Toast.makeText(Grammar_3_Activity.this,"Correct answer: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
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

                                pregunta = object.getString("pregunta" + h).trim();
                                respuestaFromBD = object.getString("respuestac" + h);
                                String opcionA = object.getString("opcA" +h);
                                String opcionB = object.getString("opcB" +h);
                                String opcionC = object.getString("opcC" +h);
                                String opcionD = object.getString("opcD" +h);

                                oracion.setText(pregunta);

                                opc1.setText(opcionA);
                                opc2.setText(opcionB);
                                opc3.setText(opcionC);
                                opc4.setText(opcionD);


                            }

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Grammar_3_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Grammar_3_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("boceto",boceto);
                params.put("type","grammar");


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
