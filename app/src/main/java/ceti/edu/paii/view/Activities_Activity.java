package ceti.edu.paii.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Stack;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.Listening_1_Activity;
import ceti.edu.paii.activities.listening.Listening_2_Activity;
import ceti.edu.paii.activities.listening.Listening_3_Activity;
import ceti.edu.paii.activities.listening.Listening_4_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_1_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_2_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_3_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_4_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_1_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_4_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_Paragraph_Activity;
import ceti.edu.paii.activities.listening.reading.Reading_paragraph_2_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_1_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_2_Activity;
import ceti.edu.paii.activities.listening.speaking.Speaking_3_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_1_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_2_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_3_Activity;
import ceti.edu.paii.activities.listening.vocabulary.Vocabulary_4_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_1_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_2_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_3_Activity;
import ceti.edu.paii.activities.listening.writing.Writing_4_Activity;

public class Activities_Activity extends AppCompatActivity {

    Button btnReading, btnWriting,btnspeaking,btnVocabulary,btnGrammar,btnListening;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_);

        btnGrammar = findViewById(R.id.btn_grammar_activity);
        btnListening = findViewById(R.id.btn_listening_activity);
        btnReading = findViewById(R.id.btn_reading_activity);
        btnWriting = findViewById(R.id.btn_writing_activity);
        btnspeaking = findViewById(R.id.btn_speaking_activity);
        btnVocabulary = findViewById(R.id.btn_vocabulary_activity);


        String data[] = new String[2];
        data = getDta();

        final String language = data[0];
        final String lesson   = data[1];


        Log.i("informaciondeleccion", language + lesson);


        btnVocabulary.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Vocabulary_1_Activity.class);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Vocabulary_2_Activity.class);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Vocabulary_3_Activity.class);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Vocabulary_4_Activity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnspeaking.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Speaking_1_Activity.class);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Speaking_2_Activity.class);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Speaking_3_Activity.class);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Speaking_1_Activity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnWriting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Writing_1_Activity.class);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Writing_2_Activity.class);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Writing_3_Activity.class);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Writing_2_Activity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnReading.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Reading_1_Activity.class);
                        i.putExtra("curso",language);
                        i.putExtra("lesson",lesson);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Reading_Paragraph_Activity.class);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Reading_paragraph_2_Activity.class);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Reading_4_Activity.class);
                        intent2.putExtra("curso",language);
                        intent2.putExtra("lesson",lesson);
                        startActivity(intent2);
                        break;
                }
            }
        });


        btnListening.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num ="";
                num = aleatorio();
                Log.i("numeroRamdon",num);
                switch (num){
                    case "0":
                        Intent i = new Intent(Activities_Activity.this, Listening_1_Activity.class);
                        startActivity(i);
                        break;

                    case "1":
                        Intent intent = new Intent(Activities_Activity.this, Listening_2_Activity.class);
                        startActivity(intent);
                        break;

                    case "2":
                        Intent intent1 = new Intent(Activities_Activity.this, Listening_3_Activity.class);
                        startActivity(intent1);
                        break;

                    case "3":
                        Intent intent2 = new Intent(Activities_Activity.this, Listening_4_Activity.class);
                        startActivity(intent2);
                        break;
                }
            }
        });

        btnGrammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String num ="";
               num = aleatorio();
               Log.i("numeroRamdon",num);
               switch (num){
                   case "0":
                       Intent i = new Intent(Activities_Activity.this, Grammar_1_Activity.class);
                       startActivity(i);
                       break;

                   case "1":
                       Intent intent = new Intent(Activities_Activity.this, Grammar_2_Activity.class);
                       startActivity(intent);
                       break;

                   case "2":
                       Intent intent1 = new Intent(Activities_Activity.this, Grammar_3_Activity.class);
                       startActivity(intent1);
                       break;

                   case "3":
                       Intent intent2 = new Intent(Activities_Activity.this, Grammar_4_Activity.class);
                       startActivity(intent2);
                       break;
               }
            }
        });

    }

    private String aleatorio(){
        // AleatoriSinRepeticion();
        String num = "";
        int pos;
        int nCartas = 4;
        Stack< Integer > pCartas = new Stack < Integer > ();
        for (int i = 0; i < nCartas ; i++) {
            pos = (int) Math.floor(Math.random() * nCartas );
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nCartas );
            }

            pCartas.push(pos);
            num = String.valueOf(pos);
        }
        Log.i("Numeros",pCartas.toString());

        return num;
    }

    private String[] getDta(){
        String data[] = new String[2];
        if(getIntent().hasExtra("curse_name")){
            data[0] = getIntent().getStringExtra("curse_name");
            data[1] = getIntent().getStringExtra("lesson");
        }
        return  data;
    }
}
