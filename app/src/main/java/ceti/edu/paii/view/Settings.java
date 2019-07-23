package ceti.edu.paii.view;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

public class Settings extends AppCompatActivity {

    private static final String TAG = Settings.class.getSimpleName();//Toma la información
    private EditText name, email,username,appa,apma,tel,date,gender;
    SessionManager sessionManager;
    String getId;
    private static String URL_READ = "http://192.168.0.6/proyecto/read_detail.php";
    private static String URL_EDIT = "http://192.168.0.6/proyecto/edit_detail.php";

    private Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        showToolbar("Configuración", true);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        name = findViewById(R.id.name_settings);
        email = findViewById(R.id.email_settings);
        username = findViewById(R.id.UserName_settings);
        appa = findViewById(R.id.ap_pa_settings);
        apma = findViewById(R.id.ap_ma_settings);
        tel = findViewById(R.id.ap_tel_settings);
        date = findViewById(R.id.date_settings);
        gender = findViewById(R.id.gender_settings);

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

    }


    private void getUserDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strUserName = object.getString("userName").trim();
                                    String strApePat = object.getString("apePat").trim();
                                    String strApeMat = object.getString("apeMat").trim();
                                    String strTel = object.getString("phone").trim();
                                    String strNac = object.getString("nacimiento").trim();
                                    String strGender = object.getString("gender").trim();

                                    name.setText(strName);
                                    email.setText(strEmail);
                                    username.setText(strUserName);
                                    appa.setText(strApePat);
                                    apma.setText(strApeMat);
                                    tel.setText(strTel);
                                    date.setText(strNac);
                                    gender.setText(strGender);

                                    if(strGender.equals("0")){
                                        gender.setText("Hombre");
                                    }else if(strGender.equals("1")){
                                        gender.setText("Mujer");
                                    }

                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(Settings.this, "Error reading dialog: " + e.toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Settings.this, "Error reading dialog: " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);


        name.setFocusable(false);
        email.setFocusable(false);
        username.setFocusable(false);
        appa.setFocusable(false);
        apma.setFocusable(false);
        tel.setFocusable(false);
        date.setFocusable(false);
        gender.setFocusable(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:

                name.setFocusable(false);
                email.setFocusable(false);
                username.setFocusableInTouchMode(true);
                appa.setFocusableInTouchMode(true);
                apma.setFocusableInTouchMode(true);
                tel.setFocusableInTouchMode(true);
                date.setFocusable(false);
                gender.setFocusable(false);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);
                return true;

            case R.id.menu_save:

                SaveEditDetail();

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                name.setFocusableInTouchMode(false);
                email.setFocusableInTouchMode(false);
                username.setFocusableInTouchMode(false);
                appa.setFocusableInTouchMode(false);
                apma.setFocusableInTouchMode(false);
                tel.setFocusableInTouchMode(false);
                date.setFocusableInTouchMode(false);
                gender.setFocusableInTouchMode(false);

                name.setFocusable(false);
                email.setFocusable(false);
                username.setFocusable(false);
                appa.setFocusable(false);
                apma.setFocusable(false);
                tel.setFocusable(false);
                date.setFocusable(false);
                gender.setFocusable(false);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void SaveEditDetail() {
        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String userName = this.username.getText().toString().trim();
        final String appa = this.appa.getText().toString().trim();
        final String apma = this.apma.getText().toString().trim();
        final String tel = this.tel.getText().toString().trim();
        comun.userName = name;

        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
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
                                Toast.makeText(Settings.this, "Exito!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(name, email, id);
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Toast.makeText(Settings.this, "Error del catch! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(Settings.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("id", id);
                params.put("userName",userName);
                params.put("appa",appa);
                params.put("apma",apma);
                params.put("phone",tel);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_set);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}