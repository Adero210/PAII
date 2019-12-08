package ceti.edu.paii.activities.listening.reading;

import android.app.ProgressDialog;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.awt.font.NumericShaper;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.stream.IntStream;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;

public class Reading_4_Activity extends AppCompatActivity {

    private TextView parrafo,word1,word2,word3,word4,word5;
    private MediaPlayer mediaPlayer,incorrect;

    private String palabrasCorrectas[] = new String[5];
    private ProgressDialog progressDialog;
    private static String URL_ACTR4 = comun.URL + "proyecto/genericAct.php";
    private String boceto = "1";
    private Button revisar, continuar;
    private String correcta = "";
    private int cont = 0;

    private String gre[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_4_);

        progressDialog =  new ProgressDialog(Reading_4_Activity.this);

        progressDialog.setCancelable(false);

        mediaPlayer = MediaPlayer.create(this,R.raw.correctding);
        incorrect = MediaPlayer.create(this,R.raw.wrong);

        parrafo = findViewById(R.id.parrafo_Reading_4);
        word1 = findViewById(R.id.word_1);
        word2 = findViewById(R.id.word_2);
        word3 = findViewById(R.id.word_3);
        word4 = findViewById(R.id.word_4);
        word5 = findViewById(R.id.word_5);
        revisar = findViewById(R.id.button_activity_Reading_4);
        continuar = findViewById(R.id.button_continuar_activity_Reading_4);


        continuar.setVisibility(View.GONE);
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


        brinTheInfo(lessonint - 1, "0");

        functiond();

    }


    private void brinTheInfo(final Integer lessonint2, final String numAle) {

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ACTR4, new Response.Listener<String>() {
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
                        for(int i = 0 ; i < jsonArray.length();i++) {

                            JSONObject object = jsonArray.getJSONObject(i);


                            for(int h = 0; h < numFilas; h++) {
                                String parrafo2 = object.getString("pregunta" + h);

                                correcta = object.getString("respuestac" + h);

                                Log.i("PARRAFOCORRECTO", correcta);

                                gre = correcta.split(",");

                                String words[] = parrafo2.split("~");


                                String part1 = words[1];
                                String part2 = words[3];
                                String part3 = words[5];
                                String part4 = words[7];
                                String part5 = words[9];


                                String ss = parrafo2.replace(words[1], "____");
                                String ssc2 = ss.replace(words[3], "____");
                                String ssc3 = ssc2.replace(words[5], "____");
                                String ssc4 = ssc3.replace(words[7], "____");
                                String ssc5 = ssc4.replace(words[9], "____");
                                ssc5 = ssc5.replaceAll("~", "");


                                String[] r = new String[5];
                                // AleatoriSinRepeticion();
                                int pos, y = 1;
                                int nCartas = 5;
                                Stack<Integer> pCartas = new Stack<Integer>();
                                for (int x = 0; x < nCartas; x++) {
                                    pos = (int) Math.floor(Math.random() * nCartas);
                                    while (pCartas.contains(pos)) {
                                        pos = (int) Math.floor(Math.random() * nCartas);
                                    }
                                    r[pos] = words[y];
                                    pCartas.push(pos);
                                    y = y + 2;
                                }
                                Log.i("Numeros", pCartas.toString());


                                final String partR1 = r[0];
                                String partR2 = r[1];
                                String partR3 = r[2];
                                String partR4 = r[3];
                                String partR5 = r[4];


                                Log.i("Partes1", part1);
                                Log.i("Partes2", part2);
                                Log.i("Partes3", part3);
                                Log.i("Partes4", part4);
                                Log.i("Partes4", part5);

                                Log.i("Partes1", partR1);
                                Log.i("Partes2", partR2);
                                Log.i("Partes3", partR3);
                                Log.i("Partes4", partR4);
                                Log.i("Partes4", partR5);


                                parrafo.setText(ssc5);


                                word1.setText(partR1);
                                word2.setText(partR2);
                                word3.setText(partR3);
                                word4.setText(partR4);
                                word5.setText(partR5);
                            }

                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                     Log.i("DATAFROMSQL", "error" + e.toString());
                    // progressBar.setVisibility(View.GONE);
                    Toast.makeText(Reading_4_Activity.this,"errorUNO" + e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  progressBar.setVisibility(View.GONE);

                        Log.i("DATAFROMSQL", "error" + error.toString());


                        Toast.makeText(Reading_4_Activity.this,"error" + error.toString(),Toast.LENGTH_SHORT).show();


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

    private void functiond() {

        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String auxParrafo = parrafo.getText().toString();

                String auxWord = word1.getText().toString();

                palabrasCorrectas[cont] = auxWord;
                cont++;

                Log.i("PARRAFO_WORD1", auxParrafo);

                String p[] = auxParrafo.split("____");

                String pauxParrafo = p[0];

                String auxp = auxParrafo.substring(p[0].length()+4);

                auxParrafo = pauxParrafo + auxWord + auxp;

                parrafo.setText(auxParrafo);

                word1.setVisibility(View.INVISIBLE);

            }
        });


        word2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String auxParrafo = parrafo.getText().toString();

                String auxWord = word2.getText().toString();

                palabrasCorrectas[cont] = auxWord;
                cont++;

                Log.i("PARRAFO_WORD1", auxParrafo);

                String p[] = auxParrafo.split("____");

                String pauxParrafo = p[0];

                String auxp = auxParrafo.substring(p[0].length()+4);

                auxParrafo = pauxParrafo + auxWord + auxp;

                parrafo.setText(auxParrafo);

                word2.setVisibility(View.INVISIBLE);
            }
        });

        word3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String auxParrafo = parrafo.getText().toString();

                String auxWord = word3.getText().toString();
                palabrasCorrectas[cont] = auxWord;
                cont++;

                Log.i("PARRAFO_WORD1", auxParrafo);

                String p[] = auxParrafo.split("____");

                String pauxParrafo = p[0];

                String auxp = auxParrafo.substring(p[0].length()+4);

                auxParrafo = pauxParrafo + auxWord + auxp;

                parrafo.setText(auxParrafo);

                word3.setVisibility(View.INVISIBLE);

            }
        });

        word4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String auxParrafo = parrafo.getText().toString();

                String auxWord = word4.getText().toString();
                palabrasCorrectas[cont] = auxWord;
                cont++;

                Log.i("PARRAFO_WORD1", auxParrafo);

                String p[] = auxParrafo.split("____");

                String pauxParrafo = p[0];

                String auxp = auxParrafo.substring(p[0].length()+4);

                auxParrafo = pauxParrafo + auxWord + auxp;

                parrafo.setText(auxParrafo);


                word4.setVisibility(View.INVISIBLE);

            }
        });

        word5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String auxParrafo = parrafo.getText().toString();

                String auxWord = word5.getText().toString();

                palabrasCorrectas[cont] = auxWord;
                cont++;

                Log.i("PARRAFO_WORD1", auxParrafo);

                String p[] = auxParrafo.split("____");

                String pauxParrafo = p[0];

                String auxp = auxParrafo.substring(p[0].length()+4);



                auxParrafo = pauxParrafo + auxWord + auxp;

                parrafo.setText(auxParrafo);


                word5.setVisibility(View.INVISIBLE);

            }
        });

        revisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                word1.setVisibility(View.INVISIBLE);
                word2.setVisibility(View.INVISIBLE);
                word3.setVisibility(View.INVISIBLE);
                word4.setVisibility(View.INVISIBLE);
                word5.setVisibility(View.INVISIBLE);


                if(gre[0].equals(palabrasCorrectas[0])&&gre[1].equals(palabrasCorrectas[1])&&gre[2].equals(palabrasCorrectas[2])
                &&gre[3].equals(palabrasCorrectas[3])&&gre[4].equals(palabrasCorrectas[4])){
                    Toast.makeText(Reading_4_Activity.this,"CORRECT ",Toast.LENGTH_SHORT).show();
                    revisar.setVisibility(View.GONE);
                    continuar.setVisibility(View.VISIBLE);
                    mediaPlayer.start();

                }else{
                    revisar.setVisibility(View.GONE);
                    continuar.setVisibility(View.VISIBLE);
                    incorrect.start();
                }

            }
        });

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
