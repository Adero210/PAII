package ceti.edu.paii.activities.listening;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ceti.edu.paii.R;

public class Listening_3_Activity extends AppCompatActivity {

    Button play_pause;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_3_);
        play_pause = (Button) findViewById(R.id.play_pause_activity_listening_3);

        mp = MediaPlayer.create(this,R.raw.musicromeo);
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    mp.pause();
                    play_pause.setBackgroundResource(R.drawable.play);
                }else{
                    mp.start();
                    play_pause.setBackgroundResource(R.drawable.pause);
                }
            }
        });
    }
}
