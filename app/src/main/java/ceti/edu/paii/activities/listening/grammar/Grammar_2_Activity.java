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
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Grammar_2_Activity extends AppCompatActivity {

    private TextView titulo;
    private TextView oracion;
    private TextView oracionHolder;
    private Button opc1;
    private Button opc2;
    private Button opc3;
    private Button opc4;
    private Button opc5;
    private Button opc6;
    private Button opc7;
    private Button opc8;
    private Button opc9;
    private Button opc1Sel;
    private Button opc2Sel;
    private Button opc3Sel;
    private Button opc4Sel;
    private Button opc5Sel;
    private Button opc6Sel;
    private Button opc7Sel;
    private Button opc8Sel;
    private Button opc9Sel;
    private Button calificar;
    private Button continuar;

    private String partR1;
    private String partR2;
    private String partR3;
    private String partR4;
    private String partR5;
    private String partR6;
    private String partR7;
    private String partR8;
    private String partR9;



    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";

    private String boceto = "2";

    private String pregunta = "";
    private String respuestaFromBD = "";
    private String respuestaSelected ="";
    private MediaPlayer mediaPlayer,incorrect;

    private String curso;
    private String lesson;

    private int numerosPreuntas = 5;

    int actHechas, cali;
    private String b1,b2,b3, calis, actHechasS;

    private  String numAletorio ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_2_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        b1 = getIntent().getStringExtra("boceto1");
        b2 = getIntent().getStringExtra("boceto2");
        b3 = getIntent().getStringExtra("boceto3");
        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);
        if(actHechas<=8) {


            progressDialog = new ProgressDialog(Grammar_2_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            titulo = findViewById(R.id.titulo_grammar_2);
            oracion = findViewById(R.id.oracion_activity_grammar_2);
            oracionHolder = findViewById(R.id.colocar_traduccion_grammar_2);

            opc1 = findViewById(R.id.opcion1_activity_grammar_2);
            opc2 = findViewById(R.id.opcion2_activity_grammar_2);
            opc3 = findViewById(R.id.opcion3_activity_grammar_2);
            opc4 = findViewById(R.id.opcion4_activity_grammar_2);
            opc5 = findViewById(R.id.opcion5_activity_grammar_2);
            opc6 = findViewById(R.id.opcion6_activity_grammar_2);
            opc7 = findViewById(R.id.opcion7_activity_grammar_2);
            opc8 = findViewById(R.id.opcion8_activity_grammar_2);
            opc9 = findViewById(R.id.opcion9_activity_grammar_2);

            opc1Sel = findViewById(R.id.opcion1_activity_selected_grammar_2);
            opc2Sel = findViewById(R.id.opcion2_activity_selected_grammar_2);
            opc3Sel = findViewById(R.id.opcion3_activity_selected_grammar_2);
            opc4Sel = findViewById(R.id.opcion4_activity_selected_grammar_2);
            opc5Sel = findViewById(R.id.opcion5_activity_selected_grammar_2);
            opc6Sel = findViewById(R.id.opcion6_activity_selected_grammar_2);
            opc7Sel = findViewById(R.id.opcion7_activity_selected_grammar_2);
            opc8Sel = findViewById(R.id.opcion8_activity_selected_grammar_2);
            opc9Sel = findViewById(R.id.opcion9_activity_selected_grammar_2);

            calificar = findViewById(R.id.button_activity_grammar_2);
            continuar = findViewById(R.id.button_continuar_activity_grammar_2);


            Log.i("curso", curso);

            numAletorio = comun.aleatorio(numerosPreuntas);
            if(b2.contains(numAletorio)) {

                if (curso.equals("Ingles")) {
                    titulo.setText("order the sentence");
                } else if (curso.equals("Italiano")) {
                    titulo.setText("ordina la frase");
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

                bringTheInfo(lessonint - 1, numAletorio);

                opciones();
            } else {

                Intent i = new Intent(Grammar_2_Activity.this, Grammar_2_Activity.class);
                i.putExtra("curso",curso);
                i.putExtra("lesson",lesson);
                i.putExtra("calificacion",String.valueOf(cali));
                i.putExtra("actividad",String.valueOf(actHechas));
                i.putExtra("boceto1",b1);
                i.putExtra("boceto2",b2);
                i.putExtra("boceto3",b3);
                startActivity(i);
            }


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

                                oracion.setText(pregunta);

                                String[] partesOracion = respuestaFromBD.split(" ");
                                int tamOra = partesOracion.length;
                                int pos, y = 0;
                                int nCartas = tamOra;
                                String[] r;
                                Stack<Integer> pCartas = new Stack<Integer>();
                                switch (tamOra) {
                                    case 1:
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
                                         partR1 = r[0];

                                        opc1.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                         opc1Sel.setText(partR1);
                                        break;
                                    case 2:
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
                                         partR1 = r[0];
                                         partR2 = r[1];

                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        break;
                                    case 3:
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
                                         partR1 = r[0];
                                         partR2 = r[1];
                                         partR3 = r[2];
                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);
                                        opc3.setVisibility(View.VISIBLE);
                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        opc3.setText(partR3);
                                        opc3Sel.setText(partR3);
                                        break;
                                    case 4:
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
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];

                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);
                                        opc3.setVisibility(View.VISIBLE);
                                        opc4.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        opc3.setText(partR3);
                                        opc3Sel.setText(partR3);
                                        opc4.setText(partR4);
                                        opc4Sel.setText(partR4);
                                        break;

                                    case 5:
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
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        partR5 = r[4];

                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);
                                        opc3.setVisibility(View.VISIBLE);
                                        opc4.setVisibility(View.VISIBLE);
                                        opc5.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        opc3.setText(partR3);
                                        opc3Sel.setText(partR3);
                                        opc4.setText(partR4);
                                        opc4Sel.setText(partR4);
                                        opc5.setText(partR5);
                                        opc5Sel.setText(partR5);
                                        break;

                                    case 6:
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
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        partR5 = r[4];
                                        partR6 = r[5];

                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);
                                        opc3.setVisibility(View.VISIBLE);
                                        opc4.setVisibility(View.VISIBLE);
                                        opc5.setVisibility(View.VISIBLE);
                                        opc6.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        opc3.setText(partR3);
                                        opc3Sel.setText(partR3);
                                        opc4.setText(partR4);
                                        opc4Sel.setText(partR4);
                                        opc5.setText(partR5);
                                        opc5Sel.setText(partR5);
                                        opc6.setText(partR6);
                                        opc6Sel.setText(partR6);
                                        break;
                                    case 7:
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
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        partR5 = r[4];
                                        partR6 = r[5];
                                        partR7 = r[6];


                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);
                                        opc3.setVisibility(View.VISIBLE);
                                        opc4.setVisibility(View.VISIBLE);
                                        opc5.setVisibility(View.VISIBLE);
                                        opc6.setVisibility(View.VISIBLE);
                                        opc7.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        opc3.setText(partR3);
                                        opc3Sel.setText(partR3);
                                        opc4.setText(partR4);
                                        opc4Sel.setText(partR4);
                                        opc5.setText(partR5);
                                        opc5Sel.setText(partR5);
                                        opc6.setText(partR6);
                                        opc6Sel.setText(partR6);
                                        opc7.setText(partR7);
                                        opc7Sel.setText(partR7);
                                        break;
                                    case 8:
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
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        partR5 = r[4];
                                        partR6 = r[5];
                                        partR7 = r[6];
                                        partR8 = r[7];

                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);
                                        opc3.setVisibility(View.VISIBLE);
                                        opc4.setVisibility(View.VISIBLE);
                                        opc5.setVisibility(View.VISIBLE);
                                        opc6.setVisibility(View.VISIBLE);
                                        opc7.setVisibility(View.VISIBLE);
                                        opc8.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        opc3.setText(partR3);
                                        opc3Sel.setText(partR3);
                                        opc4.setText(partR4);
                                        opc4Sel.setText(partR4);
                                        opc5.setText(partR5);
                                        opc5Sel.setText(partR5);
                                        opc6.setText(partR6);
                                        opc6Sel.setText(partR6);
                                        opc7.setText(partR7);
                                        opc7Sel.setText(partR7);
                                        opc8.setText(partR8);
                                        opc8Sel.setText(partR8);
                                        break;
                                    case 9:
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
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        partR5 = r[4];
                                        partR6 = r[5];
                                        partR7 = r[6];
                                        partR8 = r[7];
                                        partR9 = r[7];


                                        opc1.setVisibility(View.VISIBLE);
                                        opc2.setVisibility(View.VISIBLE);
                                        opc3.setVisibility(View.VISIBLE);
                                        opc4.setVisibility(View.VISIBLE);
                                        opc5.setVisibility(View.VISIBLE);
                                        opc6.setVisibility(View.VISIBLE);
                                        opc7.setVisibility(View.VISIBLE);
                                        opc8.setVisibility(View.VISIBLE);
                                        opc9.setVisibility(View.VISIBLE);

                                        opc1.setText(partR1);
                                        opc1Sel.setText(partR1);
                                        opc2.setText(partR2);
                                        opc2Sel.setText(partR2);
                                        opc3.setText(partR3);
                                        opc3Sel.setText(partR3);
                                        opc4.setText(partR4);
                                        opc4Sel.setText(partR4);
                                        opc5.setText(partR5);
                                        opc5Sel.setText(partR5);
                                        opc6.setText(partR6);
                                        opc6Sel.setText(partR6);
                                        opc7.setText(partR7);
                                        opc7Sel.setText(partR7);
                                        opc8.setText(partR8);
                                        opc8Sel.setText(partR8);
                                        opc9.setText(partR9);
                                        opc9Sel.setText(partR9);
                                        break;
                                }

                            }

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Grammar_2_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Grammar_2_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


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
    private void opciones() {

        opc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc1.setVisibility(View.INVISIBLE);
                opc1Sel.setVisibility(View.VISIBLE);

                String word = (String) opc1.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });
        opc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc2.setVisibility(View.INVISIBLE);
                opc2Sel.setVisibility(View.VISIBLE);

                String word = (String) opc2.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________", " " + word);
                oracionHolder.setText(res);
            }
        });
        opc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc3.setVisibility(View.INVISIBLE);
                opc3Sel.setVisibility(View.VISIBLE);

                String word = (String) opc3.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });
        opc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc4.setVisibility(View.INVISIBLE);
                opc4Sel.setVisibility(View.VISIBLE);

                String word = (String) opc4.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });
        opc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc5.setVisibility(View.INVISIBLE);
                opc5Sel.setVisibility(View.VISIBLE);

                String word = (String) opc5.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });
        opc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc6.setVisibility(View.INVISIBLE);
                opc6Sel.setVisibility(View.VISIBLE);

                String word = (String) opc6.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });
        opc7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc7.setVisibility(View.INVISIBLE);
                opc7Sel.setVisibility(View.VISIBLE);

                String word = (String) opc7.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });
        opc8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc8.setVisibility(View.INVISIBLE);
                opc8Sel.setVisibility(View.VISIBLE);

                String word = (String) opc8.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });
        opc9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc9.setVisibility(View.INVISIBLE);
                opc9Sel.setVisibility(View.VISIBLE);

                String word = (String) opc9.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst("__________"," " + word);
                oracionHolder.setText(res);
            }
        });

        opc1Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc1.setVisibility(View.VISIBLE);
                opc1Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc1Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc2Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc2.setVisibility(View.VISIBLE);
                opc2Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc2Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc3Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc3.setVisibility(View.VISIBLE);
                opc3Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc3Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc4Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc4.setVisibility(View.VISIBLE);
                opc4Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc4Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc5Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc5.setVisibility(View.VISIBLE);
                opc5Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc5Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc6Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc6.setVisibility(View.VISIBLE);
                opc6Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc6Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc7Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc7.setVisibility(View.VISIBLE);
                opc7Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc7Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc8Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc8.setVisibility(View.VISIBLE);
                opc8Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc8Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });
        opc9Sel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opc9.setVisibility(View.VISIBLE);
                opc9Sel.setVisibility(View.INVISIBLE);

                String word = (String) opc9Sel.getText();
                String ora = (String) oracionHolder.getText();
                String res = ora.replaceFirst(word, "");
                oracionHolder.setText(res);
            }
        });

        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calificar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                respuestaSelected = (String) oracionHolder.getText();

                String res = respuestaSelected.replaceAll("_","").trim();

                Log.i("respuestas",res + " - " + respuestaFromBD);

                if(res.equals(respuestaFromBD)){
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
                String b2N = b2.replaceAll(numAletorio,"");
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
                        i.putExtra("boceto1",b1);
                        i.putExtra("boceto2",b2N);
                        i.putExtra("boceto3",b3);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Grammar_2_Activity.this, Grammar_2_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1);
                        intent.putExtra("boceto2",b2N);
                        intent.putExtra("boceto3",b3);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Grammar_2_Activity.this, Grammar_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("calificacion",String.valueOf(cali));
                        intent1.putExtra("actividad",String.valueOf(actHechas));
                        intent1.putExtra("boceto1",b1);
                        intent1.putExtra("boceto2",b2N);
                        intent1.putExtra("boceto3",b3);
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
