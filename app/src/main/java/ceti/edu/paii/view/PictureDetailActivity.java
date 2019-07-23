package ceti.edu.paii.view;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.LeccionAdapterRecyclerView;
import ceti.edu.paii.model.Leccion;


public class PictureDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        showToolbar("Lessons",true);

        RecyclerView leccionesRecycler = (RecyclerView) findViewById(R.id.leccionRecycler);
        leccionesRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        leccionesRecycler.setLayoutManager(linearLayoutManager);


        LeccionAdapterRecyclerView leccionAdapterRecyclerView =
                new LeccionAdapterRecyclerView(buidLeccines(),R.layout.cardview_leccion_bottom, new PictureDetailActivity());

        leccionesRecycler.setAdapter(leccionAdapterRecyclerView);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(new Fade());
        }
    }

    public ArrayList<Leccion> buidLeccines(){
        ArrayList<Leccion> lecciones = new ArrayList<>();
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "1", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "2", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "3", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "4", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "5", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "6", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "7", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "8", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "9", "Ingles","Completed","0%"));
        lecciones.add(new Leccion("http://quinientostres.com/wp-content/uploads/2018/01/Fondo-de-agua1-750x450.jpg","Lesson", "10", "Ingles","Completed","0%"));

        return lecciones;
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
