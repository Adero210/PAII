package ceti.edu.paii.view.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ceti.edu.paii.BottomSheetDialog;
import ceti.edu.paii.R;
import ceti.edu.paii.adapter.PictureAdapterRecyclerView;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.Picture;

import static ceti.edu.paii.R.id.pictureRecycler;


public class HomeFragment extends Fragment {

    private String id;
    private FirebaseAuth firebaseAuth;
    private static String URL_READ = comun.URL + "getAllCoursesMobile.php";
    public static String terminadoIn = "";
    public static String nameCurseIn = "";
    public static String terminadoIt = "";
    public static String nameCurseIt = "";
    private TextView mensajeError;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar("Inicio ",false,view);

        //Crea un linerLayout para acomodar los recyclerviews

        RecyclerView picturesRecycler =  view.findViewById(pictureRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);


        mensajeError = view.findViewById(R.id.mensajeerror);
        id = firebaseAuth.getInstance().getCurrentUser().getUid();
        Log.i("idUserI",id);

        getUserDetail(picturesRecycler);




        return view;


    }

    private void inflateView(RecyclerView picturesRecycler) {
        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buidPictures(), R.layout.cardview_picture,getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

    }

    private void getUserDetail(final RecyclerView picturesRecycler) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Boolean success = jsonObject.getBoolean("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("courses");
                            Log.i("ttt", String.valueOf(success));


                            if (success == true) {
                                Double progressEnglishCourse = null;
                                String nameEnglishCourse = "";
                                Double progressItalianCourse = null;
                                String nameItalianCourse = "";
                                String strName = jsonObject.getString("nickname").trim();
                                comun.userNameLec = strName;
                                comun.userName = strName;
                                comun.getId = id;

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    if(object.getString("id").equals("1")) {
                                        progressEnglishCourse = object.getDouble("progress");
                                        nameEnglishCourse = object.getString("name").trim();
                                    }
                                    if(object.getString("id").equals("2")){
                                        progressItalianCourse = object.getDouble("progress");
                                        nameItalianCourse = object.getString("name").trim();
                                    }

                                    //Log.i("ttt", terminadoIn + terminadoIt + nameCurseIn + nameCurseIt + strName + object);
                                }
                                terminadoIn = String.valueOf(progressEnglishCourse);
                                nameCurseIn = nameEnglishCourse;
                                terminadoIt = String.valueOf(progressItalianCourse);
                                nameCurseIt = nameItalianCourse;

                                inflateView(picturesRecycler);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            terminadoIn = "0";
                            terminadoIt = "0";
                            nameCurseIn = "Ingles";
                            nameCurseIt = "Italiano";

                            //Toast.makeText(getContext(), "Error reading dialog: " + e.toString(), Toast.LENGTH_SHORT).show();
                           inflateView(picturesRecycler);
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Error reading dialog: " + error.toString(), Toast.LENGTH_SHORT).show();
                        mensajeError.setVisibility(View.VISIBLE);
                        mensajeError.setText("Sin Conexion Sorry");

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firebaseId", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }



    public ArrayList<Picture> buidPictures(){

        Log.i("ggrrr", terminadoIn + terminadoIt + nameCurseIn + nameCurseIt );

        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://mlstaticquic-a.akamaihd.net/bandera-estados-unidos-eeuu-usa-150x90-envio-gratis-D_NQ_NP_962788-MLU26870601027_022018-F.jpg",
                nameCurseIn,terminadoIn));
        pictures.add(new Picture("https://wallpapercave.com/wp/wp1841290.jpg",
                nameCurseIt,String.valueOf(terminadoIt)));
        return pictures;
    }

    public void showToolbar(String tittle, boolean upButton,View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}

