package ceti.edu.paii.adapter;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import ceti.edu.paii.R;
import ceti.edu.paii.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private List<Message> mMessagesList;
    private DatabaseReference mMsgRef;

    private DatabaseReference mUserDatabase;

    private FirebaseAuth mAuth;


    public MessageListAdapter(List<Message> messageList) {
        this.mMessagesList = messageList;

    }



    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_message_received,viewGroup,false);

        MessageViewHolder b = new MessageViewHolder(v);
        return b;
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout leftMsgLayout;

        public LinearLayout rightMsgLayout;

        public TextView leftMsgTextView;

        public TextView rightMsgTextView;

        public TextView fromTime;

        public TextView toTime;


        public MessageViewHolder(View view){
            super(view);

            leftMsgLayout =  itemView.findViewById(R.id.chat_left_msg_layout);
            rightMsgLayout =  itemView.findViewById(R.id.chat_right_msg_layout);
            leftMsgTextView =  itemView.findViewById(R.id.chat_left_msg_text_view);
            rightMsgTextView =  itemView.findViewById(R.id.chat_right_msg_text_view);
            fromTime = itemView.findViewById(R.id.from_time);
            toTime = itemView.findViewById(R.id.to_time);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder messageViewHolder, int i) {

        mAuth = FirebaseAuth.getInstance();

        final String current_user_id = mAuth.getCurrentUser().getUid();

        final Message msgDto = mMessagesList.get(i);

        String from_user = msgDto.getFrom();
        final String message_type = msgDto.getType();


        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(from_user);


        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mMsgRef = FirebaseDatabase.getInstance().getReference().child("messages");

                String name = dataSnapshot.child("name").getValue().toString();
                String image = dataSnapshot.child("thumb_image").getValue().toString();


                if(dataSnapshot.getKey().equals(current_user_id))
                {

                    messageViewHolder.rightMsgLayout.setVisibility(LinearLayout.VISIBLE);

                    if(message_type.equals("text")) {

                        messageViewHolder.rightMsgTextView.setText(msgDto.getMessage());

                    }
                    messageViewHolder.leftMsgLayout.setVisibility(LinearLayout.GONE);
                }
                else
                {
                    messageViewHolder.leftMsgLayout.setVisibility(LinearLayout.VISIBLE);

                    if(message_type.equals("text")) {

                        messageViewHolder.leftMsgTextView.setText(msgDto.getMessage());

                    }
                    messageViewHolder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                }



//                viewHolder.displayName.setText(name);

//                Picasso.with(viewHolder.profileImage.getContext()).load(image)
//                        .placeholder(R.drawable.default_avatar).into(viewHolder.profileImage);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }


}