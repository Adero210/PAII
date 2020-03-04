package ceti.edu.paii.activities.listening.grammar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
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

import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Grammar_1_Activity extends AppCompatActivity {

    private TextView titulo;
    private TextView oracion;
    private Button opc1;
    private Button opc2;
    private Button opc3;
    private Button opc4;
    private Button opc4Sel;
    private Button opc1Sel;
    private Button opc2Sel;
    private Button opc3Sel;
    private Button calificar;
    private Button continuar;

    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";

    private String boceto = "3";

    private String pregunta = "";
    private String respuestaFromBD = "";
    private String respuestaSelected = "";
    private MediaPlayer mediaPlayer,incorrect;

    private String curso;
    private String lesson;

    int actHechas, cali;
    private String calis, actHechasS;

    private  String numAletorio = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_1_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);

        if(actHechas <= 8) {
            progressDialog = new ProgressDialog(Grammar_1_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            titulo = findViewById(R.id.titulo_grammar_1);
            oracion = findViewById(R.id.oracion_activity_grammar_1);
            opc1 = findViewById(R.id.opcion1_activity_grammar_1);
            opc2 = findViewById(R.id.opcion2_activity_grammar_1);
            opc3 = findViewById(R.id.opcion3_activity_grammar_1);
            opc1Sel = findViewById(R.id.opcion1_activity_selected_grammar_1);
            opc2Sel = findViewById(R.id.opcion2_activity_selected_grammar_1);
            opc3Sel = findViewById(R.id.opcion3_activity_selected_grammar_1);
            calificar = findViewById(R.id.button_activity_grammar_1);
            continuar = findViewById(R.id.button_continuar_activity_grammar_1);
            opc4 = findViewById(R.id.opcion4_activity_grammar_1);
            opc4Sel = findViewById(R.id.opcion4_activity_selected_grammar_1);

            Log.i("curso", curso);

                if (curso.equals("English")) {
                    titulo.setText("Choose the correct translation");
                } else if (curso.equals("Italiano")) {
                    titulo.setText("Scegli la traduzione corretta");
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
            Intent i = new Intent(Grammar_1_Activity.this, ResumenActividad.class);
            String tipo = "Gramatica";
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
                    Log.i("ahhhha","entre al try");

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {
                        Log.i("ahhhha","success");
                        progressDialog.dismiss();
                        JSONObject object =  jsonArray.getJSONObject(0);
                        JSONArray options = object.getJSONArray("options");
                        comun.Optionss opciones = comun.getOptions(options);

                        pregunta = object.getString("question" ).trim();
                        respuestaFromBD = object.getString("correct");
                        String opcionA = opciones.opcA;
                        String opcionB = opciones.opcB;
                        String opcionC = opciones.opcC;
                        String opcionD = opciones.opcD;
                        oracion.setText(pregunta);
                        opc1.setText(opcionA);
                        opc2.setText(opcionB);
                        opc3.setText(opcionC);
                        opc1Sel.setText(opcionA);
                        opc2Sel.setText(opcionB);
                        opc3Sel.setText(opcionC);
                        opc4.setText(opcionD);
                        opc4Sel.setText(opcionD);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());
                    // progressBar.setVisibility(View.GONE);
                    Toast.makeText(Grammar_1_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Grammar_1_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();
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
        opc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc1.setVisibility(View.INVISIBLE);
                opc2Sel.setVisibility(View.INVISIBLE);
                opc3Sel.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.INVISIBLE);
                opc4.setVisibility(View.VISIBLE);
                opc3.setVisibility(View.VISIBLE);
                opc2.setVisibility(View.VISIBLE);
                opc1Sel.setVisibility(View.VISIBLE);
                respuestaSelected = (String) opc1Sel.getText();
            }
        });

        opc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc2.setVisibility(View.INVISIBLE);
                opc1Sel.setVisibility(View.INVISIBLE);
                opc3Sel.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.INVISIBLE);
                opc4.setVisibility(View.VISIBLE);
                opc1.setVisibility(View.VISIBLE);
                opc3.setVisibility(View.VISIBLE);
                opc2Sel.setVisibility(View.VISIBLE);
                respuestaSelected = (String) opc2Sel.getText();
            }
        });

        opc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc3.setVisibility(View.INVISIBLE);
                opc2Sel.setVisibility(View.INVISIBLE);
                opc1Sel.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.INVISIBLE);
                opc4.setVisibility(View.VISIBLE);
                opc1.setVisibility(View.VISIBLE);
                opc2.setVisibility(View.VISIBLE);
                opc3Sel.setVisibility(View.VISIBLE);
                respuestaSelected = (String) opc3Sel.getText();
            }
        });

        opc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc4.setVisibility(View.INVISIBLE);
                opc2Sel.setVisibility(View.INVISIBLE);
                opc1Sel.setVisibility(View.INVISIBLE);
                opc3Sel.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.VISIBLE);
                opc3.setVisibility(View.VISIBLE);
                opc1.setVisibility(View.VISIBLE);
                opc2.setVisibility(View.VISIBLE);
            }
        });

        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calificar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                if(respuestaSelected.equals(respuestaFromBD)){
                    mediaPlayer.start();
                    cali = cali + 100;

                    Toast.makeText(Grammar_1_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
                    cali = cali + 0;

                    Toast.makeText(Grammar_1_Activity.this,"Correct answer: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
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
                        Intent i = new Intent(Grammar_1_Activity.this, Grammar_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Grammar_1_Activity.this, Grammar_2_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Grammar_1_Activity.this, Grammar_3_Activity.class);
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
