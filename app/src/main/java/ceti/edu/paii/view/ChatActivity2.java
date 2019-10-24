package ceti.edu.paii.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.MessageListAdapter;
import ceti.edu.paii.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;

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
    private StorageReference mImageStorage;



    private static final int TOTAL_ITEMS_TO_LOAD = 10;
    private int mCurrentPage = 1;


    private int itemPos = 0;
    private String mLastKey ="";
    private String mPrevKey = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        mChatAddbtn = findViewById(R.id.addButton);
        mChatSendbtn = findViewById(R.id.sendButton);
        mChatedittext = findViewById(R.id.messageEditText);

        messageListAdapter = new MessageListAdapter(messagesList);

        mMessageList = findViewById(R.id.messages_list);
        mRefreshLayout = findViewById(R.id.messages_swipe_layout);


        linearLayoutManager = new LinearLayoutManager(this);

        mMessageList.setHasFixedSize(true);
        mMessageList.setLayoutManager(linearLayoutManager);


        mMessageList.setAdapter(messageListAdapter);



        mImageStorage = FirebaseStorage.getInstance().getReference();


        mUserroot = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        mCurrentUserId = firebaseAuth.getCurrentUser().getUid();

        mChatUser = getIntent().getStringExtra("userId");
        userName = getIntent().getStringExtra("name");

        showToolbar(userName,true);


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




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_PICK && resultCode == RESULT_OK) {

            Uri imageUri = data.getData();


                if (resultCode == RESULT_OK) {


                    final String current_user_ref = "messages/" + mCurrentUserId + "/" + mChatUser;
                    final String chat_user_ref = "messages/" + mChatUser + "/" + mCurrentUserId;

                    DatabaseReference user_message_push = mUserroot.child("messages")
                            .child(mCurrentUserId).child(mChatUser).push();

                    final String push_id = user_message_push.getKey();


                    StorageReference filepath = mImageStorage.child("message_images").child(push_id + ".jpg");

                    final UploadTask uploadTask = filepath.putFile(imageUri);

                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String download_url = uri.toString();

                                    Map messageMap = new HashMap();
                                    messageMap.put("message", download_url);
                                    messageMap.put("seen", false);
                                    messageMap.put("type", "image");
                                    messageMap.put("time", ServerValue.TIMESTAMP);
                                    messageMap.put("from", mCurrentUserId);

                                    Map messageUserMap = new HashMap();
                                    messageUserMap.put(current_user_ref + "/" + push_id, messageMap);
                                    messageUserMap.put(chat_user_ref + "/" + push_id, messageMap);

                                    mChatedittext.setText("");

                                    mUserroot.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                                            if (databaseError != null) {

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


                Log.d("TOTALKEYS", "Last Key : " + mLastKey + " | Prev Key : " + mPrevKey + " | Message Key : " + messageKey);

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
        Query messageQuery = messageReference.limitToLast(mCurrentPage * TOTAL_ITEMS_TO_LOAD);

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

            String push_id = user_message_push.getKey();

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


    public void showToolbar(String tittle, boolean upButton) {
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

    }
}
