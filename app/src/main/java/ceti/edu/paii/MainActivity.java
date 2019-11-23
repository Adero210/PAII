package ceti.edu.paii;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.comun.comun;
import ceti.edu.paii.view.ContainerActivity;
import ceti.edu.paii.view.CreateAccountActivity;
import ceti.edu.paii.view.ResetPassword;
import ceti.edu.paii.view.SessionManager;
import ceti.edu.paii.view.Settings;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText userEmail;
    private TextInputLayout inpLuserName, inpLpassword;
    private EditText userPass;
    private Button userLogin,resetPassword;
    private LoginButton loginButtonFacebook;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference mUserDatabase;

    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager callbackManager;
    String mEmail, emailFaceBook,userNameFaceBook;
    private static final String URL_LOGIN= comun.URL+"proyecto/login2.php";
    private static final String URL_INSERT_FACE= comun.URL+"proyecto/registerFace.php";

    SessionManager sessionManager;
    String getId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miAsyncTask miAsyncTask = new miAsyncTask();
        miAsyncTask.execute(20);
        sessionManager = new SessionManager(this);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("user");

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        inpLuserName = findViewById(R.id.inpLuserName);
        inpLpassword = findViewById(R.id.inpLpassword);
        userEmail = findViewById(R.id.userName);
        userPass  = findViewById(R.id.password);
        userLogin = findViewById(R.id.login);
        resetPassword = findViewById(R.id.reset_login_password);
        loginButtonFacebook = findViewById(R.id.login_Facebook);
        loginButtonFacebook.setReadPermissions("email");

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    emailFaceBook = firebaseUser.getEmail();
                    userNameFaceBook = firebaseUser.getDisplayName();
                    String telface = firebaseUser.getPhoneNumber();
                    Log.i("Dataface",emailFaceBook + userNameFaceBook + telface);
                    getId = firebaseAuth.getUid();
                    Log.w(TAG, "USER LOGIN" + firebaseUser.getEmail());
                 //   insertDataFacebook();
                    goHome();
                } else {
                    Log.w(TAG, "USER no LOGIN");
                }
                }

            };


       userLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmInput();
            }

        });
       resetPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, ResetPassword.class));

           }
       });
        //-------------------------------------------CALLBACK FACEBOOK-----------------------------------------------------//
        loginButtonFacebook.setReadPermissions("email","public_profile");
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.w(TAG,"Facebook login success:" + loginResult.getAccessToken().getApplicationId());
                //handleFacebookAccessToken(loginResult.getAccessToken());
               signInFacebookFirabase(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.w(TAG,"Facebook login Cancel:");
            }

            @Override
            public void onError(FacebookException error) {
                Log.w(TAG,"Facebook login error:" + error.toString());
                error.printStackTrace();
            }

        });


    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "fallo",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void confirmInput(){

        if(!validateTuser()|!validatePass()){
            Toast.makeText(this,"Comprobar datos",Toast.LENGTH_SHORT).show();

        }else
        {
            mEmail = userEmail.getText().toString().trim();
            loginData(mEmail);
            login();
        }

    }
    private boolean validateTuser(){
        String Input = inpLuserName.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            inpLuserName.setError("El campo no debe estar vacio");
            return false;
        } else {
            inpLuserName.setError(null);
            return true;
        }
    }
    private boolean validatePass(){
        String Input = inpLpassword.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            inpLpassword.setError("El campo no debe estar vacio");
            return false;
        } else {
            inpLpassword.setError(null);
            return true;
        }
    }


    private void insertDataFacebook(){

        final String email = emailFaceBook;
        final String nickName = userNameFaceBook;
        final String idUser = getId;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Guardando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_INSERT_FACE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                               // Toast.makeText(MainActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(email, nickName, idUser);
                                comun.sessionManager = sessionManager;
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error del catch! " + e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("idUser", idUser);
                params.put("nickName",nickName);
                //params.put("phone",tel);
                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void loginData(final String email){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                        JSONObject jsonObject = new JSONObject(response);

                    //JSONArray jsonArray = new JSONArray(response);
                        //JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray = jsonObject.getJSONArray("login");
                        if(success.equals("1")){
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String name = object.getString("name").trim();
                                String email = object.getString("email").trim();
                                String id = object.getString("id").trim();


                                sessionManager.createSession(name,email,id);


                                //Toast.makeText(MainActivity.this, "Seccess Login." +
                                  //      "\nName: " + name + "\nemail: " + email, Toast.LENGTH_LONG).show();
                            }
                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("error",e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorResponse",error.toString());
                    }
                })
        {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("email",email);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void login() {
        String email  = userEmail.getText().toString().trim();
        String password = userPass.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            final FirebaseUser user = task.getResult().getUser();

                            String mCurrentUserId = firebaseAuth.getCurrentUser().getUid();

                            String deviceToken = FirebaseInstanceId.getInstance().getToken();

                            mUserDatabase.child(mCurrentUserId).child("device_token").setValue(deviceToken).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {


                                    SharedPreferences pref = getSharedPreferences("USER", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("email", user.getEmail());
                                    editor.commit();
                                    Toast.makeText(MainActivity.this,"LOGIN" ,Toast.LENGTH_SHORT).show();
                                    Intent intent  = new Intent(MainActivity.this,ContainerActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);



                                }
                            });


                        }else{
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void signInFacebookFirabase(AccessToken token) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();

                    SharedPreferences pref = getSharedPreferences("USER", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("email", user.getEmail());
                    editor.commit();

                   goHome();
                    Toast.makeText(MainActivity.this, "Login exitoso",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(MainActivity.this, "Login NO exitoso",Toast.LENGTH_SHORT);
                }
            }
        });
    }


    public void goCreateAccount(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void goHome(){
        Intent intent = new Intent(this,ContainerActivity.class);
        startActivity(intent);
        finish();
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
    protected void onActivityResult(final int requestCode,final int resultCode,final Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    class miAsyncTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected String doInBackground(Integer... integers) {
            int maximo = integers[0];
            for (int i =1;i<= maximo;i++ ){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                publishProgress(i);
            }

            return "fin de la ejecucion";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            int contador = values[0];
            String Texto = "contador" + contador;



        }

        @Override
        protected void onPostExecute(String s) {


        }

    }























}
