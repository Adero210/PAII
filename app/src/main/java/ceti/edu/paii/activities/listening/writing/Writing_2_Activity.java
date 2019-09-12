package ceti.edu.paii.activities.listening.writing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Stack;

import ceti.edu.paii.R;

public class Writing_2_Activity extends AppCompatActivity {

    TextView  oracionText;
    String oracionCorrecta = " She is¬ going to visit¬ her parents¬ a couple¬ of weeks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_2_);

        oracionText = findViewById(R.id.textview_1_activity_writing_2);

        String partesOra[] = oracionCorrecta.split("¬");

        String part1 = partesOra[0];
        String part2 = partesOra[1];

        String part3 = partesOra[2];
        String part4 = partesOra[3];
        String part5 = partesOra[4];

        String[] r = new String[5];
        // AleatoriSinRepeticion();
        int pos,y=0;
        int nCartas = 5;
        Stack< Integer > pCartas = new Stack < Integer > ();
        for (int i = 0; i < nCartas ; i++) {
            pos = (int) Math.floor(Math.random() * nCartas );
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nCartas );
            }
            r[pos] = partesOra[y];
            pCartas.push(pos);
            y++;
        }
        Log.i("Numeros",pCartas.toString());


        String partR1 = r[0];
        String partR2 = r[1];
        String partR3 = r[2];
        String partR4 = r[3];
        String partR5 = r[4];

        String allOracion = partR1+partR2+partR3+partR4+partR5;
        oracionText.setText(allOracion);

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


    }
}
