package ceti.edu.paii.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.PictureAdapterRecyclerView;
import ceti.edu.paii.model.Picture;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        showToolbar("",false,view);

        RecyclerView picturesProfileRecycler = (RecyclerView) view.findViewById(R.id.pictureProfileRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        picturesProfileRecycler.setLayoutManager(linearLayoutManager);

        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(
                buidPictures(), R.layout.cardview_picture,getActivity());

        picturesProfileRecycler.setAdapter(pictureAdapterRecyclerView);

        return view;
    }

    public ArrayList<Picture> buidPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
      /*  pictures.add(new Picture("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.redhillsadventure.ie%2Fwp-content%2Fuploads%2F2018%2F02%2Farchery3.jpg&imgrefurl=https%3A%2F%2Fwww.redhillsadventure.ie%2Factivities%2Ftarget-archery%2F&docid=pOW-tz3lxkzg-M&tbnid=WBbkn0Cr8vUP1M%3A&vet=10ahUKEwiThIfCrOLhAhXKhVQKHUqPAMsQMwhDKAQwBA..i&w=780&h=500&safe=active&bih=868&biw=872&q=imagenes%20archery&ved=0ahUKEwiThIfCrOLhAhXKhVQKHUqPAMsQMwhDKAQwBA&iact=mrc&uact=8",
                "Luis Cornejo", "4 días", "4 Me gusta"));
        pictures.add(new Picture("http://mraag.com/files/bigstock/2018/05/Back-of-archery-athlete-aiming-168652454.jpg?w=1060&h=795&a=t",
                "La Ramirez", "Hace unos segundos", "0 Me gusta"));
        pictures.add(new Picture("https://www.poblanerias.com/wp-content/archivos/2015/07/KARLA.jpg",
                "Karla Hinojosa", "6 días", "65 Me gusta"));
        pictures.add(new Picture("https://http2.mlstatic.com/trucos-con-cartas-gods-creation-by-miquel-roman-promocion-D_NQ_NP_731000-MLM25606087106_052017-F.jpg",
                "Miquel Roman", "3 horas", "210 Me gusta"));*/
        return pictures;
    }

    public void showToolbar(String tittle, boolean upButton,View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
