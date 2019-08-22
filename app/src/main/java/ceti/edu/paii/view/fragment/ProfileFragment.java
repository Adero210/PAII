package ceti.edu.paii.view.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.Listening_1_Activity;
import ceti.edu.paii.activities.listening.Listening_2_Activity;
import ceti.edu.paii.activities.listening.Listening_3_Activity;
import ceti.edu.paii.activities.listening.Listening_4_Activity;
import ceti.edu.paii.adapter.PictureAdapterRecyclerView;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.Picture;
import ceti.edu.paii.view.ContainerActivity;
import ceti.edu.paii.view.SessionManager;
import ceti.edu.paii.view.Settings;
import ceti.edu.paii.view.StatusActivity;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private CircleImageView profile_image;
    private TextView mName, mStatus;
    private Bitmap bitmap;
    private static String URL_UPLOAD = comun.URL + "proyecto/upload.php";
    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;

    private Button mStatusbtn;
    private FloatingActionButton btn_photo_upload;

    //Storage Firebase
    private StorageReference mImageStorage;

    ProgressDialog progressDialog;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("",false,view);


        profile_image  = view.findViewById(R.id.imageuser_profile);
        mName   = view.findViewById(R.id.userNameProfile);
        mStatus = view.findViewById(R.id.descriptionProfile);
        //String username =  comun.userName;

        mImageStorage = FirebaseStorage.getInstance().getReference();


                /*obtenemos el ususario y el uid de firebase y posicionamos en la base de datos
         * para obtener valores desde firebase */
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(current_uid);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString().trim();
                String image = dataSnapshot.child("image").getValue().toString().trim();
                String status = dataSnapshot.child("status").getValue().toString().trim();
                String thumb_image = dataSnapshot.child("thumb_image").getValue().toString().trim();

                mName.setText(name);
                mStatus.setText(status);

                Picasso.with(getActivity()).load(image).into(profile_image);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Log.i("nombre",username+"");
        //mName.setText(comun.userName);

        RecyclerView picturesProfileRecycler = (RecyclerView) view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        picturesProfileRecycler.setLayoutManager(linearLayoutManager);

        btn_photo_upload = view.findViewById(R.id.floating_profile_button);
        mStatusbtn = view.findViewById(R.id.change_status_buttom);


        btn_photo_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
                /*CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(getActivity());*/
            }
        });

        mStatusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status_value = mStatus.getText().toString();

                //Intent i = new Intent(getContext(), StatusActivity.class);
                Intent i = new Intent(getContext(), Listening_4_Activity.class);
                i.putExtra("Descripción",status_value);
                startActivity(i);
            }
        });

        /*PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(
                buidPictures(), R.layout.cardview_picture,getActivity());

        picturesProfileRecycler.setAdapter(pictureAdapterRecyclerView);*/


        return view;
    }

   /* public ArrayList<Picture> buidPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
       pictures.add(new Picture("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.redhillsadventure.ie%2Fwp-content%2Fuploads%2F2018%2F02%2Farchery3.jpg&imgrefurl=https%3A%2F%2Fwww.redhillsadventure.ie%2Factivities%2Ftarget-archery%2F&docid=pOW-tz3lxkzg-M&tbnid=WBbkn0Cr8vUP1M%3A&vet=10ahUKEwiThIfCrOLhAhXKhVQKHUqPAMsQMwhDKAQwBA..i&w=780&h=500&safe=active&bih=868&biw=872&q=imagenes%20archery&ved=0ahUKEwiThIfCrOLhAhXKhVQKHUqPAMsQMwhDKAQwBA&iact=mrc&uact=8",
                "Luis Cornejo"));
        pictures.add(new Picture("http://mraag.com/files/bigstock/2018/05/Back-of-archery-athlete-aiming-168652454.jpg?w=1060&h=795&a=t",
                "La Ramirez"));
        pictures.add(new Picture("https://www.poblanerias.com/wp-content/archivos/2015/07/KARLA.jpg",
                "Karla Hinojosa"));
        pictures.add(new Picture("https://http2.mlstatic.com/trucos-con-cartas-gods-creation-by-miquel-roman-promocion-D_NQ_NP_731000-MLM25606087106_052017-F.jpg",
                "Miquel Roman"));
        return pictures;
    }*/

    public void showToolbar(String tittle, boolean upButton,View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void chooseFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selecciona una imagen"),1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == 1 && resultCode == getActivity().RESULT_OK) {
           
           progressDialog = new ProgressDialog(getActivity());
           progressDialog.setTitle("Subiendo Imagen");
           progressDialog.setMessage("Estamos subiendo la imagen, por favor espera");
           progressDialog.setCanceledOnTouchOutside(false);
           progressDialog.show();
           Uri imageUrl = data.getData();
           final String current_user_id = mCurrentUser.getUid();
           Log.i("helppppp", String.valueOf(imageUrl));
           StorageReference filepath = mImageStorage.child("profile_images").child(current_user_id+"jpg");

           filepath.putFile(imageUrl).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task) {
                   if(task.isSuccessful()){


                        mImageStorage.child("profile_images/"+current_user_id+"jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("Url_image", String.valueOf(uri));
                                mUserDatabase = FirebaseDatabase.getInstance().getReference().child("user").child(current_user_id);
                                String Url_download = uri.toString();
                                mUserDatabase.child("image").setValue(Url_download).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            progressDialog.dismiss();
                                        }

                                    }
                                });

                            }
                        });

                   }else{
                       progressDialog.dismiss();
                       Toast.makeText(getActivity(),"Mal",Toast.LENGTH_LONG).show();

                   }
               }
           });
       }
    }
    private void uploadPicture(final String id, final String photo) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Subiendo...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i("Imagen respuesta",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Vuelvelo a intentar!" + e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Vuelvelo A intentar" + error.toString(), Toast.LENGTH_SHORT).show();


                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("id",id);
                params.put("photo",photo);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    public  String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(imageByteArray,Base64.DEFAULT);

        return encodeImage;
    }


}
