package ceti.edu.paii.activities.listening.writing;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
    private TextView  oracionText;
    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";

    private String boceto = "2";

    private String respuestaFromBD = "";
    private EditText respuestaUser;
    private MediaPlayer mediaPlayer,incorrect;

    private Button calificar;
    private Button continuar;

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
        setContentView(R.layout.activity_writing_2_);

        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        calis   = getIntent().getStringExtra("calificacion");
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

        if(actHechas <= 8) {

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


            numAletorio = "1";

            if (curso.equals("English")) {
                oracion.setText("Order the paragraph");
                bto = "Continue";
                cWord = "Correct!";
                wWord = "Wrong: ";
            } else if (curso.equals("Italiano")) {
                oracion.setText("Ordina il paragrafo");
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

            calificacion =  new ProgressDialog(Writing_2_Activity.this);
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
                            Intent i = new Intent(Writing_2_Activity.this, Writing_1_Activity.class);
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
                            i.putExtra("id8",cadid[8]);
                            startActivity(i);
                            break;

                        case "1":
                            Intent intent = new Intent(Writing_2_Activity.this, Writing_2_Activity.class);
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
                            intent.putExtra("id8",cadid[8]);
                            startActivity(intent);
                            break;

                        case "2":
                            Intent intent1 = new Intent(Writing_2_Activity.this, Writing_3_Activity.class);
                            intent1.putExtra("curso",curso);
                            intent1.putExtra("lesson",lesson);
                            intent1.putExtra("calificacion",calis);
                            intent1.putExtra("actividad",actHechasS);
                            intent1.putExtra("id0",cadid[0]);
                            intent1.putExtra("id1",cadid[1]);
                            intent1.putExtra("id2",cadid[2]);
                            intent1.putExtra("id3",cadid[3]);
                            intent1.putExtra("id4",cadid[4]);
                            intent1.putExtra("id5",cadid[5]);
                            intent1.putExtra("id6",cadid[6]);
                            intent1.putExtra("id7",cadid[7]);
                            intent1.putExtra("id8",cadid[8]);
                            startActivity(intent1);
                            break;

                    }
                }
            });
            calificacion.setCancelable(false);
            bringTheInfo(lessonint - 1, numAletorio);
            opcions();

        }else {
            Intent i = new Intent(Writing_2_Activity.this, ResumenActividad.class);
            String tipo = "Escritura";
            i.putExtra("tipo",tipo);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
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
                    calificacion.setMessage(cWord);
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);
                    calificacion.show();
                }else if(ans.equals(resMay)){
                    calificacion.setMessage(cWord);
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);
                    calificacion.show();
                }else if(ans.equals(resMin)){
                    calificacion.setMessage(cWord);
                    mediaPlayer.start();
                    cali = cali + 100;
                    calis = String.valueOf(cali);
                    calificacion.show();
                }else{
                    calificacion.setMessage(wWord + respuestaFromBD);
                    incorrect.start();
                    cali = cali + 0;
                    calis = String.valueOf(cali);
                    calificacion.show();
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
                    Log.i("ahhhha","entre al try");

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {
                        Log.i("ahhhha","success");

                        JSONObject object =  jsonArray.getJSONObject(0);


                        String pregunta = object.getString("question").trim();


                        String partesOra[] = pregunta.split("~");

                        for (int j = 0; j<partesOra.length; j++){
                            respuestaFromBD = respuestaFromBD + partesOra[j];
                        }
                        respuestaFromBD=respuestaFromBD.trim();


                        for(int i = 0; i <= cadid.length; i++){
                            String aux = pregunta;
                            if(pregunta.equals(cadid[i])) {

                                Intent in = new Intent(Writing_2_Activity.this, Writing_2_Activity.class);
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
                                allOracion = partR1+" / "+partR2;
                                oracionText.setText(allOracion);
                                break;
                            case 3:
                                partR1 = r[0];
                                partR2 = r[1];
                                partR3 = r[2];
                                allOracion = partR1+" / "+partR2+" / "+partR3;
                                oracionText.setText(allOracion);
                                break;
                            case 4:
                                partR1 = r[0];
                                partR2 = r[1];
                                partR3 = r[2];
                                partR4 = r[3];
                                allOracion = partR1+" / "+partR2+" / "+partR3+" / "+partR4;
                                oracionText.setText(allOracion);
                                break;
                            case 5:
                                partR1 = r[0];
                                partR2 = r[1];
                                partR3 = r[2];
                                partR4 = r[3];
                                partR5 = r[4];
                                allOracion = partR1+" / "+partR2+" / "+partR3+" / "+partR4+" / "+partR5;
                                oracionText.setText(allOracion);
                                break;
                            case 6:
                                partR1 = r[0];
                                partR2 = r[1];
                                partR3 = r[2];
                                partR4 = r[3];
                                partR5 = r[4];
                                partR6 = r[5];
                                allOracion = partR1+" / "+partR2+" / "+partR3+" / "+partR4+" / "+partR5+" / "+partR6;
                                oracionText.setText(allOracion);
                                break;

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
                params.put("numberOfQuestions",numAle);
                params.put("lectionId", String.valueOf(lessonint2));
                params.put("sketch",boceto);
                params.put("typeName","Escritura");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed(){
        return;
    }

}
