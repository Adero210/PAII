package ceti.edu.paii.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

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

    private EditText mChatedittext;

    private final List<Message> messagesList = new ArrayList<ceti.edu.paii.model.Message>();
    private LinearLayoutManager linearLayoutManager;

    private MessageListAdapter messageListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);

        mChatAddbtn = findViewById(R.id.addButton);
        mChatSendbtn = findViewById(R.id.sendButton);
        mChatedittext = findViewById(R.id.messageEditText);

        messageListAdapter = new MessageListAdapter(messagesList);

        mMessageList = findViewById(R.id.messages_list);


        linearLayoutManager = new LinearLayoutManager(this);

        mMessageList.setHasFixedSize(true);
        mMessageList.setLayoutManager(linearLayoutManager);


        mMessageList.setAdapter(messageListAdapter);





        mUserroot = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        mCurrentUserId = firebaseAuth.getCurrentUser().getUid();

        mChatUser = getIntent().getStringExtra("userId");
        userName = getIntent().getStringExtra("name");

        showToolbar(userName,true);




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


        mChatSendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });


        loadMessages();

    }

    private void loadMessages() {

        mUserroot.child("messages").child(mCurrentUserId).child(mChatUser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Message message= dataSnapshot.getValue(Message.class);

                messagesList.add(message);
                messageListAdapter.notifyDataSetChanged();

                mMessageList.scrollToPosition(messagesList.size()-1);

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
