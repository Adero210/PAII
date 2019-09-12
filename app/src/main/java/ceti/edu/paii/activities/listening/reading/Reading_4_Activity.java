package ceti.edu.paii.activities.listening.reading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.awt.font.NumericShaper;
import java.util.Random;
import java.util.Stack;
import java.util.stream.IntStream;

import ceti.edu.paii.R;

public class Reading_4_Activity extends AppCompatActivity {

    TextView parrafo,word1,word2,word3,word4,word5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_4_);


        parrafo = findViewById(R.id.parrafo_Reading_4);
        word1 = findViewById(R.id.word_1);
        word2 = findViewById(R.id.word_2);
        word3 = findViewById(R.id.word_3);
        word4 = findViewById(R.id.word_4);
        word5 = findViewById(R.id.word_5);



        String p = parrafo.getText().toString();
        Log.i("PARRAFO", String.valueOf(p));

        String[] words = p.split("¬");


        final String part1 = words[1];
        String part2 = words[3];
        String part3 = words[5];
        String part4 = words[7];
        String part5 = words[9];


        String ss = p.replace(words[1], "____");
        String ssc2 = ss.replace(words[3], "____");
        String ssc3 = ssc2.replace(words[5], "____");
        String ssc4 = ssc3.replace(words[7], "____");
        String ssc5 = ssc4.replace(words[9], "____");

        String[] r = new String[5];
       // AleatoriSinRepeticion();
        int pos,y=1;
        int nCartas = 5;
        Stack< Integer > pCartas = new Stack < Integer > ();
        for (int i = 0; i < nCartas ; i++) {
            pos = (int) Math.floor(Math.random() * nCartas );
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nCartas );
            }
            r[pos] = words[y];
            pCartas.push(pos);
            y=y+2;
        }
        Log.i("Numeros",pCartas.toString());


        final String partR1 = r[0];
        String partR2 = r[1];
        String partR3 = r[2];
        String partR4 = r[3];
        String partR5 = r[4];



        Log.i("Partes1", part1);
        Log.i("Partes2", part2);
        Log.i("Partes3", part3);
        Log.i("Partes4", part4);
        Log.i("Partes4", part5);

        Log.i("Partes1", partR1);
        Log.i("Partes2", partR2);
        Log.i("Partes3", partR3);
        Log.i("Partes4", partR4);
        Log.i("Partes4", partR5);

        parrafo.setText(ssc5);


        word1.setText(partR1);
        word2.setText(partR2);
        word3.setText(partR3);
        word4.setText(partR4);
        word5.setText(partR5);




        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function(partR1,part1);
            }
        });



    }

    public void function(String word,String correct){
        String wordcheck = word;
        if(wordcheck==correct){
            Toast.makeText(this,"FIRSTWORDCORRECT",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"nah",Toast.LENGTH_SHORT).show();

        }


    }


}
