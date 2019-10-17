package ceti.edu.paii.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import ceti.edu.paii.R;
import ceti.edu.paii.model.User;
import ceti.edu.paii.view.ProfileActivity;
import ceti.edu.paii.view.Users;
import de.hdodenhof.circleimageview.CircleImageView;


public class UsersFragemts extends Fragment {

    private RecyclerView mUserList;

    private DatabaseReference mUserDatabase;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view =  inflater.inflate(R.layout.fragment_users_fragemts, container, false);


            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("user");
            mUserDatabase.keepSynced(true);


        mUserList = view.findViewById(R.id.users_list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mUserList.setLayoutManager(layoutManager);
            //layoutManager.setAutoMeasureEnabled(true);//quitar si hay problemas
            mUserList.setHasFixedSize(true);





            return view;

        }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Users, UsersViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Users, UsersViewHolder>(
                        Users.class,
                        R.layout.users_single_layout,
                        UsersViewHolder.class,
                        mUserDatabase
                ) {

                    @Override
                    protected void populateViewHolder(UsersViewHolder viewHolder, Users model, int position) {

                        viewHolder.setName(model.getName());
                        viewHolder.setUserStatus(model.getStatus());
                        viewHolder.setUserImage(model.getThumb_image(),getActivity().getApplicationContext());


                        final String user_id = getRef(position).getKey();



                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent profileIntent = new Intent(getActivity(), ProfileActivity.class);
                                profileIntent.putExtra("userId",user_id);
                                startActivity(profileIntent );

                            }
                        });
                    }
                };

        mUserList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setName(String name){
            TextView userNameView = mView.findViewById(R.id.user_single_name);
            userNameView.setText(name);
        }

        public void setUserImage(final String thumb_image, final Context ctx){

            final CircleImageView userImageView = mView.findViewById(R.id.user_single_image);
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

        private void setUserStatus(String status){

            TextView userStatusView = mView.findViewById(R.id.user_single_status);
            userStatusView.setText(status);

        }

    }

}
