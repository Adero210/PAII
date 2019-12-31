package ceti.edu.paii.adapter;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.Leccion;
import ceti.edu.paii.view.Activities_Activity;
import ceti.edu.paii.view.PictureDetailActivity;
import ceti.edu.paii.view.StatusActivity;

public class LeccionAdapterRecyclerView extends RecyclerView.Adapter<LeccionAdapterRecyclerView.LeccionViewHolder> {

    private ArrayList<Leccion> lecciones;
    private int resource;
    private Activity activity;

    private static String URL_EDIT = comun.URL + "proyecto/edit_cali.php";



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
        pictureViewHolder.calificacionCard.setText(leccion.getCalificacionCard());
        pictureViewHolder.calificacionNum.setText(leccion.getCalificacionNum());

        Picasso.with(this.activity).load(leccion.getLeccionBack()).into(pictureViewHolder.leccionBackCard);


       pictureViewHolder.leccionBackCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String numlec = leccion.getLeccionNumber();
                int lesson = Integer.valueOf(numlec);


                if (leccion.getLeccionLenguaje().equals("Italiano")) {
                    switch (numlec) {
                        case "1":
                            lesson = 11;
                            break;
                        case "2":
                            lesson = 12;
                            break;
                        case "3":
                            lesson = 13;
                            break;
                        case "4":
                            lesson = 14;
                            break;
                        case "5":
                            lesson = 15;
                            break;
                        case "6":
                            lesson = 16;
                            break;
                        case "7":
                            lesson = 17;
                            break;
                        case "8":
                            lesson = 18;
                            break;
                        case "9":
                            lesson = 19;
                            break;
                        case "10":
                            lesson = 20;
                            break;


                    }

                }


                int l = lesson-1;
                comun.lessonCalis = l;
                craeteCali(l);


                Log.i("lesson:",numlec+leccion.getLeccionLenguaje()+l);



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

    private void craeteCali(final int ins) {
        final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Guardando...");
        progressDialog.show();

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                // Toast.makeText(PictureDetailActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            // Toast.makeText(PictureDetailActivity.this, "Error del catch! " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        // Toast.makeText(Settings.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", comun.userNameLec);
                params.put("leccion",String.valueOf(ins));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
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
        private TextView calificacionCard;
        private TextView calificacionNum;



        public LeccionViewHolder(@NonNull View itemView) {
            super(itemView);

            leccionBackCard = (ImageView) itemView.findViewById(R.id.leccionBackCard);
            leccionNumberCard = (TextView) itemView.findViewById(R.id.leccionNumberCard);
            NumberCard = (TextView) itemView.findViewById(R.id.NumberCard);
            lenguajeCard = (TextView) itemView.findViewById(R.id.lenguajeCard);
            completadoCard = (TextView) itemView.findViewById(R.id.completadoCard);
            porcentajeCard = (TextView) itemView.findViewById(R.id.porcentajeCard);
            calificacionCard = (TextView) itemView.findViewById(R.id.calificacionwordCard);
            calificacionNum = (TextView) itemView.findViewById(R.id.calificacionnumbercard);


        }



    }
}
