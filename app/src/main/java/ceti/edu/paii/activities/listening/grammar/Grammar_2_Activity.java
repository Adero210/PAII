package ceti.edu.paii.activities.listening.grammar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Grammar_2_Activity extends AppCompatActivity {

    private TextView titulo;
    private EditText oracionHolder;

    private TextView parrafo;
    private Button calificar;
    private Button continuar;


    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";

    private String boceto = "2";

    private String pregunta = "";
    private String respuestaFromBD = "";
    private Editable respuestaSelected;
    private MediaPlayer mediaPlayer,incorrect;

    private String curso;
    private String lesson;

    int actHechas, cali;
    private String calis, actHechasS;

    private  String numAletorio ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_2_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);
        if(actHechas<=8) {


            progressDialog = new ProgressDialog(Grammar_2_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            parrafo = findViewById(R.id.parrafo_grammar_2);
            titulo = findViewById(R.id.titulo_grammar_2);
            oracionHolder = findViewById(R.id.colocar_traduccion_grammar_2);


            calificar = findViewById(R.id.button_activity_grammar_2);
            continuar = findViewById(R.id.button_continuar_activity_grammar_2);

            Log.i("curso", curso);
            numAletorio = "1";
                if (curso.equals("English")) {
                    titulo.setText("Order the sentence");
                } else if (curso.equals("Italiano")) {
                    titulo.setText("Ordina la frase");
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

                Log.i("PLEASEFUNCIONA", lessonint-1 + numAletorio);
                bringTheInfo(lessonint - 1, numAletorio);
                opciones();

        }else {
            Intent i = new Intent(Grammar_2_Activity.this, ResumenActividad.class);
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
                        progressDialog.dismiss();
                            JSONObject object =  jsonArray.getJSONObject(0);
                                pregunta = object.getString("question").trim();

                                String[] partesOracion = pregunta.split("~");
                                for(int i = 0; i < partesOracion.length; i++) {
                                    respuestaFromBD = respuestaFromBD + (partesOracion[i] + " ");
                                }
                                respuestaFromBD = respuestaFromBD.trim();
                                int tamOra = partesOracion.length;
                                int pos, y = 0;
                                int nCartas = tamOra;
                                String[] r;
                                Stack<Integer> pCartas = new Stack<Integer>();

                                         r = new String[tamOra];
                                        // AleatoriSinRepeticion();
                                        for (int x = 0; x < nCartas; x++) {
                                            pos = (int) Math.floor(Math.random() * nCartas);
                                            while (pCartas.contains(pos)) {
                                                pos = (int) Math.floor(Math.random() * nCartas);
                                            }
                                            r[pos] = partesOracion[y];
                                            pCartas.push(pos);
                                            y++;

                                        }
                                        Log.i("Numeros", pCartas.toString());
                                         String p = "";
                                        for(int j = 0; j < tamOra; j++){
                                            p = r[j] + " / " + p;
                                        }

                                        parrafo.setText(p);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());
                    Toast.makeText(Grammar_2_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Grammar_2_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("numberOfQuestions",numAle);
                params.put("lectionId", String.valueOf(lessonint2));
                params.put("sketch",boceto);
                params.put("typeName","Gramatica");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void opciones() {


        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calificar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                respuestaSelected =  oracionHolder.getText();

                if(respuestaSelected.equals(respuestaFromBD)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    Toast.makeText(Grammar_2_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
                    cali = cali + 0;
                    Toast.makeText(Grammar_2_Activity.this,"Correct answer: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
                }
            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actHechas++;
                String num;
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Grammar_2_Activity.this, Grammar_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Grammar_2_Activity.this, Grammar_2_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Grammar_2_Activity.this, Grammar_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("calificacion",String.valueOf(cali));
                        intent1.putExtra("actividad",String.valueOf(actHechas));
                        startActivity(intent1);
                        break;
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        return;
    }
}
