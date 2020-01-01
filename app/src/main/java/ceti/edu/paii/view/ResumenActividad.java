package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.writing.Writing_1_Activity;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.fragment.HomeFragment;

public class ResumenActividad extends AppCompatActivity {

    private int cali;
    private String calis;

    private String curso;
    private String lesson;

    private TextView textCal;
    private TextView numCal;
    private String tipo;
    private static String URL_EDIT = comun.URL + "proyecto/update_cali.php";


    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_actividad);
        calis = getIntent().getStringExtra("calificacion");
        cali  = Integer.valueOf(calis);
        curso  = getIntent().getStringExtra("curso");
        lesson = getIntent().getStringExtra("lesson");
        tipo = getIntent().getStringExtra("tipo");
        textCal = findViewById(R.id.text_cal);
        numCal = findViewById(R.id.num_cal);
        back = findViewById(R.id.back);




        int lessonint = Integer.valueOf(lesson);

        int l = lessonint-1;

        if (curso.equals("Ingles")) {
            back.setText("back");
            textCal.setText("Score");

        } else if (curso.equals("Italiano")) {
            back.setText("ritorno");
            textCal.setText("punteggio");
        }

        Double cali2 = Double.valueOf((cali/80));
        String score = String.valueOf(cali2);

        Log.i("rederescrtedeca",score + tipo
                + String.valueOf(l)+ comun.userNameLec );

        updateCali(score,l);

        numCal.setText(score);


        opcBotones();
    }

    private void updateCali(final String score, final int l) {
        final ProgressDialog progressDialog = new ProgressDialog(ResumenActividad.this);
        progressDialog.setMessage("Guardando...");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                //   Toast.makeText(Settings.this, "Exito!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            // Toast.makeText(activity.this, "Error del catch! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        // Toast.makeText(Settings.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("score", score);
                params.put("tipo", tipo);
                params.put("lesson",String.valueOf(l));
                params.put("userName",comun.userNameLec);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ResumenActividad.this);
        requestQueue.add(stringRequest);
    }

    private void opcBotones() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResumenActividad.this, ContainerActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onBackPressed(){
        return;
    }
}
