package ceti.edu.paii.adapter;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.speaking.Speaking_1_Activity;
import ceti.edu.paii.model.Message;

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

        //Layouts mensajes escritos
        public LinearLayout leftMsgLayout;
        public LinearLayout rightMsgLayout;

        public TextView leftMsgTextView;

        public TextView rightMsgTextView;

        public TextView fromTime;

        public TextView toTime;

        //Layouts mensajes imagenes

        public LinearLayout leftMsgImgLayout;
        public LinearLayout rightMsgImgLayout;

        public ImageView fotomensaje;
        public ImageView fotomensaje2;

        public TextView fromTimeimg;
        public TextView toTimeimg;

        //Layouts mensajes Audio

        public LinearLayout MsgAudioLayout;
        public LinearLayout MsgAudioLayoutleft;

        public ImageButton playAudio;
        public ImageButton playAudioleft;

        public MediaPlayer mp;


        public ImageView getFotomensaje() {
            return fotomensaje;
        }

        public void setFotomensaje(ImageView fotomensaje) {
            this.fotomensaje = fotomensaje;
        }

        public ImageView getFotomensaje2() {
            return fotomensaje2;
        }

        public void setFotomensaje2(ImageView fotomensaje2) {
            this.fotomensaje2 = fotomensaje2;
        }

        public MessageViewHolder(View view){
            super(view);

            leftMsgLayout =  itemView.findViewById(R.id.chat_left_msg_layout);
            rightMsgLayout =  itemView.findViewById(R.id.chat_right_msg_layout);

            leftMsgImgLayout = itemView.findViewById(R.id.image_left_msg_layout);
            rightMsgImgLayout = itemView.findViewById(R.id.image_right_msg_layout);

            MsgAudioLayout = itemView.findViewById(R.id.audio_msg_layout);
            MsgAudioLayoutleft = itemView.findViewById(R.id.audio_left_msg_layout);

            leftMsgTextView =  itemView.findViewById(R.id.chat_left_msg_text_view);
            rightMsgTextView =  itemView.findViewById(R.id.chat_right_msg_text_view);

            fotomensaje = itemView.findViewById(R.id.mensaje_foto);
            fotomensaje2 = itemView.findViewById(R.id.mensaje_foto2);

            fromTime = itemView.findViewById(R.id.from_time);
            toTime = itemView.findViewById(R.id.to_time);

            fromTimeimg = itemView.findViewById(R.id.from_time_image);
            toTimeimg = itemView.findViewById(R.id.to_time_image);

            playAudio = itemView.findViewById(R.id.play_pause_chat_msg);
            playAudioleft = itemView.findViewById(R.id.play_pause_chat_msg_left);


        }

    }

    @Override
    public void onBindViewHolder(@NonNull final MessageViewHolder messageViewHolder, final int i) {

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


                if(dataSnapshot.getKey().equals(current_user_id))
                {

                    messageViewHolder.MsgAudioLayoutleft.setVisibility(LinearLayout.GONE);
                    messageViewHolder.MsgAudioLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.leftMsgLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.leftMsgImgLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.rightMsgImgLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.rightMsgLayout.setVisibility(LinearLayout.VISIBLE);

                    if(message_type.equals("text")) {

                        messageViewHolder.rightMsgTextView.setText(msgDto.getMessage());

                    }else if(message_type.equals("image")){
                            messageViewHolder.rightMsgImgLayout.setVisibility(View.VISIBLE);

                            Glide.with(messageViewHolder.fotomensaje2.getContext())
                                    .load(msgDto.getUrlFoto())
                                    .into(messageViewHolder.getFotomensaje2());
                            messageViewHolder.rightMsgTextView.setText(msgDto.getMessage());

                        }else if(message_type.equals("Audio")){

                        messageViewHolder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                        messageViewHolder.MsgAudioLayout.setVisibility(LinearLayout.VISIBLE);

                        messageViewHolder.playAudio.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                messageViewHolder.mp = new MediaPlayer();
                                try {
                                    messageViewHolder.mp.setDataSource(msgDto.getUrlAudio());

                                    /*messageViewHolder.mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                        @Override
                                        public void onPrepared(MediaPlayer mpP) {
                                            messageViewHolder.mp.start();

                                        }
                                    });*/
                                    messageViewHolder.mp.prepare();


                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.d("CORNEJO",e.getMessage().toString());
                                }

                                messageViewHolder.mp.start();


                            }
                        });

                    }
                    messageViewHolder.leftMsgLayout.setVisibility(LinearLayout.GONE);

                }
                else
                {
                    messageViewHolder.MsgAudioLayoutleft.setVisibility(LinearLayout.GONE);
                    messageViewHolder.MsgAudioLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.leftMsgImgLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.rightMsgImgLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                    messageViewHolder.leftMsgLayout.setVisibility(LinearLayout.VISIBLE);

                    if(message_type.equals("text")) {

                        messageViewHolder.leftMsgTextView.setText(msgDto.getMessage());

                    }else if(message_type.equals("image")){

                        messageViewHolder.leftMsgImgLayout.setVisibility(View.VISIBLE);

                        Glide.with(messageViewHolder.fotomensaje.getContext())
                                .load(msgDto.getUrlFoto())
                                .into(messageViewHolder.getFotomensaje());
                        messageViewHolder.leftMsgTextView.setText(msgDto.getMessage());

                    }else if(message_type.equals("Audio")){

                        messageViewHolder.leftMsgLayout.setVisibility(LinearLayout.GONE);
                        messageViewHolder.MsgAudioLayoutleft.setVisibility(LinearLayout.VISIBLE);

                        messageViewHolder.playAudioleft.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                messageViewHolder.mp = new MediaPlayer();
                                try {
                                    messageViewHolder.mp.setDataSource(msgDto.getUrlAudio());

                                    /*messageViewHolder.mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                        @Override
                                        public void onPrepared(MediaPlayer mpP) {
                                            messageViewHolder.mp.start();

                                        }
                                    });*/
                                    messageViewHolder.mp.prepare();


                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.d("CORNEJO",e.getMessage().toString());
                                }

                                messageViewHolder.mp.start();


                            }
                        });

                    }
                    messageViewHolder.rightMsgLayout.setVisibility(LinearLayout.GONE);
                }

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