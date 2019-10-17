package ceti.edu.paii.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.ArrayList;

import ceti.edu.paii.R;
import ceti.edu.paii.model.Picture;
import ceti.edu.paii.view.PictureDetailActivity;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private ArrayList<Picture> pictures;
    private int resource;
    private Activity activity;

    public PictureAdapterRecyclerView(ArrayList<Picture> pictures, int resource, Activity activity) {
        this.pictures = pictures;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(resource,parent, false);
        return new PictureViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final PictureAdapterRecyclerView.PictureViewHolder pictureViewHolder, int i) {
        final Picture picture = pictures.get(i);
        pictureViewHolder.usernameCard.setText(picture.getUserName());
        Picasso.with(this.activity).load(picture.getPicture()).into(pictureViewHolder.pictureCard);

        pictureViewHolder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PictureDetailActivity.class);
                intent.putExtra("curse_name", picture.getUserName());


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(100);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                }else {
                    activity.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView usernameCard;


        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard     = (ImageView)  itemView.findViewById(R.id.image_card);
            usernameCard    = (TextView)  itemView.findViewById(R.id.userName_card);

        }
    }
}