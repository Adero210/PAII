package ceti.edu.paii.activities.listening.grammar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

    private String id0;
    private String id1;
    private String id2;
    private String id3;
    private String id4;
    private String id5;
    private String id6;
    private String id7;
    private String id8;

    private String bto;
    protected String cWord;
    private String wWord;
    private ProgressDialog calificacion;

    String[] cadid = new String[30];


    private  String numAletorio ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_2_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        id0 = getIntent().getStringExtra("id0");
        id1 = getIntent().getStringExtra("id1");
        id2 = getIntent().getStringExtra("id2");
        id3 = getIntent().getStringExtra("id3");
        id4 = getIntent().getStringExtra("id4");
        id5 = getIntent().getStringExtra("id5");
        id6 = getIntent().getStringExtra("id6");
        id7 = getIntent().getStringExtra("id7");
        id8 = getIntent().getStringExtra("id8");

        cali = Integer.parseInt(calis);
        actHechas = Integer.parseInt(actHechasS);

        cadid[0] = id0;
        cadid[1] = id1;
        cadid[2] = id2;
        cadid[3] = id3;
        cadid[4] = id4;
        cadid[5] = id5;
        cadid[6] = id6;
        cadid[7] = id7;
        cadid[8] = id8;
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



            numAletorio = "1";
                if (curso.equals("English")) {
                    titulo.setText("Order the sentence");
                    bto = "Continue";
                    cWord = "Correct!";
                    wWord = "Wrong: ";
                } else if (curso.equals("Italiano")) {
                    titulo.setText("Ordina la frase");
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

            calificacion =  new ProgressDialog(Grammar_2_Activity.this);
            calificacion.setButton(AlertDialog.BUTTON_NEGATIVE, bto, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    progressDialog.dismiss();
                    actHechas++;
                    actHechasS = String.valueOf(actHechas);
                    Log.i("aaaa",cadid[0]);
                    String num;
                    num = comun.aleatorio(3);
                    Log.i("numeroRamdon",num);
                    switch (num){
                        case "0":
                            Intent i = new Intent(Grammar_2_Activity.this, Grammar_1_Activity.class);
                            i.putExtra("curso",curso);
                            i.putExtra("lesson",lesson);
                            i.putExtra("calificacion",calis);
                            i.putExtra("actividad",actHechasS);
                            i.putExtra("id0",cadid[0]);
                            i.putExtra("id1",cadid[1]);
                            i.putExtra("id2",cadid[2]);
                            i.putExtra("id3",cadid[3]);
                            i.putExtra("id4",cadid[4]);
                            i.putExtra("id5",cadid[5]);
                            i.putExtra("id6",cadid[6]);
                            i.putExtra("id7",cadid[7]);
                            i.putExtra("id8", cadid[8]);
                            startActivity(i);
                            break;

                        case "1":
                            Intent intent = new Intent(Grammar_2_Activity.this, Grammar_2_Activity.class);
                            intent.putExtra("curso",curso);
                            intent.putExtra("lesson",lesson);
                            intent.putExtra("calificacion",calis);
                            intent.putExtra("actividad",actHechasS);
                            intent.putExtra("id0",cadid[0]);
                            intent.putExtra("id1",cadid[1]);
                            intent.putExtra("id2",cadid[2]);
                            intent.putExtra("id3",cadid[3]);
                            intent.putExtra("id4",cadid[4]);
                            intent.putExtra("id5",cadid[5]);
                            intent.putExtra("id6",cadid[6]);
                            intent.putExtra("id7",cadid[7]);
                            intent.putExtra("id8", cadid[8]);
                            startActivity(intent);
                            break;

                        case "2":
                            Intent intent2 = new Intent(Grammar_2_Activity.this, Grammar_3_Activity.class);
                            intent2.putExtra("curso",curso);
                            intent2.putExtra("lesson",lesson);
                            intent2.putExtra("calificacion",calis);
                            intent2.putExtra("actividad",actHechasS);
                            intent2.putExtra("id0",cadid[0]);
                            intent2.putExtra("id1",cadid[1]);
                            intent2.putExtra("id2",cadid[2]);
                            intent2.putExtra("id3",cadid[3]);
                            intent2.putExtra("id4",cadid[4]);
                            intent2.putExtra("id5",cadid[5]);
                            intent2.putExtra("id6",cadid[6]);
                            intent2.putExtra("id7",cadid[7]);
                            intent2.putExtra("id8", cadid[8]);
                            startActivity(intent2);
                            break;
                    }
                }
            });
            calificacion.setCancelable(false);

                bringTheInfo(lessonint - 1, numAletorio);
                opciones();

        }else {
            Intent i = new Intent(Grammar_2_Activity.this, ResumenActividad.class);
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

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {
                            JSONObject object =  jsonArray.getJSONObject(0);
                                pregunta = object.getString("question").trim();

                                String[] partesOracion = pregunta.split("~");
                                for(int i = 0; i < partesOracion.length; i++) {
                                    respuestaFromBD = respuestaFromBD + (partesOracion[i]);
                                }
                                respuestaFromBD = respuestaFromBD.trim();


                        for(int i = 0; i <= cadid.length; i++){
                            String aux = pregunta;
                            if(pregunta.equals(cadid[i])) {

                                Intent in = new Intent(Grammar_2_Activity.this, Grammar_2_Activity.class);
                                in.putExtra("curso", curso);
                                in.putExtra("lesson", lesson);
                                in.putExtra("calificacion", calis);
                                in.putExtra("actividad", actHechasS);
                                in.putExtra("id0", cadid[0]);
                                in.putExtra("id1", cadid[1]);
                                in.putExtra("id2", cadid[2]);
                                in.putExtra("id3", cadid[3]);
                                in.putExtra("id4", cadid[4]);
                                in.putExtra("id5", cadid[5]);
                                in.putExtra("id6", cadid[6]);
                                in.putExtra("id7", cadid[7]);
                                in.putExtra("id8", cadid[8]);

                                startActivity(in);
                            }

                            if (cadid[i].equals("0")) {

                                cadid[i] = aux;
                                if (cadid[i + 1].equals("0")) {
                                    cadid[i] = pregunta;
                                    break;
                                }
                            }
                        }

                        Log.i("aaa",cadid[0]+cadid[1]);

                        progressDialog.dismiss();
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
                respuestaSelected =  oracionHolder.getText();
                String rs = respuestaSelected.toString().trim();
                if(rs.equals(respuestaFromBD)){
                    calificacion.setMessage(cWord);
                    calificacion.setProgressStyle(5);
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);
                    calificacion.show();

                }else{
                    calificacion.setMessage(wWord + respuestaFromBD);
                    calificacion.setProgressStyle(2);
                    incorrect.start();
                    cali = cali + 0;
                    calis = String.valueOf(cali);
                    calificacion.show();
                }
            }
        });

    }
    @Override
    public void onBackPressed(){
        return;
    }
}
