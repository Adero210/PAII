package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import ceti.edu.paii.MainActivity;
import ceti.edu.paii.R;

public class ResetPassword extends AppCompatActivity {

    private TextInputLayout emaillay;
    private EditText email;
    private Button mButton;
    private String emailc="";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        emaillay=findViewById(R.id.tilcorreo_reset);
        email = findViewById(R.id.email_reset);
        mButton = findViewById(R.id.reset);

        mAuth = FirebaseAuth.getInstance();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput();
            }
        });
    }

    public void confirmInput(){

        if(!validateTuser()){
            Toast.makeText(this,"Comprobar datos",Toast.LENGTH_SHORT).show();

        }else
        {
            emailc = email.getText().toString().trim();
            resetPassword();
        }

    }


    private boolean validateTuser(){
        String Input = emaillay.getEditText().getText().toString().trim();

        if (Input.isEmpty()) {
            emaillay.setError("El campo no debe estar vacio");
            return false;
        } else {
            emaillay.setError(null);
            return true;
        }
    }

    private void resetPassword(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(emailc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    Toast.makeText(ResetPassword.this,"Se ha enviado enviar el correo de reestablecer",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ResetPassword.this, MainActivity.class));

                }else{
                    Toast.makeText(ResetPassword.this,"No se pudo enviar el correo de reestablecer",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
