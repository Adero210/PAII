package ceti.edu.paii.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.arover.moment.Moment;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.MessageListAdapter;
import ceti.edu.paii.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.Url;

public class ChatActivity2 extends AppCompatActivity {

    private String mChatUser;
    private DatabaseReference mUserroot;
    private TextView mTitleView;
    private String userName;

    private CircleImageView mProfileIMage;
    private String mCurrentUserId;

    private FirebaseAuth firebaseAuth;

    private ImageButton mChatAddbtn;
    private ImageButton mChatSendbtn;

    private RecyclerView mMessageList;
    private SwipeRefreshLayout mRefreshLayout;

    private EditText mChatedittext;

    private static final int GALLERY_PICK = 1;

    private final List<Message> messagesList = new ArrayList<ceti.edu.paii.model.Message>();
    private LinearLayoutManager linearLayoutManager;

    private MessageListAdapter messageListAdapter;

    private FirebaseStorage storage;
    private StorageReference mImageStorage;

    private static final int TOTAL_ITEMS_TO_LOAD = 10;
    private int mCurrentPage = 1;

    private int itemPos = 0;
    private String mLastKey ="";
    private String mPrevKey = "";

    //Mensaje Audio Media Recorder -----------------------------------------------------------------

    private ImageButton mRocorderBtn;
    private MediaRecorder recorder;
    private String mFileName = null;
    private StorageReference mStorageAudio;
    private ProgressDialog progressDialog;

    final int REQUEST_PERMISSION_CODE = 1000;
    private String userImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        if (!checkPermissionFromDivice()) {
            requestPermission();
        }

        mChatAddbtn = findViewById(R.id.addButton);
        mChatSendbtn = findViewById(R.id.sendButton);
        mRocorderBtn = findViewById(R.id.audioButton);
        mChatedittext = findViewById(R.id.messageEditText);

        progressDialog = new ProgressDialog(this);
        messageListAdapter = new MessageListAdapter(messagesList);

        mMessageList = findViewById(R.id.messages_list);
        mRefreshLayout = findViewById(R.id.messages_swipe_layout);

        linearLayoutManager = new LinearLayoutManager(this);

        mMessageList.setHasFixedSize(true);
        mMessageList.setLayoutManager(linearLayoutManager);

        mMessageList.setAdapter(messageListAdapter);

        storage = FirebaseStorage.getInstance();
        mImageStorage = FirebaseStorage.getInstance().getReference();

        mStorageAudio = FirebaseStorage.getInstance().getReference();

        mUserroot = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        mCurrentUserId = firebaseAuth.getCurrentUser().getUid();

        mChatUser = getIntent().getStringExtra("userId");
        userImage = getIntent().getStringExtra("image");

        userName = getIntent().getStringExtra("name");

        mFileName = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/"
                + UUID.randomUUID().toString() + "_audio_record.mp3";

        showToolbar(userName,userImage,true);

        loadMessages();



        mUserroot.child("user").child(mChatUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.child("image").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mUserroot.child("Chat").child(mCurrentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(mChatUser)){

                    Map chatAddMap = new HashMap();
                    chatAddMap.put("seen",false);
                    chatAddMap.put("timestamp", ServerValue.TIMESTAMP);



                    Map chatUserMap = new HashMap();
                    chatUserMap.put("Chat/" + mCurrentUserId + "/" + mChatUser,chatAddMap);
                    chatUserMap.put("Chat/" + mChatUser + "/" + mCurrentUserId,chatAddMap);

                    mUserroot.updateChildren(chatUserMap, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                            if(databaseError != null){

                                Log.d("CHAT_LOG", databaseError.getMessage().toString());

                            }
                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRocorderBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    startRecording();

                }else if(event.getAction() == MotionEvent.ACTION_UP){

                    stopRecording();

                }
                return false;
            }
        });

        mChatAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_PICK);

            }
        });

        mChatSendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mCurrentPage++;

                itemPos = 0;
                loadMoreMessages();

            }
        });

        mProfileIMage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent profileIntent = new Intent(ChatActivity2.this, ProfileActivity.class);
                profileIntent.putExtra("userId",mChatUser);
                startActivity(profileIntent );

            }
        });
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(mFileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e("LOG_TAG_RECORD", "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording() {
       // recorder.stop();
        recorder.release();
        recorder = null;

        uploadAudio();

    }

    private void uploadAudio() {

        progressDialog.setMessage("Enviando Audio");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Uri uri = Uri.fromFile(new File(mFileName));


        final StorageReference filepath = mStorageAudio.child("Audio").child(uri.getLastPathSegment());



        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                progressDialog.dismiss();

                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uriAudio) {

                        final String Url_Audio = uriAudio.toString();
                        String current_user_ref = "messages/" + mCurrentUserId + "/" + mChatUser;
                        String chat_user_ref = "messages/" + mChatUser + "/" + mCurrentUserId;

                        DatabaseReference user_message_push = mUserroot.child("messages")
                                .child(mCurrentUserId).child(mChatUser).push();


                        Moment moment = new Moment();
                        String push_id = moment.toString();

                        Map messageMap = new HashMap();
                        messageMap.put("url",Url_Audio);
                        messageMap.put("type" , "audio");
                        messageMap.put("time", ServerValue.TIMESTAMP);
                        messageMap.put("from", mCurrentUserId);

                        Map messageUserMap = new HashMap();
                        messageUserMap.put(current_user_ref + "/" + push_id,messageMap);
                        messageUserMap.put(chat_user_ref + "/" + push_id,messageMap);

                        mUserroot.child("Chat").child(mCurrentUserId).child(mChatUser).child("seen").setValue(true);
                        mUserroot.child("Chat").child(mCurrentUserId).child(mChatUser).child("timestamp").setValue(ServerValue.TIMESTAMP);

                        mUserroot.child("Chat").child(mChatUser).child(mCurrentUserId).child("seen").setValue(false);
                        mUserroot.child("Chat").child(mChatUser).child(mCurrentUserId).child("timestamp").setValue(ServerValue.TIMESTAMP);

                        mUserroot.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                                if(databaseError != null){

                                    Log.d("CHAT_LOG", databaseError.getMessage().toString());

                                }
                            }
                        });
                    }
                });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_PICK && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();

            mImageStorage = storage.getReference("message_images");
            final StorageReference fotoreference = mImageStorage.child(imageUri.getLastPathSegment());


            fotoreference.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    fotoreference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.i("thumb_uri", String.valueOf(uri));

                            final String Url_download = uri.toString();
                            Log.i("thumb_uri", Url_download);

                            String current_user_ref = "messages/" + mCurrentUserId + "/" + mChatUser;
                            String chat_user_ref = "messages/" + mChatUser + "/" + mCurrentUserId;

                            DatabaseReference user_message_push = mUserroot.child("messages")
                                    .child(mCurrentUserId).child(mChatUser).push();

                            Moment moment = new Moment();
                            String push_id = moment.toString();

                            Map messageMap = new HashMap();
                            messageMap.put("message", "se envio una foto");
                            messageMap.put("seen",false);
                            messageMap.put("url",Url_download);
                            messageMap.put("type" , "image");
                            messageMap.put("time", ServerValue.TIMESTAMP);
                            messageMap.put("from", mCurrentUserId);

                            Map messageUserMap = new HashMap();
                            messageUserMap.put(current_user_ref + "/" + push_id,messageMap);
                            messageUserMap.put(chat_user_ref + "/" + push_id,messageMap);

                            mUserroot.child("Chat").child(mCurrentUserId).child(mChatUser).child("seen").setValue(true);
                            mUserroot.child("Chat").child(mCurrentUserId).child(mChatUser).child("timestamp").setValue(ServerValue.TIMESTAMP);

                            mUserroot.child("Chat").child(mChatUser).child(mCurrentUserId).child("seen").setValue(false);
                            mUserroot.child("Chat").child(mChatUser).child(mCurrentUserId).child("timestamp").setValue(ServerValue.TIMESTAMP);


                            mUserroot.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                                    if(databaseError != null){

                                        Log.d("CHAT_LOG", databaseError.getMessage().toString());

                                    }
                                }
                            });

                        }
                    });


                }
            });
        }

    }

    private void loadMoreMessages() {

        DatabaseReference messageReference = mUserroot.child("messages")
                .child(mCurrentUserId).child(mChatUser);



        Query messageQuery = messageReference.orderByKey().endAt(mLastKey).limitToLast(10);

        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message= dataSnapshot.getValue(Message.class);
                String messageKey = dataSnapshot.getKey();

                if(!mPrevKey.equals(messageKey)){

                    messagesList.add(itemPos++, message);

                } else {

                    mPrevKey = mLastKey;

                }


                if(itemPos == 1) {

                    mLastKey = messageKey;

                }


                Log.i("TOTALKEYS", "Last Key : " + mLastKey + " | Prev Key : " + mPrevKey + " | Message Key : " + messageKey);

                messageListAdapter.notifyDataSetChanged();


                mRefreshLayout.setRefreshing(false);

                linearLayoutManager.scrollToPositionWithOffset(10,0);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void loadMessages() {

        DatabaseReference messageReference = mUserroot.child("messages")
                .child(mCurrentUserId).child(mChatUser);
        Query messageQuery = messageReference.limitToLast(mCurrentPage + TOTAL_ITEMS_TO_LOAD);

        messageQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Message message= dataSnapshot.getValue(Message.class);

                itemPos++;

                if(itemPos == 1){
                    String messageKey = dataSnapshot.getKey();

                    mLastKey = messageKey;
                    mPrevKey = messageKey;

                }
                messagesList.add(message);
                messageListAdapter.notifyDataSetChanged();

                mMessageList.scrollToPosition(messagesList.size()-1);

                mRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendMessage() {

        String message = mChatedittext.getText().toString();

        if(!TextUtils.isEmpty(message)){

            String current_user_ref = "messages/" + mCurrentUserId + "/" + mChatUser;
            String chat_user_ref = "messages/" + mChatUser + "/" + mCurrentUserId;

            DatabaseReference user_message_push = mUserroot.child("messages")
                    .child(mCurrentUserId).child(mChatUser).push();

            Moment moment = new Moment();
            String push_id = moment.toString();

            Map messageMap = new HashMap();
            messageMap.put("message", message);
            messageMap.put("seen",false);
            messageMap.put("type" , "text");
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("from", mCurrentUserId);

            Map messageUserMap = new HashMap();
            messageUserMap.put(current_user_ref + "/" + push_id,messageMap);
            messageUserMap.put(chat_user_ref + "/" + push_id,messageMap);


            mChatedittext.setText("");
            mUserroot.child("Chat").child(mCurrentUserId).child(mChatUser).child("seen").setValue(true);
            mUserroot.child("Chat").child(mCurrentUserId).child(mChatUser).child("timestamp").setValue(ServerValue.TIMESTAMP);

            mUserroot.child("Chat").child(mChatUser).child(mCurrentUserId).child("seen").setValue(false);
            mUserroot.child("Chat").child(mChatUser).child(mCurrentUserId).child("timestamp").setValue(ServerValue.TIMESTAMP);


            mUserroot.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                    if(databaseError != null){

                        Log.d("CHAT_LOG", databaseError.getMessage().toString());

                    }
                }
            });

        }
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO

        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permisos dados",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Permisos NO dados",Toast.LENGTH_SHORT).show();
                }
            }
            break;

        }
    }
    private boolean checkPermissionFromDivice() {
        int write_external_storafe_result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);
        return write_external_storafe_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
    }

    public void showToolbar(String tittle,String image , boolean upButton) {
        android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarchat);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(upButton);
        actionbar.setDisplayShowCustomEnabled(upButton);
        actionbar.setTitle(tittle);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View action_bar_view = inflater.inflate(R.layout.chat_custom_bar,null);

        actionbar.setCustomView(action_bar_view);

        mTitleView = findViewById(R.id.custom_bar_);
        mProfileIMage = findViewById(R.id.custom_bar_image);

        Glide.with(ChatActivity2.this)
                .load(image)
                .placeholder(R.drawable.logo_transparent)
                .into(mProfileIMage);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}
