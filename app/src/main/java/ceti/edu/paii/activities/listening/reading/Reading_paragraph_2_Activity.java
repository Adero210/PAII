package ceti.edu.paii.activities.listening.reading;

import android.app.ProgressDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;

public class Reading_paragraph_2_Activity extends AppCompatActivity {
    private Button buttoncont;

    private ProgressDialog progressDialog;
    private TextView parrafo;
    private static String URL_ACTR2 = comun.URL + "proyecto/genericAct.php";
    private String boceto = "4";
    private String pregunta1;
    private String pregunta2;
    private String pregunta3;
    private String pregunta4;
    private String pregunta5;
    private String pregunta6;
    private String pregunta7;
    private String pregunta8;
    private String pregunta9;
    private String pregunta10;
    private String respuesta1c;
    private String respuesta2c;
    private String respuesta3c;
    private String respuesta4c;
    private String respuesta5c;
    private String respuesta6c;
    private String respuesta7c;
    private String respuesta8c;
    private String respuesta9c;
    private String respuesta10c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_paragraph_2_);


        buttoncont = findViewById(R.id.button_activity_reading_parrafo_2);
        progressDialog =  new ProgressDialog(Reading_paragraph_2_Activity.this);
        parrafo = findViewById(R.id.parrafo_Reading_2);

        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);


        final String curso = getIntent().getStringExtra("curso");
        final String lesson = getIntent().getStringExtra("lesson");
        Log.i("importantstuff",curso+lesson);

        String numAletorio = aleatorio();

        int lessonint = Integer.parseInt(lesson);

        if(curso.equals("Curso Italiano")) {
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

        bringTheInfo(lessonint - 1, "0");


        buttoncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Reading_3_Activity.class);

                i.putExtra("curso", curso);
                i.putExtra("lesson", lesson);

                i.putExtra("pregunta1", pregunta1);
                i.putExtra("pregunta2", pregunta2);
                i.putExtra("pregunta3", pregunta3);
                i.putExtra("pregunta4", pregunta4);
                i.putExtra("pregunta5", pregunta5);
                i.putExtra("pregunta6", pregunta6);
                i.putExtra("pregunta7", pregunta7);
                i.putExtra("pregunta8", pregunta8);
                i.putExtra("pregunta9", pregunta9);
                i.putExtra("pregunta10", pregunta10);


                i.putExtra("respuesta1c", respuesta1c);
                i.putExtra("respuesta2c", respuesta2c);
                i.putExtra("respuesta3c", respuesta3c);
                i.putExtra("respuesta4c", respuesta4c);
                i.putExtra("respuesta5c", respuesta5c);
                i.putExtra("respuesta6c", respuesta6c);
                i.putExtra("respuesta7c", respuesta7c);
                i.putExtra("respuesta8c", respuesta8c);
                i.putExtra("respuesta9c", respuesta9c);
                i.putExtra("respuesta10c", respuesta10c);


                startActivity(i);
            }
        });
    }

    private void bringTheInfo(final int lessontint2, final String numAle) {
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
                            pregunta6 = object.getString("pregunta5");
                            pregunta7 = object.getString("pregunta6");
                            pregunta8 = object.getString("pregunta7");
                            pregunta9 = object.getString("pregunta8");
                            pregunta10 = object.getString("pregunta9");

                            respuesta1c = object.getString("respuestac0");
                            respuesta2c = object.getString("respuestac1");
                            respuesta3c = object.getString("respuestac2");
                            respuesta4c = object.getString("respuestac3");
                            respuesta5c = object.getString("respuestac4");
                            respuesta6c = object.getString("respuestac5");
                            respuesta7c = object.getString("respuestac6");
                            respuesta8c = object.getString("respuestac7");
                            respuesta9c = object.getString("respuestac8");
                            respuesta10c = object.getString("respuestac9");



                            Log.i("PARRAFO", parrafoString);

                            parrafo.setText(parrafoString);

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Reading_paragraph_2_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Reading_paragraph_2_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessontint2));
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
