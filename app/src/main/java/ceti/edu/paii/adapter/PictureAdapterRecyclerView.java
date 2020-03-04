package ceti.edu.paii.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.BottomSheetDialog;
import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.Message;
import ceti.edu.paii.model.Picture;
import ceti.edu.paii.view.PictureDetailActivity;
import ceti.edu.paii.view.Settings;

public class PictureAdapterRecyclerView extends RecyclerView.Adapter<PictureAdapterRecyclerView.PictureViewHolder> {

    private String id;
    private FirebaseAuth firebaseAuth;
    private String TAG_BOTTOMSHETT = "BottomSheet";
    private String TAG = "cursuc";

    private ArrayList<Picture> pictures;
    private static String URL_EDIT = comun.URL + "inscribeUserToCourse.php";

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
        pictureViewHolder.porcentajeCal.setText(picture.getPorcentaje()+"%");


        id = firebaseAuth.getInstance().getCurrentUser().getUid();


        pictureViewHolder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // inscription();

              //  if(comun.MESSAGE_BOTTON_SHEET.equals("YES")){
                    Intent intent = new Intent(activity, PictureDetailActivity.class);
                    intent.putExtra("curse_name", picture.getUserName());
                    comun.MESSAGE_BOTTON_SHEET= "";

                    String curso = picture.getUserName();
                    int cursoint = 0;
                    Log.i(TAG,curso);
                    if (curso.equals("Inglés")){
                        cursoint = 1;
                    }else if(curso.equals("Italiano")){
                        cursoint = 2;
                    }

                    intent.putExtra("curse_id", String.valueOf(cursoint));

                    crearCurso(cursoint);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        Explode explode = new Explode();
                        explode.setDuration(100);
                        activity.getWindow().setExitTransition(explode);
                          activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());
                  // }else {
                      //   activity.startActivity(intent);
                    //}

                }else {
                    Log.i("MessageBotton",comun.MESSAGE_BOTTON_SHEET);
                    comun.MESSAGE_BOTTON_SHEET= "";
                }

            }
        });
    }

    private void inscription() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
        bottomSheetDialog.show(comun.fragmentManager,TAG_BOTTOMSHETT);

        Log.i("MessageBotton",comun.MESSAGE_BOTTON_SHEET);
    }


    private void crearCurso(final int cursoint) {

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
                             //   Toast.makeText(Settings.this, "Exito!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                           // Toast.makeText(activity.this, "Error del catch! " + e.toString(), Toast.LENGTH_SHORT).show();
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
                params.put("firebaseId", id);
                params.put("courseId", String.valueOf(cursoint));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }


    public static class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView pictureCard;
        private TextView usernameCard;
        private TextView porcentajeCal;


        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureCard     =  itemView.findViewById(R.id.image_card);
            usernameCard    =  itemView.findViewById(R.id.userName_card);
            porcentajeCal    = itemView.findViewById(R.id.porcentajeCard);


        }
    }

}

