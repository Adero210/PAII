package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ceti.edu.paii.R;
import ceti.edu.paii.view.fragment.ProfileFragment;

public class StatusActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private TextInputLayout mStatus;
    private Button mSavebtn;

    //Firebase
    private DatabaseReference mStatusDatabase;
    private FirebaseUser mCurrentUser;

    //Progress
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        //Firebase
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();

        mStatusDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(current_uid);
        //
        toolbar = findViewById(R.id.status_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Descripción");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String status_value = getIntent().getStringExtra("Descripción");

        mStatus  = findViewById(R.id.status_input);
        mSavebtn = findViewById(R.id.status_save_buttom);

        mStatus.getEditText().setText(status_value);

        mSavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Progress
                progressDialog = new ProgressDialog(StatusActivity.this);
                progressDialog.setTitle("Guardando Cambios");
                progressDialog.setMessage("Por favor espere...");
                progressDialog.show();

                String status = mStatus.getEditText().getText().toString();
                mStatusDatabase.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),
                                    "Se guardo Exitosamente",
                                    Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "Se obtuvo un error mientras guardaba",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }

        });


    }
}
