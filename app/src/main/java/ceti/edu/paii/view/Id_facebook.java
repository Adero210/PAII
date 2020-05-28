package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.MainActivity;
import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;

public class Id_facebook extends AppCompatActivity {

    private Button next;
    private TextInputLayout nickname;
    private FirebaseAuth firebaseAuth;

    private String name, idUser, firebaseId,email,nicknameString;
    private DatabaseReference mDataBase;

    private static String URL_REGIST = comun.URL + "registerUserFacebook.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_facebook);
        nickname = findViewById(R.id.nickname_facebook);
        next = findViewById(R.id.next_face);

        name  = getIntent().getStringExtra("name");
        firebaseId = getIntent().getStringExtra("firebaseId");
        email = getIntent().getStringExtra("email");

        showToolbar("Registro Con Facebook", false);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nicknameString = nickname.getEditText().getText().toString().trim();
                FirebaseUser passu = FirebaseAuth.getInstance().getCurrentUser();
                String UID = passu.getUid();


                // String deviceToken = FirebaseInstanceId.getInstance().getToken();

                mDataBase = FirebaseDatabase.getInstance().getReference().child("user").child(UID);
                HashMap<String, String> userMap = new HashMap<>();
                //userMap.put("device_token",deviceToken);
                userMap.put("name",nicknameString);
                userMap.put("status","Hello! there");
                userMap.put("image","default");
                userMap.put("thumb_image","default");

                mDataBase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            idUser = FirebaseAuth.getInstance().getCurrentUser().getUid().trim();
                            // Toast.makeText(CreateAccountActivity.this, "TOAST" + cuser, Toast.LENGTH_LONG).show();
                            Log.d("cuser", idUser);

                            register();

                        }

                    }
                });
            }
        });
    }

    private void register() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating User");
        progressDialog.setMessage("Loading ...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean success = jsonObject.getBoolean("success");

                    if (success == true) {
                        progressDialog.dismiss();
                        Toast.makeText(Id_facebook.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Id_facebook.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("error1", e.toString());
                    Toast.makeText(Id_facebook.this, "Registro Fallido" + e.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Id_facebook.this, "Register error2" + error.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("helperror",error.toString());

                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firebaseId", firebaseId);
                params.put("nickname", nicknameString);
                params.put("email",email);
                params.put("name",name);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar =  findViewById(R.id.toolbar_login_face);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }


    @Override
    protected void onDestroy() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        return;
    }
}
