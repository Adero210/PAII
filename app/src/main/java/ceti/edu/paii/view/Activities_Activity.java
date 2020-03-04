package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import ceti.edu.paii.comun.comun;

public class Activities_Activity extends AppCompatActivity {

    Button btnReading, btnWriting,btnspeaking,btnVocabulary,btnGrammar,btnListening;

    String language2;
    String leson;
    private static String URL_READ = comun.URL + "getUserActivities.php";

    Button br,bw,bl,bv,bg,bs;
    private TextView tcalES,tcalL,tcalV, tcalH, tcalESCU,tcal6G;

    public static String calES,calL,calV, calH, calESCU,cal6G;
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

        calitext1.setText("qualification: ");
        calitext2.setText("qualification: ");
        calitext3.setText("qualification: ");
        calitext4.setText("qualification: ");
        calitext5.setText("qualification: ");
        calitext6.setText("qualification: ");

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

            calitext1.setText("qualificazione: ");
            calitext2.setText("qualificazione: ");
            calitext3.setText("qualificazione: ");
            calitext4.setText("qualificazione: ");
            calitext5.setText("qualificazione: ");
            calitext6.setText("qualificazione: ");
        }

        language2 = language;
        leson = lesson;


        Log.i("informaciondeleccion", language + lesson);


        btnVocabulary.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String boceto;
                String num ="";
                String  actHechas = "1";
                String calificacion = "0";
                num = comun.aleatorio(4);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Vocabulary_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Vocabulary_2_Activity.class);
                        boceto = "2";
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("boceto",boceto);
                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Vocabulary_3_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Vocabulary_2_Activity.class);
                        boceto = "4";
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("boceto",boceto);
                        intent2.putExtra("calificacion",calificacion);
                        intent2.putExtra("actividad",actHechas);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnspeaking.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                String  actHechas = "1";
                String calificacion = "0";

                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                    switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Speaking_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Speaking_2_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Speaking_3_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        startActivity(intent1);
                        break;
                }
            }
        });

        btnWriting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                String  actHechas = "1";
                String calificacion = "0";
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Writing_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Writing_2_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Writing_3_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        startActivity(intent1);
                        break;
                }
            }
        });

        btnReading.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = "";
                String  actHechas = "1";
                String calificacion = "0";
                num = comun.aleatorio(4);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Reading_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Reading_Paragraph_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Reading_paragraph_2_Activity.class);
                        intent1.putExtra("curso",language);
                        intent1.putExtra("lesson",lesson);
                        intent1.putExtra("calificacion",calificacion);
                        intent1.putExtra("actividad",actHechas);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Reading_4_Activity.class);
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("calificacion",calificacion);
                        intent2.putExtra("actividad",actHechas);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnListening.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                String actHechas = "1";
                String calificacion = "0";

                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Listening_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        i.putExtra("calificacion",calificacion);
                        i.putExtra("actividad",actHechas);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Listening_3_Activity.class);
                        intent.putExtra("curso",language);
                        intent.putExtra("lesson",lesson);
                        intent.putExtra("calificacion",calificacion);
                        intent.putExtra("actividad",actHechas);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent2= new Intent(Activities_Activity.this, Listening_4_Activity.class);
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        intent2.putExtra("calificacion",calificacion);
                        intent2.putExtra("actividad",actHechas);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num;
                String actHechas = "1";
                String calificacion = "0";
                num = comun.aleatorio(3);
                Log.i("numeroRamdon",num);
               switch (num) {
                   case "0":
                       Intent i = new Intent(Activities_Activity.this, Grammar_1_Activity.class);
                       i.putExtra("curso", language);
                       i.putExtra("lesson", lesson);
                       i.putExtra("calificacion", calificacion);
                       i.putExtra("actividad", actHechas);
                       startActivity(i);
                       break;

                   case "1":
                       Intent intent = new Intent(Activities_Activity.this, Grammar_2_Activity.class);
                       intent.putExtra("curso", language);
                       intent.putExtra("lesson", lesson);
                       intent.putExtra("calificacion", calificacion);
                       intent.putExtra("actividad", actHechas);
                       startActivity(intent);
                       break;

                   case "2":
                       Intent intent1 = new Intent(Activities_Activity.this, Grammar_3_Activity.class);
                       intent1.putExtra("curso", language);
                       intent1.putExtra("lesson", lesson);
                       intent1.putExtra("calificacion", calificacion);
                       intent1.putExtra("actividad", actHechas);
                       startActivity(intent1);
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
                            String success = jsonObject.getString("status");
                            JSONArray jsonArray = jsonObject.getJSONArray("activities");
                            if (success.equals("GOOD")) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    JSONObject activity = object.getJSONObject("type");

                                    if(activity.getString("name").equals("Escritura"))
                                        calES = object.getString("calification");
                                    if(activity.getString("name").equals("Lectura"))
                                        calL = object.getString("calification");
                                    if(activity.getString("name").equals("Vocabulario"))
                                        calV = object.getString("calification");
                                    if(activity.getString("name").equals("Habla"))
                                        calH = object.getString("calification");
                                    if(activity.getString("name").equals("Escucha"))
                                        calESCU = object.getString("calification");
                                    if(activity.getString("name").equals("Gramatica"))
                                        cal6G = object.getString("calification");

                                }
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
                params.put("nickname", comun.userNameLec);
                params.put("lectionId", String.valueOf(comun.lessonCalis));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Activities_Activity.this);
        requestQueue.add(stringRequest);
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
