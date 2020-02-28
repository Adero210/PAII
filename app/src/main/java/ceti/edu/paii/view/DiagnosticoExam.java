package ceti.edu.paii.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import ceti.edu.paii.R;
import ceti.edu.paii.activities.listening.Listening_1_Activity;
import ceti.edu.paii.activities.listening.Listening_4_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_1_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_2_Activity;
import ceti.edu.paii.activities.listening.grammar.Grammar_3_Activity;
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
import ceti.edu.paii.comun.comun;

public class DiagnosticoExam extends AppCompatActivity {

    private String idCurso;
    private String language;

    private String lesson = "1";
    private String tipo = "";
    private int numElige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico_exam);

        numElige = Integer.parseInt(comun.aleatorio(6));

        language = getIntent().getStringExtra("curse_len");
        idCurso = getIntent().getStringExtra("curse_id");

        //lesson = comun.aleatorio(3);

        if(numElige == 0){
            vocabulario();
        }else if(numElige == 1){
            sepaking();
        }else if(numElige == 2){
            writing();
        }else if(numElige == 3){
            reading();
        }else if(numElige == 4){
            listening();
        }else if(numElige == 5){
            grammar();
        }
    }

    private void grammar() {
        tipo = "5";
        String num ="";
        String  actHechas = "1";
        String calificacion = "0";
        String actB1 = "1,2,3,4,0";
        String actB2 = "1,2,3,4,0";
        String actB3 = "1,2,3,4,0";

        num = comun.aleatorio(4);
        Log.i("numeroRamdon",num);
        switch (num){
            case "0":
                Intent i = new Intent(DiagnosticoExam.this, Grammar_1_Activity.class);
                i.putExtra("curso",language);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);

                i.putExtra("calificacion",calificacion);
                i.putExtra("actividad",actHechas);
                i.putExtra("boceto1",actB1);
                i.putExtra("boceto2",actB2);
                i.putExtra("boceto3",actB3);
                startActivity(i);
                break;

            case "1":
                Intent intent = new Intent(DiagnosticoExam.this, Grammar_2_Activity.class);
                intent.putExtra("curso",language);
                intent.putExtra("lesson",lesson);
                intent.putExtra("tipo",tipo);

                intent.putExtra("calificacion",calificacion);
                intent.putExtra("actividad",actHechas);
                intent.putExtra("boceto1",actB1);
                intent.putExtra("boceto2",actB2);
                intent.putExtra("boceto3",actB3);
                startActivity(intent);
                break;

            case "2":
                Intent intent1 = new Intent(DiagnosticoExam.this, Grammar_3_Activity.class);
                intent1.putExtra("curso",language);
                intent1.putExtra("lesson",lesson);
                intent1.putExtra("tipo",tipo);

                intent1.putExtra("calificacion",calificacion);
                intent1.putExtra("actividad",actHechas);
                intent1.putExtra("boceto1",actB1);
                intent1.putExtra("boceto2",actB2);
                intent1.putExtra("boceto3",actB3);
                startActivity(intent1);
                break;

            case "3":
                Intent intent2 = new Intent(DiagnosticoExam.this, Grammar_3_Activity.class);
                intent2.putExtra("curso",language);
                intent2.putExtra("lesson",lesson);
                intent2.putExtra("tipo",tipo);

                intent2.putExtra("calificacion",calificacion);
                intent2.putExtra("actividad",actHechas);
                intent2.putExtra("boceto1",actB1);
                intent2.putExtra("boceto2",actB2);
                intent2.putExtra("boceto3",actB3);
                startActivity(intent2);
                break;
        }
    }

    private void listening() {
        tipo="4";
        String num ="";
        String actHechas = "1";
        String calificacion = "0";
        String actB2 = "1,2,3,4,0,5,6,7,8,9";
        String actB1 = "1,2,3,4,0";

        num = comun.aleatorio(4);
        Log.i("numeroRamdon",num);
        switch (num){
            case "0":
                Intent i = new Intent(DiagnosticoExam.this, Listening_1_Activity.class);
                i.putExtra("curso",language);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);

                i.putExtra("calificacion",calificacion);
                i.putExtra("actividad",actHechas);
                i.putExtra("boceto1",actB1);
                i.putExtra("boceto2",actB2);
                startActivity(i);
                break;

            case "1":
                Intent intent = new Intent(DiagnosticoExam.this, Listening_1_Activity.class);
                intent.putExtra("curso",language);
                intent.putExtra("lesson",lesson);
                intent.putExtra("tipo",tipo);

                intent.putExtra("calificacion",calificacion);
                intent.putExtra("actividad",actHechas);
                intent.putExtra("boceto1",actB1);
                intent.putExtra("boceto2",actB2);
                startActivity(intent);
                break;

            case "2":
                Intent intent1 = new Intent(DiagnosticoExam.this, Listening_4_Activity.class);
                intent1.putExtra("curso",language);
                intent1.putExtra("lesson",lesson);
                intent1.putExtra("tipo",tipo);

                intent1.putExtra("calificacion",calificacion);
                intent1.putExtra("actividad",actHechas);
                intent1.putExtra("boceto1",actB1);
                intent1.putExtra("boceto2",actB2);
                startActivity(intent1);
                break;

            case "3":
                Intent intent2 = new Intent(DiagnosticoExam.this, Listening_4_Activity.class);
                intent2.putExtra("curso",language);
                intent2.putExtra("lesson",lesson);
                intent2.putExtra("tipo",tipo);

                intent2.putExtra("calificacion",calificacion);
                intent2.putExtra("boceto1",actB1);
                intent2.putExtra("boceto2",actB2);
                startActivity(intent2);
                break;
        }
    }

    private void reading() {
        tipo = "1";
        String num = "";
        String  actHechas = "1";
        String calificacion = "0";
        String actB1 = "1,2,3,4,0";
        String actB2 = "1,2,3,4,0";
        String actB3 = "1,2,0";
        String actB4 = "1,2,0";
        num = comun.aleatorio(4);
        Log.i("numeroRamdon",num);
        switch (num){
            case "0":
                Intent i = new Intent(DiagnosticoExam.this, Reading_1_Activity.class);
                i.putExtra("curso",language);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);
                i.putExtra("calificacion",calificacion);
                i.putExtra("actividad",actHechas);
                i.putExtra("boceto1",actB1);
                i.putExtra("boceto2",actB2);
                i.putExtra("boceto3",actB3);
                i.putExtra("boceto4",actB4);
                startActivity(i);
                startActivity(i);
                break;

            case "1":
                Intent intent = new Intent(DiagnosticoExam.this, Reading_Paragraph_Activity.class);
                intent.putExtra("curso",language);
                intent.putExtra("lesson",lesson);
                intent.putExtra("tipo",tipo);
                intent.putExtra("calificacion",calificacion);
                intent.putExtra("actividad",actHechas);
                intent.putExtra("boceto1",actB1);
                intent.putExtra("boceto2",actB2);
                intent.putExtra("boceto3",actB3);
                intent.putExtra("boceto4",actB4);
                startActivity(intent);
                break;

            case "2":
                Intent intent1 = new Intent(DiagnosticoExam.this, Reading_paragraph_2_Activity.class);
                intent1.putExtra("curso",language);
                intent1.putExtra("lesson",lesson);
                intent1.putExtra("tipo",tipo);
                intent1.putExtra("calificacion",calificacion);
                intent1.putExtra("actividad",actHechas);
                intent1.putExtra("boceto1",actB1);
                intent1.putExtra("boceto2",actB2);
                intent1.putExtra("boceto3",actB3);
                intent1.putExtra("boceto4",actB4);
                startActivity(intent1);
                break;

            case "3":
                Intent intent2 = new Intent(DiagnosticoExam.this, Reading_4_Activity.class);
                intent2.putExtra("curso",language);
                intent2.putExtra("lesson",lesson);
                intent2.putExtra("tipo",tipo);
                intent2.putExtra("calificacion",calificacion);
                intent2.putExtra("actividad",actHechas);
                intent2.putExtra("boceto1",actB1);
                intent2.putExtra("boceto2",actB2);
                intent2.putExtra("boceto3",actB3);
                intent2.putExtra("boceto4",actB4);
                startActivity(intent2);
                break;
        }
    }

    private void writing() {
        tipo = "0";
        String num ="";
        String  actHechas = "1";
        String calificacion = "0";
        String actB1 = "1,2,3,4,0";
        String actB2 = "1,2,3,4,0";
        String actB3 = "1,2,3,4,0";

        num = comun.aleatorio(4);
        Log.i("numeroRamdon",num);
        switch (num){
            case "0":
                Intent i = new Intent(DiagnosticoExam.this, Writing_1_Activity.class);
                i.putExtra("curso",language);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);

                i.putExtra("calificacion",calificacion);
                i.putExtra("actividad",actHechas);
                i.putExtra("boceto1",actB1);
                i.putExtra("boceto2",actB2);
                i.putExtra("boceto3",actB3);
                startActivity(i);
                break;

            case "1":
                Intent intent = new Intent(DiagnosticoExam.this, Writing_2_Activity.class);
                intent.putExtra("curso",language);
                intent.putExtra("lesson",lesson);
                intent.putExtra("tipo",tipo);

                intent.putExtra("calificacion",calificacion);
                intent.putExtra("actividad",actHechas);
                intent.putExtra("boceto1",actB1);
                intent.putExtra("boceto2",actB2);
                intent.putExtra("boceto3",actB3);
                startActivity(intent);
                break;

            case "2":
                Intent intent1 = new Intent(DiagnosticoExam.this, Writing_3_Activity.class);
                intent1.putExtra("curso",language);
                intent1.putExtra("lesson",lesson);
                intent1.putExtra("tipo",tipo);

                intent1.putExtra("calificacion",calificacion);
                intent1.putExtra("actividad",actHechas);
                intent1.putExtra("boceto1",actB1);
                intent1.putExtra("boceto2",actB2);
                intent1.putExtra("boceto3",actB3);
                startActivity(intent1);
                break;

            case "3":
                Intent intent2 = new Intent(DiagnosticoExam.this, Writing_2_Activity.class);
                intent2.putExtra("curso",language);
                intent2.putExtra("lesson",lesson);
                intent2.putExtra("tipo",tipo);
                intent2.putExtra("calificacion",calificacion);
                intent2.putExtra("actividad",actHechas);
                intent2.putExtra("boceto1",actB1);
                intent2.putExtra("boceto2",actB2);
                intent2.putExtra("boceto3",actB3);
                startActivity(intent2);
                break;
        }
    }

    private void sepaking() {
        tipo = "3";
        String num ="";
        String  actHechas = "1";
        String calificacion = "0";
        String actB1 = "1,2,3,4,0";
        String actB2 = "1,2,3,4,0";
        String actB3 = "1,2,3,4,0";

        num = comun.aleatorio(4);
        Log.i("numeroRamdon",num);
        switch (num){
            case "0":
                Intent i = new Intent(DiagnosticoExam.this, Speaking_1_Activity.class);
                i.putExtra("curso",language);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);

                i.putExtra("calificacion",calificacion);
                i.putExtra("actividad",actHechas);
                i.putExtra("boceto1",actB1);
                i.putExtra("boceto2",actB2);
                i.putExtra("boceto3",actB3);
                startActivity(i);
                break;

            case "1":
                Intent intent = new Intent(DiagnosticoExam.this, Speaking_2_Activity.class);
                intent.putExtra("curso",language);
                intent.putExtra("lesson",lesson);
                intent.putExtra("tipo",tipo);

                intent.putExtra("calificacion",calificacion);
                intent.putExtra("actividad",actHechas);
                intent.putExtra("boceto1",actB1);
                intent.putExtra("boceto2",actB2);
                intent.putExtra("boceto3",actB3);
                startActivity(intent);
                break;

            case "2":
                Intent intent1 = new Intent(DiagnosticoExam.this, Speaking_3_Activity.class);
                intent1.putExtra("curso",language);
                intent1.putExtra("lesson",lesson);
                intent1.putExtra("tipo",tipo);

                intent1.putExtra("calificacion",calificacion);
                intent1.putExtra("actividad",actHechas);
                intent1.putExtra("boceto1",actB1);
                intent1.putExtra("boceto2",actB2);
                intent1.putExtra("boceto3",actB3);
                startActivity(intent1);
                break;

            case "3":
                Intent intent2 = new Intent(DiagnosticoExam.this, Speaking_1_Activity.class);
                intent2.putExtra("curso",language);
                intent2.putExtra("lesson",lesson);
                intent2.putExtra("tipo",tipo);

                intent2.putExtra("calificacion",calificacion);
                intent2.putExtra("actividad",actHechas);
                intent2.putExtra("boceto1",actB1);
                intent2.putExtra("boceto2",actB2);
                intent2.putExtra("boceto3",actB3);
                startActivity(intent2);
                break;
        }
    }

    private void vocabulario() {
        tipo="2";
        String num ="";
        String  actHechas = "1";
        String calificacion = "0";
        String actB1 = "1,2,3,4,0";
        String actB2 = "1,2,3,4,0";
        String actB3 = "1,2,3,4,0";
        String actB4 = "1,2,3,4,0";

        num = comun.aleatorio(4);
        Log.i("numeroRamdon",num);
        switch (num){
            case "0":
                Intent i = new Intent(DiagnosticoExam.this, Vocabulary_1_Activity.class);
                i.putExtra("curso",language);
                i.putExtra("lesson",lesson);
                i.putExtra("tipo",tipo);
                i.putExtra("calificacion",calificacion);
                i.putExtra("actividad",actHechas);
                i.putExtra("boceto1",actB1);
                i.putExtra("boceto2",actB2);
                i.putExtra("boceto3",actB3);
                i.putExtra("boceto4",actB4);
                startActivity(i);
                break;

            case "1":
                Intent intent = new Intent(DiagnosticoExam.this, Vocabulary_2_Activity.class);
                intent.putExtra("curso",language);
                intent.putExtra("lesson",lesson);
                intent.putExtra("tipo",tipo);
                intent.putExtra("calificacion",calificacion);
                intent.putExtra("actividad",actHechas);
                intent.putExtra("boceto1",actB1);
                intent.putExtra("boceto2",actB2);
                intent.putExtra("boceto3",actB3);
                intent.putExtra("boceto4",actB4);
                startActivity(intent);
                break;

            case "2":
                Intent intent1 = new Intent(DiagnosticoExam.this, Vocabulary_3_Activity.class);
                intent1.putExtra("curso",language);
                intent1.putExtra("lesson",lesson);
                intent1.putExtra("tipo",tipo);
                intent1.putExtra("calificacion",calificacion);
                intent1.putExtra("actividad",actHechas);
                intent1.putExtra("boceto1",actB1);
                intent1.putExtra("boceto2",actB2);
                intent1.putExtra("boceto3",actB3);
                intent1.putExtra("boceto4",actB4);
                startActivity(intent1);
                break;

            case "3":
                Intent intent2 = new Intent(DiagnosticoExam.this, Vocabulary_4_Activity.class);

                intent2.putExtra("curso",language);
                intent2.putExtra("lesson",lesson);
                intent2.putExtra("tipo",tipo);

                intent2.putExtra("calificacion",calificacion);
                intent2.putExtra("actividad",actHechas);
                intent2.putExtra("boceto1",actB1);
                intent2.putExtra("boceto2",actB2);
                intent2.putExtra("boceto3",actB3);
                intent2.putExtra("boceto4",actB4);
                startActivity(intent2);
                break;
        }
    }
}
