package ceti.edu.paii.activities.listening;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ceti.edu.paii.R;

public class Listening_2_Activity extends AppCompatActivity {

    Button play1,play2,play3,play4;
    MediaPlayer mp,mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_2_);

        play1 = (Button) findViewById(R.id.play_pause_1__activity_listening_2);
        play2 = (Button) findViewById(R.id.play_pause_2__activity_listening_2);
        play3 = (Button) findViewById(R.id.play_pause_3__activity_listening_2);
        play4 = (Button) findViewById(R.id.play_pause_4__activity_listening_2);

        mp = MediaPlayer.create(this,R.raw.musica);
        mp2 = MediaPlayer.create(this,R.raw.musicromeo);
        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    play1.setBackgroundResource(R.drawable.play);
                }else{
                    mp.start();
                    play1.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp2.isPlaying()){
                    mp2.pause();
                    play2.setBackgroundResource(R.drawable.play);
                }else{
                    mp2.start();
                    play2.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        play3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    play3.setBackgroundResource(R.drawable.play);
                }else{
                    mp.start();
                    play3.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp2.isPlaying()){
                    mp2.pause();
                    play4.setBackgroundResource(R.drawable.play);
                }else{
                    mp2.start();
                    play4.setBackgroundResource(R.drawable.pause);
                }
            }
        });



    }
}
