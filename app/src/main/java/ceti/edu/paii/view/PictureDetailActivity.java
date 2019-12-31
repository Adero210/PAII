package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.LeccionAdapterRecyclerView;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.Leccion;
import ceti.edu.paii.model.Picture;


public class PictureDetailActivity extends AppCompatActivity {

    private static String URL_READ = comun.URL + "proyecto/read_leccion.php";

    private static String URL_EDIT = comun.URL + "proyecto/edit_leccion.php";


    private String id;
    private FirebaseAuth firebaseAuth;
    private String lessonName;
    private String progresoLec1,progresoLec2,progresoLec3,progresoLec4,progresoLec5,progresoLec6,progresoLec7,
            progresoLec8,progresoLec9,progresoLec10;

    private String calificacionLec1,calificacionLec2,calificacionLec3,calificacionLec4,calificacionLec5,
            calificacionLec6,calificacionLec7,calificacionLec8,calificacionLec9,calificacionLec10;

    private String strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("Lessons",true);

        RecyclerView leccionesRecycler = (RecyclerView) findViewById(R.id.leccionRecycler);
        leccionesRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        leccionesRecycler.setLayoutManager(linearLayoutManager);

        id = firebaseAuth.getInstance().getCurrentUser().getUid();

        lessonName = getDta();

        int cursoint = 0;
        if (lessonName.equals("Ingles")){
            cursoint = 0;
        }else if(lessonName.equals("Italiano")){
            cursoint = 1;
        }

        Log.i("ahhhha",lessonName);

        getUserDetail(leccionesRecycler,cursoint);
        
        



    }

    private void getUserDetail(final RecyclerView leccionesRecycler, final int cursoint) {

        final ProgressDialog progressDialog = new ProgressDialog(PictureDetailActivity.this);
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

                                    strName = object.getString("name").trim();

                                    calificacionLec1 = object.getString("calificacion0");
                                    calificacionLec2 = object.getString("calificacion1");
                                    calificacionLec3 = object.getString("calificacion2");
                                    calificacionLec4 = object.getString("calificacion3");
                                    calificacionLec5 = object.getString("calificacion4");
                                    calificacionLec6 = object.getString("calificacion5");
                                    calificacionLec7 = object.getString("calificacion6");
                                    calificacionLec8 = object.getString("calificacion7");
                                    calificacionLec9 = object.getString("calificacion8");
                                    calificacionLec10 = object.getString("calificacion9");

                                    progresoLec1 = object.getString("progreso0");
                                    progresoLec2 = object.getString("progreso1");
                                    progresoLec3 = object.getString("progreso2");
                                    progresoLec4 = object.getString("progreso3");
                                    progresoLec5 = object.getString("progreso4");
                                    progresoLec6 = object.getString("progreso5");
                                    progresoLec7 = object.getString("progreso6");
                                    progresoLec8 = object.getString("progreso7");
                                    progresoLec9 = object.getString("progreso8");
                                    progresoLec10 = object.getString("progreso9");


                                    LeccionAdapterRecyclerView leccionAdapterRecyclerView =
                                            new LeccionAdapterRecyclerView(buidLeccines(lessonName),R.layout.cardview_leccion_bottom,
                                                    PictureDetailActivity.this);

                                    leccionesRecycler.setAdapter(leccionAdapterRecyclerView);


                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();

                            createLessons();
                            //TO DO CREAR EL DIAGNOSTICO ______________________________________________________________
                            Intent i = new Intent(PictureDetailActivity.this,DiagnosticoExam.class);
                            startActivity(i);

                            //Toast.makeText(getContext(), "Error reading dialog: " + e.toString(), Toast.LENGTH_SHORT).show();

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
                params.put("id", id);
                params.put("idCurso", String.valueOf(cursoint));

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PictureDetailActivity.this);
        requestQueue.add(stringRequest);
    }

    private void createLessons() {
        final ProgressDialog progressDialog = new ProgressDialog(PictureDetailActivity.this);
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
                               // Toast.makeText(PictureDetailActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            // Toast.makeText(PictureDetailActivity.this, "Error del catch! " + e.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("name",comun.userNameLec);
                params.put("curseName",lessonName);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(PictureDetailActivity.this);
        requestQueue.add(stringRequest);
    }

    public ArrayList<Leccion> buidLeccines(String r){
        ArrayList<Leccion> lecciones = new ArrayList<>();

        double cal1 = Double.valueOf(calificacionLec1);
        double cal2 = Double.valueOf(calificacionLec2);
        double cal3 = Double.valueOf(calificacionLec3);
        double cal4 = Double.valueOf(calificacionLec4);
        double cal5 = Double.valueOf(calificacionLec5);
        double cal6 = Double.valueOf(calificacionLec6);
        double cal7 = Double.valueOf(calificacionLec7);
        double cal8 = Double.valueOf(calificacionLec8);
        double cal9 = Double.valueOf(calificacionLec9);


        if (r.equals("Ingles")){
            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "1", r,"Completed",progresoLec1+"%","Calif:",calificacionLec1));
            if(cal1>8.0){
                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "2", r,"Completed",progresoLec2+"%","Calif:",calificacionLec2));
                if(cal2>8.0) {
                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "3", r, "Completed", progresoLec3+"%", "Calif:", calificacionLec3));
                    if (cal3 > 8.0) {
                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "4", r, "Completed", progresoLec4+"%", "Calif:", calificacionLec4));
                        if(cal4>8.0) {
                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "5", r, "Completed", progresoLec5+"%", "Calif:", calificacionLec5));
                            if(cal5>8.0) {
                                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "6", r, "Completed", progresoLec6+"%", "Calif:", calificacionLec6));
                                if (cal6 > 8.0) {
                                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "7", r, "Completed", progresoLec7+"%", "Calif:", calificacionLec7));
                                    if(cal7>8.0) {
                                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "8", r, "Completed", progresoLec8+"%", "Calif:", calificacionLec8));
                                        if (cal8 > 8.0) {
                                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "9", r, "Completed", progresoLec9+"%", "Calif:", calificacionLec9));
                                            if(cal9>8.0) {
                                                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "10", r, "Completed", progresoLec10+"%", "Calif:", calificacionLec10));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }else if(r.equals("Italiano")) {
            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "1", r, "Completato", progresoLec1+"%", "tasso", calificacionLec1));
            if (cal1 > 8.0) {
                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "2", r, "Completato", progresoLec2+"%", "tasso", calificacionLec2));
                if(cal2>8.0) {
                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "3", r, "Completato", progresoLec3+"%", "tasso", calificacionLec3));
                    if(cal3>8.0) {
                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "4", r, "Completato", progresoLec4+"%", "tasso", calificacionLec4));
                        if(cal4>8.0) {
                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "5", r, "Completato", progresoLec5+"%", "tasso", calificacionLec5));
                            if(cal5>8.0) {
                                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "6", r, "Completato", progresoLec6+"%", "tasso", calificacionLec6));
                                if(cal6>8.0) {
                                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "7", r, "Completato", progresoLec7+"%", "tasso", calificacionLec7));
                                    if(cal7>8.0) {
                                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "8", r, "Completato", progresoLec8+"%", "tasso", calificacionLec8));
                                        if (cal8 > 8.0) {
                                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "9", r, "Completato", progresoLec9+"%", "tasso", calificacionLec9));
                                            if(cal9>8.0) {
                                                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "10", r, "Completato", progresoLec10+"%", "tasso", calificacionLec10));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return lecciones;
    }

    private String getDta(){
        String lessonName = "";
        if(getIntent().hasExtra("curse_name")){
             lessonName = getIntent().getStringExtra("curse_name");
        }
        return  lessonName;
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
