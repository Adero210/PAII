package ceti.edu.paii.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.R;

public class ProfileActivity extends AppCompatActivity {

    private ImageView mProfileImageView;
    private TextView mProfileName, mProfileStatus;
    private Button requestbtn, declinebtn;
    private DatabaseReference mUsersDatabase;
    private DatabaseReference mFriendRequestDatabase;
    private DatabaseReference mFriendDatabase;
    private DatabaseReference mRootRef;
    private DatabaseReference mNotificationDatabase;
    private ProgressDialog mProgressDialog;
    private FirebaseUser mCurrentUser;
    private String mCurrent_state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final String user_id = getIntent().getStringExtra("userId");

        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(user_id);
        mUsersDatabase.keepSynced(true);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mFriendRequestDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_req");
        mFriendDatabase = FirebaseDatabase.getInstance().getReference().child("Friends");
        mNotificationDatabase = FirebaseDatabase.getInstance().getReference().child("notification");
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();

        mProfileImageView = findViewById(R.id.profile_display_image);
        mProfileName = findViewById(R.id.profile_display_name);
        mProfileStatus = findViewById(R.id.profile_display_status);
        requestbtn = findViewById(R.id.profile_display_request);
        declinebtn = findViewById(R.id.profile_display_Rechazar);
        mCurrent_state = "not_friends";

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(R.string.CargandoUserData);
        mProgressDialog.setMessage("Please wait while we load the user data.");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        if(user_id.equals(mCurrentUser.getUid())){
            requestbtn.setVisibility(View.INVISIBLE);
            declinebtn.setVisibility(View.INVISIBLE);
            declinebtn.setEnabled(false);
        }

        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String display_name = dataSnapshot.child("name").getValue().toString();
                String status = dataSnapshot.child("status").getValue().toString();
                final String image = dataSnapshot.child("image").getValue().toString();

                mProfileName.setText(display_name);
                mProfileStatus.setText(status);

                Picasso.with(ProfileActivity.this).load(image).networkPolicy(NetworkPolicy.OFFLINE)
                        .placeholder(R.drawable.logo).into(mProfileImageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                        Picasso.with(ProfileActivity.this).load(image).placeholder(R.drawable.logo).into(mProfileImageView);
                    }
                });

                declinebtn.setVisibility(View.INVISIBLE);
                declinebtn.setEnabled(false);
                //------------------------FRIENDS LIST / REQUEST FEATURE-------------------
                mFriendRequestDatabase.child(mCurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.hasChild(user_id)){

                            String req_type = dataSnapshot.child(user_id).child("request_type")
                                    .getValue().toString();
                            if (req_type.equals("received")){

                                mCurrent_state ="req_received";
                                 requestbtn.setText(R.string.Aceptarsolicitud1);

                                declinebtn.setVisibility(View.VISIBLE);
                                declinebtn.setEnabled(true);

                            }else if(req_type.equals("sent")){
                                mCurrent_state = "req_sent";
                                requestbtn.setText(R.string.Cancelarsolicitud1);

                                declinebtn.setVisibility(View.INVISIBLE);
                                declinebtn.setEnabled(false);

                            }
                            mProgressDialog.dismiss();
                        }else {

                            mFriendDatabase.child(mCurrentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    if(dataSnapshot.hasChild(user_id)){

                                        mCurrent_state ="Friends";
                                        requestbtn.setText("Eliminar Amigo");

                                        declinebtn.setVisibility(View.INVISIBLE);
                                        declinebtn.setEnabled(false);
                                    }
                                    mProgressDialog.dismiss();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    mProgressDialog.dismiss();
                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        requestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestbtn.setEnabled(false);
                //-------------------------- NOT FRIEND STATE -----------------------------//
                if(mCurrent_state.equals("not_friends")){


                    DatabaseReference newNotificationref = mRootRef.child("notification").child(user_id).push();
                    String newNotificationId = newNotificationref.getKey();

                    HashMap<String, String> notificationData = new HashMap<>();
                    notificationData.put("from",mCurrentUser.getUid());
                    notificationData.put("type","request");

                    Map requestMap = new HashMap();
                    requestMap.put("Friend_req/" + mCurrentUser.getUid() + "/" + user_id + "/request_type","sent");
                    requestMap.put("Friend_req/" + user_id + "/" + mCurrentUser.getUid() + "/request_type","received");
                    requestMap.put("notification/" + user_id + "/" + newNotificationId, notificationData);


                    mRootRef.updateChildren(requestMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                            if (databaseError != null) {
                                Toast.makeText(ProfileActivity.this, "there was an error ", Toast.LENGTH_SHORT).show();

                            }

                            requestbtn.setEnabled(true);
                            mCurrent_state ="req_sent";
                            requestbtn.setText(R.string.Cancelarsolicitud1);
                            declinebtn.setVisibility(View.INVISIBLE);
                            declinebtn.setEnabled(false);

                        }

                    });

                     }
                //------------------------- CANCEL REQUEST STATE -----------------------------//
                if(mCurrent_state.equals("req_sent")){
                    mFriendRequestDatabase.child(mCurrentUser.getUid()).child(user_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mFriendRequestDatabase.child(user_id).child(mCurrentUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    requestbtn.setEnabled(true);
                                    mCurrent_state ="not_friends";
                                    requestbtn.setText(R.string.Enviar);

                                    declinebtn.setVisibility(View.INVISIBLE);
                                    declinebtn.setEnabled(false);

                                }
                            });
                        }
                    });
                }
                //---------------------REQUEST RECEIVED STATE ------------------------------
                if(mCurrent_state.equals("req_received")){

                    final String currentDate = DateFormat.getDateTimeInstance().format(new Date());
                    Map friendMap = new HashMap();
                    friendMap.put("Friends/" + mCurrentUser.getUid() + "/" + user_id + "/date" , currentDate);
                    friendMap.put("Friends/" + user_id + "/" + mCurrentUser.getUid() + "/date" , currentDate);

                    friendMap.put("Friend_req/" + mCurrentUser.getUid() + "/" + user_id,null);
                    friendMap.put("Friend_req/" + user_id + "/" + mCurrentUser.getUid(),null);

                    mRootRef.updateChildren(friendMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                            if(databaseError == null){

                                requestbtn.setEnabled(true);
                                mCurrent_state ="Friends";
                                requestbtn.setText("Eliminar Amigo");
                                declinebtn.setVisibility(View.INVISIBLE);
                                declinebtn.setEnabled(false);
                            }else{
                                String error = databaseError.getMessage();
                                Toast.makeText(ProfileActivity.this, "there was an error " + error, Toast.LENGTH_SHORT).show();


                            }

                        }
                    });


                }
                //---------------------------------Eliminar Amigo-----------------------------------
                if(mCurrent_state.equals("Friends")){

                    Map unFriendMap = new HashMap();
                    unFriendMap.put("Friends/" + mCurrentUser.getUid() + "/" + user_id,null);
                    unFriendMap.put("Friends/" + user_id + "/" + mCurrentUser.getUid(),null);
                    mRootRef.updateChildren(unFriendMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                            if(databaseError == null){


                                mCurrent_state ="not_friends";
                                requestbtn.setText(R.string.Enviar);

                                declinebtn.setVisibility(View.INVISIBLE);
                                declinebtn.setEnabled(false);
                            }else{
                                String error = databaseError.getMessage();
                                Toast.makeText(ProfileActivity.this, "there was an error " + error, Toast.LENGTH_SHORT).show();


                            }

                            requestbtn.setEnabled(true);

                        }
                    });


                }

            }
        });

        declinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFriendRequestDatabase.child(mCurrentUser.getUid()).child(user_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        mFriendRequestDatabase.child(user_id).child(mCurrentUser.getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                requestbtn.setEnabled(true);
                                mCurrent_state ="not_friends";
                                requestbtn.setText(R.string.Enviar);

                                declinebtn.setVisibility(View.INVISIBLE);
                                declinebtn.setEnabled(false);

                            }
                        });
                    }
                });
            }
        });
    }
}
