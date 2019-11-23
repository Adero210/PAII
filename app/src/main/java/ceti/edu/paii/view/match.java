package ceti.edu.paii.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ceti.edu.paii.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class match extends AppCompatActivity {


    public static final String TAG = AppCompatActivity.class.getSimpleName();
    private final String NONE = "none";
    private Button button;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mMatchmaker = database.getReference("matchmaker");
    DatabaseReference mGamesReference = database.getReference("games");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);


        findMatch();



        button = findViewById(R.id.button_match_activity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("games").push();
        String matchmaker = databaseReference.getKey();


        databaseReference.child(matchmaker).child(matchmaker).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        mGamesReference.child("matchmaker").removeValue();


        finish();
    }
    public void findMatch() {
        mMatchmaker.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String matchmaker = dataSnapshot.getValue(String.class);
                Log.d(TAG, "mMatchmaker: " + matchmaker);

                if (matchmaker == null) {
                    findMatchFirstArriver();
                } else {
                    findMatchSecondArriver(matchmaker);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void findMatchFirstArriver() {
        String matchmaker;
        final DatabaseReference dbReference = mGamesReference.push();
        dbReference.push().setValue("player0");
        matchmaker = dbReference.getKey();
        final String newMatchmaker = matchmaker;

        mMatchmaker.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue(String.class) == null) {
                    mutableData.setValue(newMatchmaker);
                    return Transaction.success(mutableData);
                }
                // someone beat us to posting a game, so fail and retry later
                return Transaction.abort();
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean commit, DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(),
                        commit ? "transaction success" : "transaction failed",
                        Toast.LENGTH_SHORT).show();
                if (!commit) {
                    // we failed to post the game, so destroy the game so we don't leave trash.
                    dbReference.removeValue();
                }
            }
        });
    }

    private void findMatchSecondArriver(final String matchmaker) {
        mMatchmaker.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue(String.class).equals(matchmaker)) {
                    mutableData.setValue(NONE);
                    return Transaction.success(mutableData);
                }
                // someone beat us to joining this game, so fail and retry later
                return Transaction.abort();
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed,
                                   DataSnapshot dataSnapshot) {
                if (committed) {
                    DatabaseReference gameReference = mGamesReference.child(matchmaker);
                    gameReference.push().setValue("player1");
                    mMatchmaker.setValue(NONE);
                }
            }
        });
    }

}

