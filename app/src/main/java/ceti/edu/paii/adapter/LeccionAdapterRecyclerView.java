package ceti.edu.paii.adapter;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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

import java.util.ArrayList;

import ceti.edu.paii.R;
import ceti.edu.paii.model.Leccion;
import ceti.edu.paii.view.Activities_Activity;
import ceti.edu.paii.view.StatusActivity;

public class LeccionAdapterRecyclerView extends RecyclerView.Adapter<LeccionAdapterRecyclerView.LeccionViewHolder> {

    private ArrayList<Leccion> lecciones;
    private int resource;
    private Activity activity;


    public LeccionAdapterRecyclerView(ArrayList<Leccion> lecciones, int resource, Activity activity) {
        this.lecciones = lecciones;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LeccionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource,viewGroup, false);
        return new LeccionViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull LeccionAdapterRecyclerView.LeccionViewHolder pictureViewHolder, int i) {
        final Leccion leccion = lecciones.get(i);
        pictureViewHolder.leccionNumberCard.setText(leccion.getLeccionWord());
        pictureViewHolder.NumberCard.setText(leccion.getLeccionNumber());
        pictureViewHolder.lenguajeCard.setText(leccion.getLeccionLenguaje());
        pictureViewHolder.completadoCard.setText(leccion.getPorcentajeWord());
        pictureViewHolder.porcentajeCard.setText(leccion.getPorcentajeNumber());
        Picasso.with(this.activity).load(leccion.getLeccionBack()).into(pictureViewHolder.leccionBackCard);


       pictureViewHolder.leccionBackCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(activity, Activities_Activity.class);
                i.putExtra("curse_name", leccion.getLeccionLenguaje());
                i.putExtra("lesson",leccion.getLeccionNumber());


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(100);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(i,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                }else {
                    activity.startActivity(i);
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return lecciones.size();
    }

    public class LeccionViewHolder extends RecyclerView.ViewHolder {

        private ImageView leccionBackCard;
        private TextView leccionNumberCard;
        private TextView NumberCard;
        private TextView lenguajeCard;
        private TextView completadoCard;
        private TextView porcentajeCard;

        public LeccionViewHolder(@NonNull View itemView) {
            super(itemView);

            leccionBackCard = (ImageView) itemView.findViewById(R.id.leccionBackCard);
            leccionNumberCard = (TextView) itemView.findViewById(R.id.leccionNumberCard);
            NumberCard = (TextView) itemView.findViewById(R.id.NumberCard);
            lenguajeCard = (TextView) itemView.findViewById(R.id.lenguajeCard);
            completadoCard = (TextView) itemView.findViewById(R.id.completadoCard);
            porcentajeCard = (TextView) itemView.findViewById(R.id.porcentajeCard);
        }



    }
}
