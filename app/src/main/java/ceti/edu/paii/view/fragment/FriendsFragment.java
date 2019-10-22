package ceti.edu.paii.view.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import ceti.edu.paii.R;
import ceti.edu.paii.view.ChatActivity;
import ceti.edu.paii.view.ChatActivity2;
import ceti.edu.paii.view.Friends;
import ceti.edu.paii.view.ProfileActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    private RecyclerView mFriendsList;

    private DatabaseReference mFriendsDatabase;
    private DatabaseReference mUserDtabase;
    private FirebaseAuth mAuth;

    private String mCurrent_user_id;

    private View mMainView;


    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.fragment_friends, container, false);

        mFriendsList = mMainView.findViewById(R.id.friends_list);
        mAuth = FirebaseAuth.getInstance();

        mCurrent_user_id = mAuth.getCurrentUser().getUid();

        mFriendsDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(mCurrent_user_id);
        mFriendsDatabase.keepSynced(true);
        mUserDtabase = FirebaseDatabase.getInstance().getReference().child("user");
        mUserDtabase.keepSynced(true);


        mFriendsList.setHasFixedSize(true);
        mFriendsList.setLayoutManager(new LinearLayoutManager(getContext()));


        return mMainView;
    }

    @Override
    public  void onStart(){
        super.onStart();

        final FirebaseRecyclerAdapter<Friends, FriendsViewHolder> friendsFriendsViewHolderFirebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<Friends, FriendsViewHolder>(
                Friends.class,
                R.layout.users_single_layout,
                FriendsViewHolder.class,
                mFriendsDatabase
        ) {
            @Override
            protected void populateViewHolder(final FriendsViewHolder viewHolder, Friends model, int position) {

                viewHolder.setDate(model.getDate());

                final String listUserid = getRef(position).getKey();

                mUserDtabase.child(listUserid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final String userName = dataSnapshot.child("name").getValue().toString();
                        String userThumb = dataSnapshot.child("thumb_image").getValue().toString();

                        if(dataSnapshot.hasChild("online")){

                            Boolean userOnline = (boolean) dataSnapshot.child("online").getValue();
                            viewHolder.setOnline(userOnline);

                        }

                        viewHolder.setName(userName);
                        viewHolder.setUserImage(userThumb,getContext());


                        viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                             CharSequence options[] = new CharSequence[]{"Open Profile", "Send message"};

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Selecciona las opciones ") ;
                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        if(which == 0){
                                            Intent profileIntent = new Intent(getActivity(), ProfileActivity.class);
                                            profileIntent.putExtra("userId",listUserid);
                                            startActivity(profileIntent );
                                        }
                                        if(which == 1){

                                            Intent chatIntent = new Intent(getActivity(), ChatActivity2.class);
                                            chatIntent.putExtra("userId",listUserid);
                                            chatIntent.putExtra("name",userName);
                                            startActivity(chatIntent );

                                        }

                                    }
                                });

                                builder.show();


                            }
                        });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        };

        mFriendsList.setAdapter(friendsFriendsViewHolderFirebaseRecyclerAdapter);

    }

    public static class FriendsViewHolder extends RecyclerView.ViewHolder{
        View mview;
        public FriendsViewHolder(View itemView){
            super(itemView);

            mview = itemView;
        }

        public void setDate(String date){
            TextView userNameView = mview.findViewById(R.id.user_single_status);
            userNameView.setText(date);
        }

        public void setName(String name){
            TextView userNameView = mview.findViewById(R.id.user_single_name);
            userNameView.setText(name);
        }

        public void setUserImage(final String thumb_image, final Context ctx){

            final CircleImageView userImageView = mview.findViewById(R.id.user_single_image);
            Picasso.with(ctx).load(thumb_image).networkPolicy(NetworkPolicy.OFFLINE)
                    .placeholder(R.drawable.logo).into(userImageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                    Picasso.with(ctx).load(thumb_image).placeholder(R.drawable.logo).into(userImageView);


                }
            });
        }

        public void setOnline(Boolean online){
            ImageView userOnlineView = mview.findViewById(R.id.user_single_online_icon);
            if (online == true){

                userOnlineView.setVisibility(View.VISIBLE);
            }else {
                userOnlineView.setVisibility(View.INVISIBLE);
            }

        }

    }

}
