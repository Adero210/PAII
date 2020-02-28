package ceti.edu.paii.activities.listening.writing;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.reading.Reading_1_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Writing_2_Activity extends AppCompatActivity {


    private TextView oracion;
    private String tipo;
    private TextView  oracionText;
    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "genericAct.php";

    private String boceto = "2";

    private String respuestaFromBD = "";
    private EditText respuestaUser;
    private MediaPlayer mediaPlayer,incorrect;

    private Button calificar;
    private Button continuar;

    private int numerosPreuntas = 5;

    private String curso;
    private String lesson;

    int actHechas, cali;
    private String b1,b2,b3, calis, actHechasS;

    private  String numAletorio ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_2_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
        actHechasS = getIntent().getStringExtra("actividad");
        b1 = getIntent().getStringExtra("boceto1");
        b2 = getIntent().getStringExtra("boceto2");
        b3 = getIntent().getStringExtra("boceto3");
        tipo = getIntent().getStringExtra("tipo");
        cali = Integer.valueOf(calis);
        actHechas = Integer.valueOf(actHechasS);

        if(actHechas<=8) {

            progressDialog = new ProgressDialog(Writing_2_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            oracion = findViewById(R.id.textview_1_activity_writing_2);
            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);
            oracionText = findViewById(R.id.textview_2_activity_writing_2);
            respuestaUser = findViewById(R.id.editText_1_activity_writing_2);

            calificar = findViewById(R.id.button_activity_Writing_2);
            continuar = findViewById(R.id.button_continuar_activity_writing_2);


            numAletorio = comun.aleatorio(numerosPreuntas);
            if(b2.contains(numAletorio)) {
                if (curso.equals("Ingles")) {
                    oracion.setText("answers");
                } else if (curso.equals("Italiano")) {
                    oracion.setText("risposta");
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
                opcions();

            }else {

                Intent i = new Intent(Writing_2_Activity.this, Writing_2_Activity.class);
                i.putExtra("curso",curso);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);

                i.putExtra("calificacion",String.valueOf(cali));
                i.putExtra("actividad",String.valueOf(actHechas));
                i.putExtra("boceto1",b1);
                i.putExtra("boceto2",b2);
                i.putExtra("boceto3",b3);
                startActivity(i);
            }

        }else {
            Intent i = new Intent(Writing_2_Activity.this, ResumenActividad.class);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("tipo",tipo);

            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);

        }



    }

    private void opcions() {
        calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calificar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                //String fre = String.valueOf(respuestaUser.getText());
                String ans = String.valueOf(respuestaUser.getText());
                Log.i("respuestass",respuestaFromBD);
                Log.i("respuestass", ans);

                String resMay = respuestaFromBD.toUpperCase();
                String resMin = respuestaFromBD.toLowerCase();

                if(ans.equals(respuestaFromBD)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    Toast.makeText(Writing_2_Activity.this,"Correct",Toast.LENGTH_SHORT).show();

                }else if(ans.equals(resMay)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    Toast.makeText(Writing_2_Activity.this,"Correct",Toast.LENGTH_SHORT).show();

                }else if(ans.equals(resMin)){
                    mediaPlayer.start();
                    cali = cali + 100;
                    Toast.makeText(Writing_2_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    incorrect.start();
                    cali = cali + 0;
                    Toast.makeText(Writing_2_Activity.this,"Correct answer: " + respuestaFromBD,Toast.LENGTH_SHORT).show();
                }
            }
        });
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b2N = b2.replaceAll(numAletorio,"");
                actHechas++;
                String num ="";
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Writing_2_Activity.this, Writing_1_Activity.class);
                        i.putExtra("curso",curso);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",String.valueOf(cali));
                        i.putExtra("actividad",String.valueOf(actHechas));
                        i.putExtra("boceto1",b1);
                        i.putExtra("boceto2",b2N);
                        i.putExtra("boceto3",b3);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Writing_2_Activity.this, Writing_2_Activity.class);
                        intent.putExtra("curso",curso);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",String.valueOf(cali));
                        intent.putExtra("actividad",String.valueOf(actHechas));
                        intent.putExtra("boceto1",b1);
                        intent.putExtra("boceto2",b2N);
                        intent.putExtra("boceto3",b3);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Writing_2_Activity.this, Writing_3_Activity.class);
                        intent1.putExtra("curso",curso);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);
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
                                respuestaFromBD = object.getString("respuestac" + h);

                                String partesOra[] = pregunta.split("~");

                                int tam = partesOra.length;

                                String[] r = new String[6];
                                // AleatoriSinRepeticion();
                                int pos,y=0;
                                int nCartas = tam;
                                Stack< Integer > pCartas = new Stack < Integer > ();
                                for (int t = 0; t < nCartas ; t++) {
                                    pos = (int) Math.floor(Math.random() * nCartas );
                                    while (pCartas.contains(pos)) {
                                        pos = (int) Math.floor(Math.random() * nCartas );
                                    }
                                    r[pos] = partesOra[y];
                                    pCartas.push(pos);
                                    y++;
                                }
                                Log.i("Numeros",pCartas.toString());

                                String partR1;
                                String partR2;
                                String partR3;
                                String partR4;
                                String partR5;
                                String partR6;
                                String allOracion;

                                switch (tam){
                                    case 2:
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        allOracion = partR1+partR2;
                                        oracionText.setText(allOracion);
                                        break;
                                    case 3:
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        allOracion = partR1+partR2+partR3;
                                        oracionText.setText(allOracion);
                                        break;
                                    case 4:
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        allOracion = partR1+partR2+partR3+partR4;
                                        oracionText.setText(allOracion);
                                        break;
                                    case 5:
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        partR5 = r[4];
                                        allOracion = partR1+partR2+partR3+partR4+partR5;
                                        oracionText.setText(allOracion);
                                        break;
                                    case 6:
                                        partR1 = r[0];
                                        partR2 = r[1];
                                        partR3 = r[2];
                                        partR4 = r[3];
                                        partR5 = r[4];
                                        partR6 = r[5];
                                        allOracion = partR1+partR2+partR3+partR4+partR5+partR6;
                                        oracionText.setText(allOracion);
                                        break;

                                }
                            }

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Writing_2_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Writing_2_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("boceto",boceto);
                params.put("type","writing");


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
        int nCartas = 6;
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
