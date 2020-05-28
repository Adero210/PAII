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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.protobuf.StringValue;

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

    private static String URL_READ = comun.URL + "getUserLections.php";


    private String id,id3;
    private String[] id2;
    private FirebaseAuth firebaseAuth;
    private String lessonName;
    public static String progresoLec1,progresoLec2,progresoLec3,progresoLec4,progresoLec5,progresoLec6,progresoLec7,
            progresoLec8,progresoLec9,progresoLec10;

    public static String calificacionLec1,calificacionLec2,calificacionLec3,calificacionLec4,calificacionLec5,
            calificacionLec6,calificacionLec7,calificacionLec8,calificacionLec9,calificacionLec10;

    private String strName;


    int cursoint = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);


        RecyclerView leccionesRecycler = (RecyclerView) findViewById(R.id.leccionRecycler);
        leccionesRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        leccionesRecycler.setLayoutManager(linearLayoutManager);

        id = comun.currentId;

        id2 = id.split("/");

        id3 = id2[4];

        lessonName = getDta();

        if (lessonName.equals("Inglés")){
            cursoint = 1;
            showToolbar("Lessons", true);
        }else if(lessonName.equals("Italiano")){
            cursoint = 2;
            showToolbar("Lezioni", true);
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
                            Log.i("ahhhha","entre al try");

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("status");
                            JSONArray jsonArray = jsonObject.getJSONArray("userLections");
                            if (success.equals("GOOD")) {
                                Log.i("ahhhha","success");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    JSONObject lection = object.getJSONObject("lection");

                                    strName = object.getString("nickname").trim();

                                    if(lection.getString("lectionNumber").equals("1")){
                                        calificacionLec1 = object.getString("calification");
                                        progresoLec1 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("2")){
                                        calificacionLec2 = object.getString("calification");
                                        progresoLec2 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("3")){
                                        calificacionLec3 = object.getString("calification");
                                        progresoLec3 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("4")){
                                        calificacionLec4 = object.getString("calification");
                                        progresoLec4 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("5")){
                                        calificacionLec5 = object.getString("calification");
                                        progresoLec5 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("6")){
                                        calificacionLec6 = object.getString("calification");
                                        progresoLec6 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("7")){
                                        calificacionLec7 = object.getString("calification");
                                        progresoLec7 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("8")){
                                        calificacionLec8 = object.getString("calification");
                                        progresoLec8 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("9")){
                                        calificacionLec9 = object.getString("calification");
                                        progresoLec9 = object.getString("progress");
                                    }
                                    if(lection.getString("lectionNumber").equals("10")){
                                        calificacionLec10 = object.getString("calification");
                                        progresoLec10 = object.getString("progress");
                                    }

                                }

                                LeccionAdapterRecyclerView leccionAdapterRecyclerView =
                                        new LeccionAdapterRecyclerView(buidLeccines(lessonName),R.layout.cardview_leccion_bottom,
                                                PictureDetailActivity.this);

                                leccionesRecycler.setAdapter(leccionAdapterRecyclerView);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();

                            Toast.makeText(PictureDetailActivity.this, "Error reading dialog1: " + e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(PictureDetailActivity.this, "Error reading dialog: " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firebaseId", id3);
                params.put("courseId", String.valueOf(cursoint));


                Log.i("pleasework",id3 + " + " +String.valueOf(cursoint));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(PictureDetailActivity.this);
        requestQueue.getCache().clear();
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


        if (r.equals("Inglés")){
            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "1", "English","Completed",progresoLec1+"%","score:",calificacionLec1));
            if(cal1 >= 80.0){
                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "2", "English","Completed",progresoLec2+"%","score:",calificacionLec2));
                if(cal2 >= 80.0) {
                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "3", "English", "Completed", progresoLec3+"%", "score:", calificacionLec3));
                    if (cal3 >= 80.0) {
                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "4", "English", "Completed", progresoLec4+"%", "score:", calificacionLec4));
                        if(cal4 >= 80.0) {
                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "5", "English", "Completed", progresoLec5+"%", "score:", calificacionLec5));
                            if(cal5 >= 80.0) {
                                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "6", "English", "Completed", progresoLec6+"%", "score:", calificacionLec6));
                                if (cal6 >= 80.0) {
                                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "7", "English", "Completed", progresoLec7+"%", "score:", calificacionLec7));
                                    if(cal7 >= 80.0) {
                                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "8", "English", "Completed", progresoLec8+"%", "score:", calificacionLec8));
                                        if (cal8 >= 80.0) {
                                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "9", "English", "Completed", progresoLec9+"%", "score:", calificacionLec9));
                                            if(cal9 >= 80.0) {
                                                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lesson", "10", "English", "Completed", progresoLec10+"%", "score:", calificacionLec10));
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
            if (cal1 >= 80.0) {
                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "2", r, "Completato", progresoLec2+"%", "tasso", calificacionLec2));
                if(cal2 >= 80.0) {
                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "3", r, "Completato", progresoLec3+"%", "tasso", calificacionLec3));
                    if(cal3 >= 80.0) {
                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "4", r, "Completato", progresoLec4+"%", "tasso", calificacionLec4));
                        if(cal4 >= 80.0) {
                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "5", r, "Completato", progresoLec5+"%", "tasso", calificacionLec5));
                            if(cal5 >= 80.0) {
                                lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "6", r, "Completato", progresoLec6+"%", "tasso", calificacionLec6));
                                if(cal6 >= 80.0) {
                                    lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "7", r, "Completato", progresoLec7+"%", "tasso", calificacionLec7));
                                    if(cal7 >= 80.0) {
                                        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "8", r, "Completato", progresoLec8+"%", "tasso", calificacionLec8));
                                        if (cal8 >= 80.0) {
                                            lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368", "Lezione", "9", r, "Completato", progresoLec9+"%", "tasso", calificacionLec9));
                                            if(cal9 >= 80.0) {
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
