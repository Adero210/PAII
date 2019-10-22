package ceti.edu.paii.adapter;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import ceti.edu.paii.R;
import ceti.edu.paii.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private List<Message> mMessagesList;
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
        public TextView messageText, messagesent;
        public CircleImageView profileImage;

        public MessageViewHolder(View view){
            super(view);

            messagesent = view.findViewById(R.id.text_message_body_sent);
            messageText = view.findViewById(R.id.text_message_body);
            profileImage = view.findViewById(R.id.image_message_profile);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int i) {

        mAuth = FirebaseAuth.getInstance();
        String current_user_id = mAuth.getCurrentUser().getUid();
        Message c = mMessagesList.get(i);

        String fromId = c.getFrom();
        if(fromId.equals(current_user_id)){

            messageViewHolder.messageText.setBackgroundColor(Color.WHITE);
            messageViewHolder.messageText.setTextColor(Color.BLACK);

        }else{
            messageViewHolder.messageText.setBackgroundResource(R.drawable.rounded_rentangle_orange);
            messageViewHolder.messageText.setTextColor(Color.WHITE);

        }

        messageViewHolder.messageText.setText(c.getMessage());
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }


}