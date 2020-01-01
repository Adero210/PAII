package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import ceti.edu.paii.activities.listening.Listening_1_Activity;
import ceti.edu.paii.activities.listening.Listening_3_Activity;
import ceti.edu.paii.activities.listening.Listening_4_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_1_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_2_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_3_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_1_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_4_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_Paragraph_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_paragraph_2_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_1_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_2_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_3_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_1_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_2_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_3_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_4_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_1_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_2_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_3_Activity;
import ceti.edu.paii.adapter.LeccionAdapterRecyclerView;
import ceti.edu.paii.comun.comun;

public class Activities_Activity extends AppCompatActivity {

    Button btnReading, btnWriting,btnspeaking,btnVocabulary,btnGrammar,btnListening;

    String language2;
    String leson;
    private static String URL_READ = comun.URL + "proyecto/read_cali.php";

    Button br,bw,bl,bv,bg,bs;
    private TextView tcalES,tcalL,tcalV, tcalH, tcalESCU,tcal6G;

    private String tipo;
    private String calES,calL,calV, calH, calESCU,cal6G;
    private TextView calitext1,calitext2,calitext3,calitext4,calitext5,calitext6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_);

        btnGrammar = findViewById(R.id.btn_grammar_activity);
        btnListening = findViewById(R.id.btn_listening_activity);
        btnReading = findViewById(R.id.btn_reading_activity);
        btnWriting = findViewById(R.id.btn_writing_activity);
        btnspeaking = findViewById(R.id.btn_speaking_activity);
        btnVocabulary = findViewById(R.id.btn_vocabulary_activity);

        br = findViewById(R.id.btn_reading_activity);
        bw = findViewById(R.id.btn_writing_activity);
        bl = findViewById(R.id.btn_listening_activity);
        bv = findViewById(R.id.btn_vocabulary_activity);
        bg = findViewById(R.id.btn_grammar_activity);
        bs = findViewById(R.id.btn_speaking_activity);

        calitext1 = findViewById(R.id.calific_reading_activity);
        calitext2 = findViewById(R.id.calific_writing_activity);
        calitext3 = findViewById(R.id.calific_speaking_activity);
        calitext4 = findViewById(R.id.calific_vocabulary_activity);
        calitext5 = findViewById(R.id.calific_grammar_activity);
        calitext6 = findViewById(R.id.calific_listening_activity);

        tcalES = findViewById(R.id.number_writing_activity);
        tcalL  = findViewById(R.id.number_reading_activity);
        tcalV  = findViewById(R.id.number_vocabulary_activity);
        tcalH  = findViewById(R.id.number_speaking_activity);
        tcalESCU  = findViewById(R.id.number_listenig_activity);
        tcal6G  = findViewById(R.id.number_grammar_activity);

        calitext1.setText("Calification: ");
        calitext2.setText("Calification: ");
        calitext3.setText("Calification: ");
        calitext4.setText("Calification: ");
        calitext5.setText("Calification: ");
        calitext6.setText("Calification: ");

        readCali();
        String data[] = new String[2];
        data = getDta();

        final String language = data[0];
        final String lesson   = data[1];

        if(language.equals("Italiano")){

            br.setText("lettura");
            bw.setText("scrittura");
            bl.setText("ascoltando");
            bv.setText("vocabolario");
            bg.setText("grammatica");
            bs.setText("A proposito di");

            calitext1.setText("Calif: ");
            calitext2.setText("Calif: ");
            calitext3.setText("Calif: ");
            calitext4.setText("Calif: ");
            calitext5.setText("Calif: ");
            calitext6.setText("Calif: ");
        }

        language2 = language;
        leson = lesson;


        Log.i("informaciondeleccion", language + lesson);


        btnVocabulary.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo="2";
                String num ="";
                String  actHechas = "1";
                String calificacion = "0";
                String actB1 = "1,2,3,4,0";
                String actB2 = "1,2,3,4,0";
                String actB3 = "1,2,3,4,0";
                String actB4 = "1,2,3,4,0";

                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Vocabulary_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);
                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        i.putExtra("boceto1",actB1);
                        i.putExtra("boceto2",actB2);
                        i.putExtra("boceto3",actB3);
                        i.putExtra("boceto4",actB4);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Vocabulary_2_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);
                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        intent.putExtra("boceto1",actB1);
                        intent.putExtra("boceto2",actB2);
                        intent.putExtra("boceto3",actB3);
                        intent.putExtra("boceto4",actB4);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Vocabulary_3_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);
                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        intent1.putExtra("boceto1",actB1);
                        intent1.putExtra("boceto2",actB2);
                        intent1.putExtra("boceto3",actB3);
                        intent1.putExtra("boceto4",actB4);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Vocabulary_4_Activity.class);

                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);

                        intent2.putExtra("calificacion",calificacion);
                        intent2.putExtra("actividad",actHechas);
                        intent2.putExtra("boceto1",actB1);
                        intent2.putExtra("boceto2",actB2);
                        intent2.putExtra("boceto3",actB3);
                        intent2.putExtra("boceto4",actB4);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnspeaking.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo = "3";
                String num ="";
                String  actHechas = "1";
                String calificacion = "0";
                String actB1 = "1,2,3,4,0";
                String actB2 = "1,2,3,4,0";
                String actB3 = "1,2,3,4,0";

                num = aleatorio();
                Log.i("numeroRamdon",num);
                    switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Speaking_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        i.putExtra("boceto1",actB1);
                        i.putExtra("boceto2",actB2);
                        i.putExtra("boceto3",actB3);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Speaking_2_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        intent.putExtra("boceto1",actB1);
                        intent.putExtra("boceto2",actB2);
                        intent.putExtra("boceto3",actB3);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Speaking_3_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);

                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        intent1.putExtra("boceto1",actB1);
                        intent1.putExtra("boceto2",actB2);
                        intent1.putExtra("boceto3",actB3);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Speaking_1_Activity.class);
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);

                        intent2.putExtra("calificacion",calificacion);
                        intent2.putExtra("actividad",actHechas);
                        intent2.putExtra("boceto1",actB1);
                        intent2.putExtra("boceto2",actB2);
                        intent2.putExtra("boceto3",actB3);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnWriting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo = "0";
                String num ="";
                String  actHechas = "1";
                String calificacion = "0";
                String actB1 = "1,2,3,4,0";
                String actB2 = "1,2,3,4,0";
                String actB3 = "1,2,3,4,0";

                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Writing_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        i.putExtra("boceto1",actB1);
                        i.putExtra("boceto2",actB2);
                        i.putExtra("boceto3",actB3);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Writing_2_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        intent.putExtra("boceto1",actB1);
                        intent.putExtra("boceto2",actB2);
                        intent.putExtra("boceto3",actB3);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Writing_3_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);

                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        intent1.putExtra("boceto1",actB1);
                        intent1.putExtra("boceto2",actB2);
                        intent1.putExtra("boceto3",actB3);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Writing_2_Activity.class);
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);

                        intent2.putExtra("calificacion",calificacion);
                        intent2.putExtra("actividad",actHechas);
                        intent2.putExtra("boceto1",actB1);
                        intent2.putExtra("boceto2",actB2);
                        intent2.putExtra("boceto3",actB3);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnReading.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Reading_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Reading_Paragraph_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Reading_paragraph_2_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Reading_4_Activity.class);
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        startActivity(intent2);
                        break;
                }
            }
        });


        btnListening.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo="4";
                String num ="";
                String actHechas = "1";
                String calificacion = "0";
                String actB2 = "1,2,3,4,0,5,6,7,8,9";
                String actB1 = "1,2,3,4,0";

                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Listening_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("tipo",tipo);

                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        i.putExtra("boceto1",actB1);
                        i.putExtra("boceto2",actB2);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Listening_1_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("tipo",tipo);

                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        intent.putExtra("boceto1",actB1);
                        intent.putExtra("boceto2",actB2);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Listening_4_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("tipo",tipo);

                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        intent1.putExtra("boceto1",actB1);
                        intent1.putExtra("boceto2",actB2);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Listening_4_Activity.class);
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("tipo",tipo);

                        intent2.putExtra("calificacion",calificacion);
                        intent2.putExtra("boceto1",actB1);
                        intent2.putExtra("boceto2",actB2);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipo = "5";
                String num ="";
                String  actHechas = "1";
                String calificacion = "0";
                String actB1 = "1,2,3,4,0";
                String actB2 = "1,2,3,4,0";
                String actB3 = "1,2,3,4,0";

                num = aleatorio();
                Log.i("numeroRamdon",num);
               switch (num){
                   case "0":
                       Intent i = new Intent(Activities_Activity.this, Grammar_1_Activity.class);
                       i.putExtra("curso",language);
                       i.putExtra("lesson",lesson);
                       i.putExtra("tipo",tipo);

                       i.putExtra("calificacion",calificacion);
                       i.putExtra("actividad",actHechas);
                       i.putExtra("boceto1",actB1);
                       i.putExtra("boceto2",actB2);
                       i.putExtra("boceto3",actB3);
                       startActivity(i);
                       break;

                   case "1":
                       Intent intent = new Intent(Activities_Activity.this, Grammar_2_Activity.class);
                       intent.putExtra("curso",language);
                       intent.putExtra("lesson",lesson);
                       intent.putExtra("tipo",tipo);

                       intent.putExtra("calificacion",calificacion);
                       intent.putExtra("actividad",actHechas);
                       intent.putExtra("boceto1",actB1);
                       intent.putExtra("boceto2",actB2);
                       intent.putExtra("boceto3",actB3);
                       startActivity(intent);
                       break;

                   case "2":
                       Intent intent1 = new Intent(Activities_Activity.this, Grammar_3_Activity.class);
                       intent1.putExtra("curso",language);
                       intent1.putExtra("lesson",lesson);
                       intent1.putExtra("tipo",tipo);

                       intent1.putExtra("calificacion",calificacion);
                       intent1.putExtra("actividad",actHechas);
                       intent1.putExtra("boceto1",actB1);
                       intent1.putExtra("boceto2",actB2);
                       intent1.putExtra("boceto3",actB3);
                       startActivity(intent1);
                       break;

                   case "3":
                       Intent intent2 = new Intent(Activities_Activity.this, Grammar_3_Activity.class);
                       intent2.putExtra("curso",language);
                       intent2.putExtra("lesson",lesson);
                       intent2.putExtra("tipo",tipo);

                       intent2.putExtra("calificacion",calificacion);
                       intent2.putExtra("actividad",actHechas);
                       intent2.putExtra("boceto1",actB1);
                       intent2.putExtra("boceto2",actB2);
                       intent2.putExtra("boceto3",actB3);
                       startActivity(intent2);
                       break;
               }
            }
        });

    }

    private void readCali() {
        final ProgressDialog progressDialog = new ProgressDialog(Activities_Activity.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    calES = object.getString("calificacion0");
                                    calL = object.getString("calificacion1");
                                    calV = object.getString("calificacion2");
                                    calH = object.getString("calificacion3");
                                    calESCU = object.getString("calificacion4");
                                    cal6G = object.getString("calificacion5");

                                    double cales = Double.valueOf(calES);
                                    double call = Double.valueOf(calL);
                                    double calv = Double.valueOf(calV);
                                    double calh = Double.valueOf(calH);
                                    double calescu = Double.valueOf(calESCU);
                                    double calg = Double.valueOf(cal6G);


                                    if(cales<6.0){
                                        tcalES.setTextColor(Color.parseColor("#ff0000"));
                                    }if(call<6.0){
                                        tcalL.setTextColor(Color.parseColor("#ff0000"));
                                    }if(calv<6.0){
                                        tcalV.setTextColor(Color.parseColor("#ff0000"));
                                    }if(calh<6.0){
                                        tcalH.setTextColor(Color.parseColor("#ff0000"));
                                    }if(calescu<6.0){
                                        tcalESCU.setTextColor(Color.parseColor("#ff0000"));
                                    }if(calg<6.0){
                                        tcal6G.setTextColor(Color.parseColor("#ff0000"));
                                    }

                                    tcalES.setText(calES);
                                    tcalL.setText(calL);
                                    tcalV.setText(calV);
                                    tcalH.setText(calH);
                                    tcalESCU.setText(calESCU);
                                    tcal6G.setText(cal6G);


                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            // "Error reading dialog: " + e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(PictureDetailActivity.this, "Error reading dialog: " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", comun.userNameLec);
                params.put("idLec", String.valueOf(comun.lessonCalis));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Activities_Activity.this);
        requestQueue.add(stringRequest);
    }

    private String aleatorio(){
        // AleatoriSinRepeticion();
        String num = "";
        int pos;
        int nCartas = 4;
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

    private String[] getDta(){
        String data[] = new String[2];
        if(getIntent().hasExtra("curse_name")){
            data[0] = getIntent().getStringExtra("curse_name");
            data[1] = getIntent().getStringExtra("lesson");
        }
        return  data;
    }


}
