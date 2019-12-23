package ceti.edu.paii.activities.listening.vocabulary;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.vocabularyThree;

public class Vocabulary_3_Activity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";



    private String curso;
    private String lesson;

    private String boceto = "3";

    private String pregunta = "";
    private String respuestaFromBD = "";
    private String respuestaSelected ="";
    private MediaPlayer mediaPlayer,incorrect;


    private int cartSel = 0;
    private int numPreguntas = 5;

    private TextView oracion;

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_3_);
        progressDialog = new ProgressDialog(Vocabulary_3_Activity.this);

        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);

        mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
        incorrect = MediaPlayer.create(this, R.raw.wrong);


        curso = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");

        btn0 = findViewById(R.id.opcion1_activity_vocabulary_3);
        btn1 = findViewById(R.id.opcion2_activity_vocabulary_3);
        btn2 = findViewById(R.id.opcion3_activity_vocabulary_3);
        btn3 = findViewById(R.id.opcion4_activity_vocabulary_3);
        btn4 = findViewById(R.id.opcion5_activity_vocabulary_3);
        btn5 = findViewById(R.id.opcion6_activity_vocabulary_3);
        btn6 = findViewById(R.id.opcion7_activity_vocabulary_3);
        btn7 = findViewById(R.id.opcion8_activity_vocabulary_3);
        btn8 = findViewById(R.id.opcion9_activity_vocabulary_3);
        btn9 = findViewById(R.id.opcion0_activity_vocabulary_3);


        String numAletorio = comun.aleatorio(numPreguntas);

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
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    check();
                }else {
                    btn1.setTextColor(Color.parseColor("#008000"));
                    cartSel++;
                }
            }
        });
    }

    private void check() {
        cartSel--;
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

                                Log.i("vocabulary2","pregunta: " + pregunta);
                                Log.i("vocabulary2","respuesta: " + respuestaFromBD);


                            }
                            String preguntas[] = pregunta.split(",");

                            String respuestas[] = respuestaFromBD.split(",");
                            vocabularyThree opcionA = new vocabularyThree(preguntas[0],respuestas[0]);
                            vocabularyThree opcionB = new vocabularyThree(preguntas[1],respuestas[1]);
                            vocabularyThree opcionC = new vocabularyThree(preguntas[2],respuestas[2]);
                            vocabularyThree opcionD = new vocabularyThree(preguntas[3],respuestas[3]);
                            vocabularyThree opcionE = new vocabularyThree(preguntas[4],respuestas[4]);

                            String kk = pregunta+","+respuestaFromBD;
                            String words[] = kk.split(",");

                            Log.i("vocabulary2","kk: " + kk);


                            String[] r = new String[10];
                            // AleatoriSinRepeticion();
                            int pos, y = 0;
                            int nCartas = 10;
                            Stack<Integer> pCartas = new Stack<Integer>();
                            for (int x = 0; x < nCartas; x++) {
                                pos = (int) Math.floor(Math.random() * nCartas);
                                while (pCartas.contains(pos)) {
                                    pos = (int) Math.floor(Math.random() * nCartas);
                                }
                                r[pos] = words[y];
                                pCartas.push(pos);
                                y = y + 1;
                            }

                            btn0.setText(r[9]);
                            btn1.setText(r[0]);
                            btn2.setText(r[1]);
                            btn3.setText(r[2]);
                            btn4.setText(r[3]);
                            btn5.setText(r[4]);
                            btn6.setText(r[5]);
                            btn7.setText(r[6]);
                            btn8.setText(r[7]);
                            btn9.setText(r[8]);

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Vocabulary_3_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Vocabulary_3_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


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


    /*@Override
    public void onBackPressed(){
        return;
    }*/
}
