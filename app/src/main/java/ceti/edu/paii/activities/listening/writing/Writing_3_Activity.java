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
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
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
import ceti.edu.paii.activities.listening.Listening_1_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_1_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ResumenActividad;

public class Writing_3_Activity extends AppCompatActivity {

    private TextView length;


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

    private ImageView ima1;
    private ImageView ima2;
    private ImageView ima3;
    private ImageView ima4;
    private  String numAletorio;
    private TextView textimage;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;
    private Button btn16;
    private Button btn17;
    private Button btn18;

    private Button revisar;
    private Button continuar;
    public static int tam = 0;
    String boceto = "1";
    private LinearLayout contenedorBotones;

    private  String lesson;
    private String curso;
    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "getActivity.php";
    private String respuestaFromBD = "";
    private String respuestaUser="";
    private MediaPlayer mediaPlayer, incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_3_);

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

        if(actHechas<=8) {

            progressDialog = new ProgressDialog(Writing_3_Activity.this);

            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);

            mediaPlayer = MediaPlayer.create(this, R.raw.correctding);
            incorrect = MediaPlayer.create(this, R.raw.wrong);

            length = findViewById(R.id.lenght_num);
            ima1 = findViewById(R.id.image_1_writing_3);
            ima2 = findViewById(R.id.image_2_writing_3);
            ima3 = findViewById(R.id.image_3_writing_3);
            ima4 = findViewById(R.id.image_4_writing_3);

            contenedorBotones = findViewById(R.id.contenedor_botones);

            btn1 = findViewById(R.id.char_1_writing_3);
            btn2 = findViewById(R.id.char_2_writing_3);
            btn3 = findViewById(R.id.char_3_writing_3);
            btn4 = findViewById(R.id.char_4_writing_3);
            btn5 = findViewById(R.id.char_5_writing_3);
            btn6 = findViewById(R.id.char_6_writing_3);
            btn7 = findViewById(R.id.char_7_writing_3);
            btn8 = findViewById(R.id.char_8_writing_3);
            btn9 = findViewById(R.id.char_9_writing_3);
            btn10 = findViewById(R.id.char_10_writing_3);
            btn11 = findViewById(R.id.char_11_writing_3);
            btn12 = findViewById(R.id.char_12_writing_3);
            btn13 = findViewById(R.id.char_13_writing_3);
            btn14 = findViewById(R.id.char_14_writing_3);
            btn15 = findViewById(R.id.char_15_writing_3);
            btn16 = findViewById(R.id.char_16_writing_3);
            btn17 = findViewById(R.id.char_17_writing_3);
            btn18 = findViewById(R.id.char_18_writing_3);

            textimage = findViewById(R.id.textview_1_activity_writing_3);

            revisar = findViewById(R.id.button_activity_Writing_3);
            continuar = findViewById(R.id.button_continuar_activity_writing_3);

            numAletorio = "1";

            if (curso.equals("English")) {
                textimage.setText("Guess the word");
                bto = "Continue";
                cWord = "Correct!";
                wWord = "Wrong: ";
            } else if (curso.equals("Italiano")) {
                textimage.setText("Indovina la parola");
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

            calificacion =  new ProgressDialog(Writing_3_Activity.this);
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
                            Intent i = new Intent(Writing_3_Activity.this, Writing_1_Activity.class);
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
                            Intent intent = new Intent(Writing_3_Activity.this, Writing_2_Activity.class);
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
                            Intent intent1 = new Intent(Writing_3_Activity.this, Writing_3_Activity.class);
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
            opciones();


        }else {
            Intent i = new Intent(Writing_3_Activity.this, ResumenActividad.class);
            String tipo = "Escritura";
            i.putExtra("tipo",tipo);
            i.putExtra("curso",curso);
            i.putExtra("lesson",lesson);
            i.putExtra("calificacion", String.valueOf(cali));
            startActivity(i);

        }

    }

    private void bringTheInfo(final Integer lessonint2, final String numAle) {

        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ACTR2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Log.i("ahhhha","entre al try");

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    JSONArray jsonArray = jsonObject.getJSONArray("questions");

                    if (success.equals("GOOD")) {
                        Log.i("ahhhha","success");
                        JSONObject object = jsonArray.getJSONObject(0);
                        respuestaFromBD = object.getString("correct").trim();

                        JSONArray jsonObject1 = object.getJSONArray("images");

                        String imagenfrom1 = null;
                        String imagenfrom2 = null;
                        String imagenfrom3 = null;
                        String imagenfrom4 = null;

                        for(int i = 0; i <= cadid.length; i++){
                            String aux = respuestaFromBD;
                            if(respuestaFromBD.equals(cadid[i])) {

                                Intent in = new Intent(Writing_3_Activity.this, Writing_3_Activity.class);
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
                                    cadid[i] = respuestaFromBD;

                                    break;
                                }
                            }

                        }

                        Log.i("aaa",cadid[0]+cadid[1]);


                        progressDialog.dismiss();


                        for(int i = 0; i < jsonObject1.length(); i++){
                            JSONObject jsonObject2 = jsonObject1.getJSONObject(i);
                                JSONObject jsonObject3 = jsonObject2.getJSONObject("image");
                                if(i == 0) imagenfrom1 = jsonObject3.getString("imageRoute");
                                if(i == 1) imagenfrom2 = jsonObject3.getString("imageRoute");
                                if(i == 2) imagenfrom3 = jsonObject3.getString("imageRoute");
                                if(i == 3) imagenfrom4 = jsonObject3.getString("imageRoute");
                            }



                        tam = respuestaFromBD.length();

                        length.setText(String.valueOf(tam));

                        Glide.with(Writing_3_Activity.this)
                                .load(imagenfrom1)
                                .into(ima1);
                        Glide.with(Writing_3_Activity.this)
                                .load(imagenfrom2)
                                .into(ima2);
                        Glide.with(Writing_3_Activity.this)
                                .load(imagenfrom3)
                                .into(ima3);
                        Glide.with(Writing_3_Activity.this)
                                .load(imagenfrom4)
                                .into(ima4);

                    }

                    String revo = aleatorioLetras(tam);
                    String partOracRev = (respuestaFromBD + revo).trim();
                    String words[] = partOracRev.split("");

                    String[] r = new String[16];
                    // AleatoriSinRepeticion();
                    int pos, y = 1;
                    int nCartas = 16;
                    Stack<Integer> pCartas = new Stack<Integer>();
                    for (int x = 0; x < nCartas; x++) {
                        pos = (int) Math.floor(Math.random() * nCartas);
                        while (pCartas.contains(pos)) {
                            pos = (int) Math.floor(Math.random() * nCartas);
                        }
                        r[pos] = words[y];
                        pCartas.push(pos);
                        y = y + 1;
                    }
                    Log.i("Numeros", pCartas.toString());
                    Log.i("Numeros", words[1] + words[2] + words[3]);

                    Log.i("Numeros", r[0] + r[1] + r[2]+ r[3]+ r[4]+ r[5]+ r[6]+ r[7]
                            + r[8]+ r[9]+ r[10]+ r[11]+ r[12]+ r[13]+ r[14]+ r[15]);
                    Log.i("aaaaaa", partOracRev);

                    btn1.setText(r[0]);
                    btn1.setTextSize(9);
                    btn2.setText(r[1]);
                    btn2.setTextSize(9);
                    btn3.setText(r[2]);
                    btn3.setTextSize(9);
                    btn4.setText(r[3]);
                    btn4.setTextSize(9);
                    btn5.setText(r[4]);
                    btn6.setText(r[5]);
                    btn7.setText(r[6]);
                    btn8.setText(r[7]);
                    btn10.setText(r[8]);
                    btn11.setText(r[9]);
                    btn12.setText(r[10]);
                    btn13.setText(r[11]);
                    btn14.setText(r[12]);
                    btn15.setText(r[13]);
                    btn16.setText(r[14]);
                    btn17.setText(r[15]);

                    btn5.setTextSize(9);
                    btn6.setTextSize(9);
                    btn7.setTextSize(9);
                    btn8.setTextSize(9);
                    btn10.setTextSize(9);
                    btn11.setTextSize(9);
                    btn12.setTextSize(9);
                    btn13.setTextSize(9);
                    btn14.setTextSize(9);
                    btn15.setTextSize(9);
                    btn16.setTextSize(9);
                    btn17.setTextSize(9);

                    agregarBotones(tam, contenedorBotones);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Writing_3_Activity.this, "errorUNO" + e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Writing_3_Activity.this, "error" + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
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

    private void agregarBotones(int tam, LinearLayout contenedorBotones) {

        for (int i = 0; i < tam; i++) {
            Button button = new Button(getApplicationContext());
            button.setMinHeight(0);
            button.setPadding(0,0,0,0);
            button.setLayoutParams(new LinearLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT,1f));
            button.setId(i);
            button.setOnClickListener(miseventosButton);
            contenedorBotones.addView(button);
        }
    }

    private View.OnClickListener miseventosButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button objButon = (Button) v;
            objButon.setText("");
        }
    };

    private void opciones(){
        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revisar.setVisibility(View.INVISIBLE);
                continuar.setVisibility(View.VISIBLE);
                String[] arrTxt = new String[tam];
                for (int i = 0; i<tam;i++){
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = (Button) findViewById(resID);
                    //aqui pongo un array como ejemplo de donde guardar cada dato del formulario
                    arrTxt[i] = (String) txtObtenido.getText();
                    respuestaUser = respuestaUser + arrTxt[i];
                }

                Log.i("aaaaa",respuestaUser);
                if(respuestaUser.equals(respuestaFromBD)){
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
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn1.getText();
                functionBottom(vf);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn2.getText();
                functionBottom(vf);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn3.getText();
                functionBottom(vf);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn4.getText();
                functionBottom(vf);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn5.getText();
                functionBottom(vf);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn6.getText();
                functionBottom(vf);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn7.getText();
                functionBottom(vf);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn8.getText();
                functionBottom(vf);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn9.getText();
                functionBottom(vf);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn10.getText();
                functionBottom(vf);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn11.getText();
                functionBottom(vf);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn12.getText();
                functionBottom(vf);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn13.getText();
                functionBottom(vf);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn14.getText();
                functionBottom(vf);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn15.getText();
                functionBottom(vf);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn16.getText();
                functionBottom(vf);
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn17.getText();
                functionBottom(vf);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn18.getText();
                functionBottom(vf);
            }
        });



    }

    private void functionBottom(String vf){
        boolean loop = false;
        int i = 0;
            do {
                String btnID = String.valueOf(i);
                int resID = getResources().getIdentifier(btnID,
                        "id", getPackageName());
                Button txtObtenido = findViewById(resID);
                Log.i("sadsdasdada", String.valueOf(txtObtenido));
                String f = (String) txtObtenido.getText();
                if(txtObtenido != null) {
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = true;
                    }
                    i++;
                }else
                {
                    Toast.makeText(Writing_3_Activity.this,"full",Toast.LENGTH_SHORT).show();
                    loop = true;
                }
            } while (loop == false);
    }

    private String aleatorioLetras(int tam){
        String oraAux = "";
        char c = '0';
        for (int i = 0; i < 16 - tam; i++){
            c = (char) (Math.random()*26 + 'a');
            oraAux = oraAux + Character.toString(c);
        }
        return oraAux;
    }

    @Override
    public void onBackPressed(){
        return;
    }

}
