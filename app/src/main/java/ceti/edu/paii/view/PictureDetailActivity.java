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
import android.widget.EditText;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        leccionesRecycler.setLayoutManager(linearLayoutManager);

        String lessonName = getDta();



        LeccionAdapterRecyclerView leccionAdapterRecyclerView =
                new LeccionAdapterRecyclerView(buidLeccines(lessonName),R.layout.cardview_leccion_bottom,
                        PictureDetailActivity.this);

        leccionesRecycler.setAdapter(leccionAdapterRecyclerView);

    }

    public ArrayList<Leccion> buidLeccines(String r){
        String n = r;
        ArrayList<Leccion> lecciones = new ArrayList<>();
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "1", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "2", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "3", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "4", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "5", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "6", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "7", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "8", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "9", n,"Completed","0%"));
        lecciones.add(new Leccion("http://cdn.shopify.com/s/files/1/0778/7113/files/Lessons.png?v=1499095368","Lesson", "10", n,"Completed","0%"));

        return lecciones;
    }

    private String getDta(){
        String lessonName = "";
        if(getIntent().hasExtra("curse_name")){
             lessonName = getIntent().getStringExtra("curse_name");
        }
        return  lessonName;
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
