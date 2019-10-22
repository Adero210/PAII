package ceti.edu.paii;

import android.app.Application;
import android.support.annotation.NonNull;

import com.facebook.FacebookSdk;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class PaiiApplication extends Application {

    private DatabaseReference mUserDatabase;
    private FirebaseAuth mAuth;



    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getBaseContext());


        FirebaseDatabase.getInstance().setPersistenceEnabled(true); //Firebase offline capabilities

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

        mAuth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference()
                .child("user").child(mAuth.getCurrentUser().getUid());


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot != null) {
                    mUserDatabase.child("online").onDisconnect().setValue(false);
                    mUserDatabase.child("online").setValue(true);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
