package ceti.edu.paii.activities.listening.vocabulary;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import ceti.edu.paii.view.vocabularyThree;

public class Vocabulary_3_Activity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";
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


    private String curso;
    private String lesson;
    private String boceto = "3";

    private String pregunta = "";
    private String respuestaFromBD = "";
    private String respuestaSelected1 ="";
    private MediaPlayer mediaPlayer,incorrect;
    private int cartSel = 0;
    private TextView Titulo;
    private  String numAletorio;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,cont;
    private String respuestaSelected2="";
    private int paCo=0;
    private int paIn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_3_);

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
            progressDialog = new ProgressDialog(Vocabulary_3_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            Titulo = findViewById(R.id.titulo_vocabulary_3);
            btn0 = findViewById(R.id.opcion1_activity_vocabulary_3);
            btn1 = findViewById(R.id.opcion2_activity_vocabulary_3);
            btn2 = findViewById(R.id.opcion3_activity_vocabulary_3);
            btn3 = findViewById(R.id.opcion4_activity_vocabulary_3);
            btn4 = findViewById(R.id.opcion5_activity_vocabulary_3);
            btn5 = findViewById(R.id.opcion6_activity_vocabulary_3);
            btn6 = findViewById(R.id.opcion7_activity_vocabulary_3);
            btn7 = findViewById(R.id.opcion8_activity_vocabulary_3);
            btn8 = findViewById(R.id.opcion9_activity_vocabulary_3);
            btn9 = findViewById(R.id.opcion0_activity_vocabulary_3);

            cont = findViewById(R.id.button_activity_vocabulary_3);

            numAletorio = "5";

            if (curso.equals("English")) {
                Titulo.setText("Answer");
                bto = "Continue";
                cWord = "Correct!";
                wWord = "Wrong: ";
            } else if (curso.equals("Italiano")) {
                Titulo.setText("Risposta");
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

            calificacion =  new ProgressDialog(Vocabulary_3_Activity.this);
            calificacion.setButton(AlertDialog.BUTTON_NEGATIVE, bto, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    progressDialog.dismiss();
                    actHechas++;
                    actHechasS = String.valueOf(actHechas);
                    Log.i("aaaa",cadid[0]);
                    String num;
                    num = comun.aleatorio(4);
                    Log.i("numeroRamdon",num);
                    switch (num){
                        case "0":
                            Intent i = new Intent(Vocabulary_3_Activity.this, Vocabulary_1_Activity.class);
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
                            boceto = "2";
                            Intent intent = new Intent(Vocabulary_3_Activity.this, Vocabulary_2_Activity.class);
                            intent.putExtra("curso",curso);
                            intent.putExtra("lesson",lesson);
                            intent.putExtra("boceto", boceto);
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
                            Intent intent1 = new Intent(Vocabulary_3_Activity.this, Vocabulary_3_Activity.class);
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
                            intent1.putExtra("id8", cadid[8]);
                            startActivity(intent1);
                            break;

                        case "3":
                            boceto = "4";
                            Intent intent2 = new Intent(Vocabulary_3_Activity.this, Vocabulary_2_Activity.class);
                            intent2.putExtra("curso",curso);
                            intent2.putExtra("lesson",lesson);
                            intent2.putExtra("boceto", boceto);
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

        }
        else {
            Intent i = new Intent(Vocabulary_3_Activity.this, ResumenActividad.class);
            String tipo = "Vocabulario";
            i.putExtra("tipo",tipo);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);

        }

    }

    private void opciones() {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn0.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn0.getText();
                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn0.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn0.getText();
                    cartSel=1;
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn1.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn1.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn1.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn1.getText();

                    cartSel=1;

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn2.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn2.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn2.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn2.getText();

                    cartSel=1;

                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn3.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn3.getText();
                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn3.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn3.getText();

                    cartSel=1;
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn4.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn4.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn4.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn4.getText();
                    cartSel=1;

                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn5.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn5.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn5.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn5.getText();

                    cartSel=1;

                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn6.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn6.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn6.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn6.getText();

                    cartSel=1;

                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn7.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn7.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn7.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn7.getText();

                    cartSel=1;

                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn8.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn8.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn8.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn8.getText();

                    cartSel=1;

                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartSel==1){
                    btn9.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected2 = (String) btn9.getText();

                    check(respuestaSelected1,respuestaSelected2);
                }else {
                    btn9.setTextColor(Color.parseColor("#008000"));
                    respuestaSelected1 = (String) btn9.getText();

                    cartSel=1;

                }
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paIn>3){
                    cali = cali+0;
                    calis = String.valueOf(cali);

                }else{
                    cali = cali+100;
                    calis = String.valueOf(cali);


                }
                calificacion.show();
            }
        });
    }

    private void check(String r1, String r2) {
        String pregunta1 = Questions.questions[0];
        String respuesta1 = Questions.corrects[0];
        String pregunta2 = Questions.questions[1];
        String respuesta2 = Questions.corrects[1];
        String pregunta3 = Questions.questions[2];
        String respuesta3 = Questions.corrects[2];
        String pregunta4 = Questions.questions[3];
        String respuesta4 = Questions.corrects[3];
        String pregunta5 = Questions.questions[4];
        String respuesta5 = Questions.corrects[4];


        if(paCo<=5) {

            if (pregunta1.equals(r1)) {
                if (respuesta1.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);

                }
            }else if(pregunta2.equals(r1)){
                if (respuesta2.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(pregunta3.equals(r1)){
                if (respuesta3.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(pregunta4.equals(r1)){
                if (respuesta4.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(pregunta5.equals(r1)){
                if (respuesta5.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta1.equals(r1)) {
                if (pregunta1.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta2.equals(r1)) {
                if (pregunta2.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta3.equals(r1)) {
                if (pregunta3.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta4.equals(r1)) {
                if (pregunta4.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }else if(respuesta5.equals(r1)) {
                if (pregunta5.equals(r2)) {
                    paCo++;
                } else {
                    paIn++;
                    paCo=0;

                    btn0.setTextColor(Color.WHITE);
                    btn1.setTextColor(Color.WHITE);
                    btn2.setTextColor(Color.WHITE);
                    btn3.setTextColor(Color.WHITE);
                    btn4.setTextColor(Color.WHITE);
                    btn5.setTextColor(Color.WHITE);
                    btn6.setTextColor(Color.WHITE);
                    btn7.setTextColor(Color.WHITE);
                    btn8.setTextColor(Color.WHITE);
                    btn9.setTextColor(Color.WHITE);
                }
            }

            cartSel = 0;

        }else{

            btn0.setEnabled(false);
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);
            btn5.setEnabled(false);
            btn6.setEnabled(false);
            btn7.setEnabled(false);
            btn8.setEnabled(false);
            btn9.setEnabled(false);
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
                        Log.i("ahhhha","success");

                        getQuestions(jsonArray);

                        String questionOne = Questions.questions[0];
                        String questionTwo = Questions.questions[1];
                        String questionThree = Questions.questions[2];
                        String questionFour = Questions.questions[3];
                        String questionFive = Questions.questions[4];

                        String correctOne = Questions.corrects[3];
                        String correctTwo = Questions.corrects[1];
                        String correctThree = Questions.corrects[0];
                        String correctFour = Questions.corrects[2];
                        String correctFive = Questions.corrects[4];


                        progressDialog.dismiss();
                        btn1.setText(questionOne);
                        btn2.setText(questionTwo);
                        btn3.setText(questionThree);
                        btn4.setText(questionFour);
                        btn5.setText(questionFive);
                        btn6.setText(correctOne);
                        btn7.setText(correctTwo);
                        btn8.setText(correctThree);
                        btn9.setText(correctFour);
                        btn0.setText(correctFive);

                        btn1.setTextSize(8);
                        btn2.setTextSize(8);
                        btn3.setTextSize(8);
                        btn4.setTextSize(8);
                        btn5.setTextSize(8);
                        btn6.setTextSize(8);
                        btn7.setTextSize(8);
                        btn8.setTextSize(8);
                        btn9.setTextSize(8);
                        btn0.setTextSize(8);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());
                    Toast.makeText(Vocabulary_3_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Vocabulary_3_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("numberOfQuestions",numAle);
                params.put("lectionId", String.valueOf(lessonint2));
                params.put("sketch",boceto);
                params.put("typeName","Vocabulario");
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

    public static class Questions{
        public static String questions[] = new String[5];
        public static String corrects[] = new String[5];
    }

    public static Questions getQuestions(JSONArray questions){
        Questions newQuestions = null;

        for (int i = 0; i < questions.length();i++){
            try {
                JSONObject question = questions.getJSONObject(i);
                if (i==0){
                    newQuestions.questions[i] = question.getString("question");
                    newQuestions.corrects[i] = question.getString("correct");
                }
                if (i==1){
                    newQuestions.questions[i] = question.getString("question");
                    newQuestions.corrects[i] = question.getString("correct");
                }
                if (i==2){
                    newQuestions.questions[i] = question.getString("question");
                    newQuestions.corrects[i] = question.getString("correct");
                }
                if (i==3){
                    newQuestions.questions[i] = question.getString("question");
                    newQuestions.corrects[i] = question.getString("correct");
                }
                if (i==4){
                    newQuestions.questions[i] = question.getString("question");
                    newQuestions.corrects[i] = question.getString("correct");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return newQuestions;
    }

}
