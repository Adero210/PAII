package ceti.edu.paii.activities.listening.writing;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Writing_3_Activity extends AppCompatActivity {

    private TextView length;
    private ImageView ima1;
    private ImageView ima2;
    private ImageView ima3;
    private ImageView ima4;
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


    private ProgressDialog progressDialog;
    private static String URL_ACTR2 = comun.URL + "proyecto/writing3Act.php";
    private String respuestaFromBD = "";
    private String respuestaUser;
    private MediaPlayer mediaPlayer,incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_3_);


        progressDialog =  new ProgressDialog(Writing_3_Activity.this);

        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);

        mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
        incorrect = MediaPlayer.create(this,R.raw.wrong);

        length = findViewById(R.id.lenght_num);
        ima1 = findViewById(R.id.image_1_writing_3);
        ima2 = findViewById(R.id.image_2_writing_3);
        ima3 = findViewById(R.id.image_3_writing_3);
        ima4 = findViewById(R.id.image_4_writing_3);
        textimage = findViewById(R.id.editText_1_activity_writing_3);

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

        revisar = findViewById(R.id.button_activity_Writing_3);
        continuar = findViewById(R.id.button_continuar_activity_writing_3);

        String curso = getIntent().getStringExtra("curso");
        String lesson = getIntent().getStringExtra("lesson");

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

        bringTheInfo(lessonint - 1, "0");


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

                                String pregunta = object.getString("pregunta").trim();
                                respuestaFromBD = object.getString("respuestac");
                                String imagenfrom1 = object.getString("urlImage0");
                                String imagenfrom2 = object.getString("urlImage1");
                                String imagenfrom3 = object.getString("urlImage2");
                                String imagenfrom4 = object.getString("urlImage3");

                                int tam = respuestaFromBD.length();

                                length .setText(String.valueOf(tam));
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

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("DATAFROMSQL", "success" + e.toString());

                    // progressBar.setVisibility(View.GONE);

                    Toast.makeText(Writing_3_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Toast.makeText(Writing_3_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("pregunta",numAle);
                params.put("lesson", String.valueOf(lessonint2));
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

    /*@Override
    public void onBackPressed(){
        return;
    }*/

}
