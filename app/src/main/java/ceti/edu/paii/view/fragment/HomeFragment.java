package ceti.edu.paii.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.PictureAdapterRecyclerView;
import ceti.edu.paii.model.Picture;

import static ceti.edu.paii.R.id.parrafo_Reading_2;
import static ceti.edu.paii.R.id.pictureProfileRecycler;
import static ceti.edu.paii.R.id.pictureRecycler;

/**
 * A simple {@link Fragment} subclass.
 *
 *
 */


public class HomeFragment extends Fragment {

    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home),false,view);

        //Crea un linerLayout para acomodar los recyclerviews

        RecyclerView picturesRecycler = (RecyclerView) view.findViewById(pictureRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        picturesRecycler.setLayoutManager(linearLayoutManager);


        ///Sube datos a firebase el dato es un 0
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_uid = mCurrentUser.getUid();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("not").child(current_uid);

        mUserDatabase.child("dia").setValue("0").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    //Toast.makeText(getContext(),
                    //        "Se guardo Exitosamente",
                    //       Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(),
                            "Se obtuvo un error mientras guardaba",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //////////termina de enviar datos a firebase


        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buidPictures(), R.layout.cardview_picture,getActivity());

        picturesRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }


    public ArrayList<Picture> buidPictures(){
        String userNameIn = "Curso Ingles";
        String userNameIt = "Curso Italiano";
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("https://mlstaticquic-a.akamaihd.net/bandera-estados-unidos-eeuu-usa-150x90-envio-gratis-D_NQ_NP_962788-MLU26870601027_022018-F.jpg",
                userNameIn));
        pictures.add(new Picture("https://wallpapercave.com/wp/wp1841290.jpg",
                userNameIt));
        return pictures;
    }


    public void showToolbar(String tittle, boolean upButton,View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}

