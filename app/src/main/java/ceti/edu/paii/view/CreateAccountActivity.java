package ceti.edu.paii.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.MainActivity;
import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;

public class CreateAccountActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private String idUser, gender;
    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;

    RadioGroup radioGroup;
    private Button btnJoinUs;
    private TextInputEditText edtEmail, edtcPassword, edtPassword, edtbirt, edtapp, edtmpp, edtnick, edtnombre;
    private TextInputLayout tilEmail, tilcpass, tilPassword, tilbirt, tlapp, tilmpp, tilnick, tilnombre;
    private Log log;
    private static String URL_REGIST = comun.URL + "registerUser.php";

    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_tittle_createaccount), true);


        radioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        edtnick = (TextInputEditText) findViewById(R.id.nickName);

        edtbirt = (TextInputEditText) findViewById(R.id.birthdate);
        edtapp = (TextInputEditText) findViewById(R.id.midlename);
        edtmpp = (TextInputEditText) findViewById(R.id.lastName);
        edtnombre = (TextInputEditText) findViewById(R.id.name);
        edtEmail = (TextInputEditText) findViewById(R.id.email);
        edtPassword = (TextInputEditText) findViewById(R.id.password_createaccount);
        edtcPassword = (TextInputEditText) findViewById(R.id.confirmPassword);
        btnJoinUs = (Button) findViewById(R.id.joinUs);

        tilnick = (TextInputLayout) findViewById(R.id.inpT_nickName);
        tilbirt = (TextInputLayout) findViewById(R.id.inpT_birth);
        tlapp = (TextInputLayout) findViewById(R.id.inpT_apepat);
        tilmpp = (TextInputLayout) findViewById(R.id.inpT_apemat);
        tilcpass = (TextInputLayout) findViewById(R.id.inpT_confirmPassword);
        tilnombre = (TextInputLayout) findViewById(R.id.inpT_name);
        tilEmail = (TextInputLayout) findViewById(R.id.inpT_email);
        tilPassword = (TextInputLayout) findViewById(R.id.inpT_password);

        radioGroup.setOnCheckedChangeListener(this);


        firebaseAuth = FirebaseAuth.getInstance();


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "USER LOGIN" + firebaseUser.getEmail());

                } else {
                    Log.w(TAG, "USER no LOGIN");


                }
            }
        };




        btnJoinUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmInput();
            }
        });
    }

    public void confirmInput(){

        if(!validateNickName()|!validateemail()|!validateName()|!validateapp()|!validateapm()
        |!validateDate()|!validatePass()|!cvalidatePass()){
            Toast.makeText(this,"Comprobar datos",Toast.LENGTH_SHORT).show();
        }else
        {
            createAccount();
        }

    }

    private boolean validateNickName() {
        String nickInput = tilnick.getEditText().getText().toString().trim();

        if (nickInput.isEmpty()) {
            tilnick.setError("El campo no debe estar vacio");
            return false;
        } else if (nickInput.length() > 50) {
            tilnick.setError("El campo contiene demasiados caracteres");
            return false;
        } else {
            tilnick.setError(null);
            return true;
        }

    }
    private boolean validateemail() {
        String emailInput = tilEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            tilEmail.setError("El campo no debe estar vacio");
            return false;
        } else if (emailInput.length() > 50) {
            tilEmail.setError("El campo contiene demasiados caracteres");
            return false;
        } else {
            tilEmail.setError(null);
            return true;
        }
    }
    private boolean validateName() {
        String Input = tilnombre.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            tilnombre.setError("El campo no debe estar vacio");
            return false;
        } else if (Input.length() > 50) {
            tilnombre.setError("El campo contiene demasiados caracteres");
            return false;
        } else {
            tilnombre.setError(null);
            return true;
        }
    }
    private boolean validateapp() {
        String Input = tlapp.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            tlapp.setError("El campo no debe estar vacio");
            return false;
        } else if (Input.length() > 50) {
            tlapp.setError("El campo contiene demasiados caracteres");
            return false;
        } else {
            tlapp.setError(null);
            return true;
        }
    }
    private boolean validateapm(){
        String Input = tilmpp.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            tilmpp.setError("El campo no debe estar vacio");
            return false;
        } else if (Input.length() > 50) {
            tilmpp.setError("El campo contiene demasiados caracteres");
            return false;
        } else {
            tilmpp.setError(null);
            return true;
        }
    }

    private boolean validateDate(){
        String Input = tilbirt.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            tilbirt.setError("El campo no debe estar vacio");
            return false;
        } else if (Input.length() > 10) {
            tilbirt.setError("El campo contiene demasiados caracteres");
            return false;
        } else {
            tilbirt.setError(null);
            return true;
        }
    }
    private boolean validatePass(){
        String Input = tilPassword.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            tilPassword.setError("El campo no debe estar vacio");
            return false;
        } else if (Input.length() < 8) {
            tilPassword.setError("El campo debe contener 8 o más caracteres");
            return false;
        }else if(!checkPasswordNum()){
            tilPassword.setError("El campo debe contener números");
            return false;

        }
        else {
            tilPassword.setError(null);
            return true;
        }
    }

    private boolean cvalidatePass(){
        String Input = tilPassword.getEditText().getText().toString().trim();
        String Input2 = tilcpass.getEditText().getText().toString().trim();

        if (Input2.isEmpty()) {
            tilcpass.setError("El campo no debe estar vacio");
            return false;
        }else if(!Input2.equals(Input)){
            tilcpass.setError("No coincide");
            return false;
        }
        else {
            tilcpass.setError(null);
            return true;
        }
    }

    private boolean checkPasswordNum(){
        String pass = tilPassword.getEditText().getText().toString().trim();
        String parts[] = pass.split("");
        int cont = 0;
        for(int i = 0; i < pass.length();i++){
            if(parts[i].equals("0")|parts[i].equals("1")|parts[i].equals("2")|parts[i].equals("3")
            |parts[i].equals("4")|parts[i].equals("5")|parts[i].equals("6")|parts[i].equals("7")
            |parts[i].equals("8")|parts[i].equals("9")){
                cont++;
            }
        }
        if(cont>=1){
            return true;
        }else {
            return false;
        }
    }

    private void createAccount() {
        final String name = edtnick.getText().toString().trim();
        final String email = edtEmail.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser passu = FirebaseAuth.getInstance().getCurrentUser();
                            String UID = passu.getUid();


                            // String deviceToken = FirebaseInstanceId.getInstance().getToken();

                            mDataBase = FirebaseDatabase.getInstance().getReference().child("user").child(UID);
                            HashMap<String, String> userMap = new HashMap<>();
                            //userMap.put("device_token",deviceToken);
                            userMap.put("name",name);
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
                                        regist();

                                    }

                                }
                            });
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Cuenta No creada", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void regist() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating User");
        progressDialog.setMessage("Loading ...");
        progressDialog.show();


        final String email = edtEmail.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();
        final String name = edtnombre.getText().toString().trim();
        final String apepat = edtapp.getText().toString().trim();
        final String apemat = edtmpp.getText().toString().trim();
        final String nickname = edtnick.getText().toString().trim();
        final String nacimiento = edtbirt.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean success = jsonObject.getBoolean("success");

                    if (success == true) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateAccountActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("error1", e.toString());
                    Toast.makeText(CreateAccountActivity.this, "Registro Fallido" + e.toString(), Toast.LENGTH_SHORT).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CreateAccountActivity.this, "Register error2" + error.toString(), Toast.LENGTH_SHORT).show();
                        Log.i("helperror",error.toString());

                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("lastname1", apepat);
                params.put("lastname2", apemat);
                params.put("nickname", nickname);
                params.put("bdate", nacimiento);
                params.put("idUser", idUser);
                params.put("sex",gender);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){

            case R.id.radio_btn_female:
                gender = "1";
                break;
            case R.id.radio_btn_male:
                gender = "2";
                break;
            default:
                gender="0";
                break;


        }

    }
}
