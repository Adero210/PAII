package ceti.edu.paii.activities.listening.reading;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Reading_Paragraph_Activity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private TextView parrafo;
    private static String URL_ACTR2 = comun.URL + "genericAct.php";
    private String boceto = "3";
    private String pregunta1;
    private String pregunta2;
    private String pregunta3;
    private String pregunta4;
    private String pregunta5;
    private String respuesta1c;
    private String respuesta2c;
    private String respuesta3c;
    private String respuesta4c;
    private String respuesta5c;
    private String opcA1;
    private String opcB1;
    private String opcC1;
    private String opcD1;
    private String opcA2;
    private String opcB2;
    private String opcC2;
    private String opcD2;
    private String opcA3;
    private String opcB3;
    private String opcC3;
    private String opcD3;
    private String opcA4;
    private String opcB4;
    private String opcC4;
    private String opcD4;
    private String opcA5;
    private String opcB5;
    private String opcC5;
    private String opcD5;


    private String curso;
    private String lesson;

    private String tipo;
    private TextView oracion;


    int actHechas, cali;
    private String b1,b2,b3,b4, calis, actHechasS;
    private  String numAletorio ="";
    private int numerosPreuntas = 3;



    private Button buttoncont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading__paragraph_);

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
        oracion = findViewById(R.id.pregunta_parrafo_Reading_1);
        if(actHechas<=8) {

            buttoncont = findViewById(R.id.button_activity_reading_parrafo_1);
            progressDialog = new ProgressDialog(Reading_Paragraph_Activity.this);
            parrafo = findViewById(R.id.parrafo_Reading_1);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);


            Log.i("importantstuff", curso + lesson);

            if (curso.equals("Ingles")) {
                oracion.setText("read carefully");
            } else if (curso.equals("Italiano")) {
                oracion.setText("leggi attentamente");
            }
            numAletorio = comun.aleatorio(numerosPreuntas);


            if (curso.equals("Ingles")) {
                oracion.setText("Listen and Repeat");
            } else if (curso.equals("Italiano")) {
                oracion.setText("Ascolta e ripeti");
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

            Log.i("pleasefunc",String.valueOf(lessonint-1)+ " : " +numAletorio);

            bringTheInfo(lessonint - 1, numAletorio);

            opciones();
        }else {
            Intent i = new Intent(Reading_Paragraph_Activity.this, ResumenActividad.class);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("tipo",tipo);

            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);

        }



    }

    private void opciones() {

        buttoncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Reading_2_Activity.class);
                i.putExtra("curso", curso);
                i.putExtra("lesson", lesson);

                i.putExtra("tipo",tipo);

                i.putExtra("calificacion",String.valueOf(cali));
                i.putExtra("actividad",String.valueOf(actHechas));
                i.putExtra("boceto1",b1);
                i.putExtra("boceto2",b2);
                i.putExtra("boceto3",b3);
                i.putExtra("boceto4",b4);

                i.putExtra("pregunta1", pregunta1);
                i.putExtra("pregunta2", pregunta2);
                i.putExtra("pregunta3", pregunta3);
                i.putExtra("pregunta4", pregunta4);
                i.putExtra("pregunta5", pregunta5);

                i.putExtra("respuesta1c", respuesta1c);
                i.putExtra("respuesta2c", respuesta2c);
                i.putExtra("respuesta3c", respuesta3c);
                i.putExtra("respuesta4c", respuesta4c);
                i.putExtra("respuesta5c", respuesta5c);

                i.putExtra("OpcA1", opcA1);
                i.putExtra("OpcB1", opcB1);
                i.putExtra("OpcC1", opcC1);
                i.putExtra("OpcD1", opcD1);

                i.putExtra("OpcA2", opcA2);
                i.putExtra("OpcB2", opcB2);
                i.putExtra("OpcC2", opcC2);
                i.putExtra("OpcD2", opcD2);

                Log.i("AAAA",opcA2);

                i.putExtra("OpcA3",opcA3);
                i.putExtra("OpcB3",opcB3);
                i.putExtra("OpcC3",opcC3);
                i.putExtra("OpcD3",opcD3);

                i.putExtra("OpcA4",opcA4);
                i.putExtra("OpcB4",opcB4);
                i.putExtra("OpcC4",opcC4);
                i.putExtra("OpcD4",opcD4);

                i.putExtra("OpcA5",opcA5);
                i.putExtra("OpcB5",opcB5);
                i.putExtra("OpcC5",opcC5);
                i.putExtra("OpcD5",opcD5);

                startActivity(i);
            }
        });
    }

    private void bringTheInfo(final int lessonint2, final String numAle) {
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



                                String parrafoString = object.getString("parrafo");


                                pregunta1 = object.getString("pregunta0");
                                pregunta2 = object.getString("pregunta1");
                                pregunta3 = object.getString("pregunta2");
                                pregunta4 = object.getString("pregunta3");
                                pregunta5 = object.getString("pregunta4");

                                respuesta1c = object.getString("respuestac0");
                                respuesta2c = object.getString("respuestac1");
                                respuesta3c = object.getString("respuestac2");
                                respuesta4c = object.getString("respuestac3");
                                respuesta5c = object.getString("respuestac4");

                                opcA1 = object.getString("opcA0");
                                opcB1 = object.getString("opcB0");
                                opcC1 = object.getString("opcC0");
                                opcD1 = object.getString("opcD0");
                                opcA2 = object.getString("opcA1");
                                opcB2 = object.getString("opcB1");
                                opcC2 = object.getString("opcC1");
                                opcD2 = object.getString("opcD1");
                                opcA3 = object.getString("opcA2");
                                opcB3 = object.getString("opcB2");
                                opcC3 = object.getString("opcC2");
                                opcD3 = object.getString("opcD2");
                                opcA4 = object.getString("opcA3");
                                opcB4 = object.getString("opcB3");
                                opcC4 = object.getString("opcC3");
                                opcD4 = object.getString("opcD3");
                                opcA5 = object.getString("opcA4");
                                opcB5 = object.getString("opcB4");
                                opcC5 = object.getString("opcC4");
                                opcD5 = object.getString("opcD4");


                            Log.i("AAAH", opcA1);

                            Log.i("PARRAFO", parrafoString);

                                parrafo.setText(parrafoString);




                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Reading_Paragraph_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Reading_Paragraph_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


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

    @Override
    public void onBackPressed(){
        return;
    }
}
