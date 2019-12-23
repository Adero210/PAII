package ceti.edu.paii.activities.listening.writing;

import android.app.ProgressDialog;
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

public class Writing_3_Activity extends AppCompatActivity {

    private TextView length;
    int actHechas, cali;

    private ImageView ima1;
    private ImageView ima2;
    private ImageView ima3;
    private ImageView ima4;
    private  String numAletorio ="";

    private int numPre = 5;

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
    private int tam = 0;

    private LinearLayout contenedorBotones;
    private  String lesson ="";
    private String curso="";
    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/writing3Act.php";
    private String respuestaFromBD = "";
    private String respuestaUser="";
    private MediaPlayer mediaPlayer, incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_3_);


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

        curso = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        numAletorio = comun.aleatorio(numPre);


        if (curso.equals("Ingles")) {
            textimage.setText("Guess the word");
        } else if (curso.equals("Italiano")) {
            textimage.setText("Indovina la parola");
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
        opciones();

    }

    private void bringTheInfo(final Integer lessonint2, final String numAle) {

        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ACTR2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String numfilas = jsonObject.getString("filas");
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("actr2");

                    int numFilas = Integer.parseInt(numfilas);

                    if (success.equals("1")) {
                        progressDialog.dismiss();
                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);

                            for (int h = 0; h < numFilas; h++) {

                                respuestaFromBD = object.getString("respuestac").trim();
                                String imagenfrom1 = object.getString("urlImage0");
                                String imagenfrom2 = object.getString("urlImage1");
                                String imagenfrom3 = object.getString("urlImage2");
                                String imagenfrom4 = object.getString("urlImage3");

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

                            String revo = aleatorioLetras(tam-1);
                            String partOracRev = (respuestaFromBD + revo).trim();
                            String words[] = partOracRev.split("");

                            String[] r = new String[18];
                            // AleatoriSinRepeticion();
                            int pos, y = 1;
                            int nCartas = 18;
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
                                    + r[8]+ r[9]+ r[10]+ r[11]+ r[12]+ r[13]+ r[14]+ r[15]+ r[16]+ r[17]);
                            Log.i("aaaaaa", partOracRev);

                            btn1.setText(r[0]);
                            btn2.setText(r[1]);
                            btn3.setText(r[2]);
                            btn4.setText(r[3]);
                            btn5.setText(r[4]);
                            btn6.setText(r[5]);
                            btn7.setText(r[6]);
                            btn8.setText(r[7]);
                            btn9.setText(r[8]);
                            btn10.setText(r[9]);
                            btn11.setText(r[10]);
                            btn12.setText(r[11]);
                            btn13.setText(r[12]);
                            btn14.setText(r[13]);
                            btn15.setText(r[14]);
                            btn16.setText(r[15]);
                            btn17.setText(r[16]);
                            btn18.setText(r[17]);

                            agregarBotones(tam, contenedorBotones);

                        }
                    }


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
                params.put("pregunta", numAle);
                params.put("lesson", String.valueOf(lessonint2));
                params.put("type", "writing");
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
                    cali = cali + 100;

                    mediaPlayer.start();
                    if(curso.equals("Ingles")){
                        Toast.makeText(Writing_3_Activity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Writing_3_Activity.this,"corretta",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    incorrect.start();
                    cali = cali + 0;

                    if(curso.equals("Ingles")){
                        Toast.makeText(Writing_3_Activity.this,"wrong",Toast.LENGTH_SHORT).show();
                    }
                    else if(curso.equals("Italiano")){
                        Toast.makeText(Writing_3_Activity.this,"sbagliata",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn1.getText();
                int loop = 0;
                int i = 0;
                    do {
                        String btnID = String.valueOf(i);
                        int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                        Button txtObtenido = findViewById(resID);
                        String f = (String) txtObtenido.getText();
                        if (f.equals("")) {
                            txtObtenido.setText(vf);
                            loop = 1;
                        } else {
                            i++;
                        }
                    }while (loop == 0);
                }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn2.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn3.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn4.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn5.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn6.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn7.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn8.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn9.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn10.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn11.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn12.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn13.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn14.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn15.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn16.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn17.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vf = (String) btn18.getText();
                int loop = 0;
                int i = 0;
                do {
                    String btnID = String.valueOf(i);
                    int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                    Button txtObtenido = findViewById(resID);
                    String f = (String) txtObtenido.getText();
                    if (f.equals("")) {
                        txtObtenido.setText(vf);
                        loop = 1;
                    } else {
                        i++;
                    }
                }while (loop == 0);
            }
        });
    }

    private String aleatorioLetras(int tam){
        String oraAux = "";
        char c = '0';
        for (int i = 0; i < 18 - tam; i++){
            c = (char) (Math.random()*26 + 'a');
            oraAux = oraAux + Character.toString(c);
        }
        return oraAux;
    }

    /*@Override
    public void onBackPressed(){
        return;
    }*/

}
