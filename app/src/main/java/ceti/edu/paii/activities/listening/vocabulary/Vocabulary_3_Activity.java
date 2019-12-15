package ceti.edu.paii.activities.listening.vocabulary;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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



    private TextView oracion;

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;

    String palabras[];

    Button [] botonera = new Button[10];

    int fondo;

    ArrayList<String> arrayBarajado;

    Button primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo = false;


    final Handler handler = new Handler();

    //finalmente, el número de aciertos y la puntuación
    int aciertos=0;
    int puntuacion=0;
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

        //bringTheInfo(lessonint - 1, numAletorio);
        opciones();

    }

    private void opciones() {
    }

    private void iniciar() {
    }

    private void botonesMenu() {
    }

    private void cargarImagenes() {
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

                            }

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
