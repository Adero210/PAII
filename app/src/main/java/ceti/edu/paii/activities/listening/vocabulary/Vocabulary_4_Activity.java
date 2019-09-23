package ceti.edu.paii.activities.listening.vocabulary;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import ceti.edu.paii.R;

public class Vocabulary_4_Activity extends AppCompatActivity {


    Button play_pause;
    //AUDIO
    TextView tiempo, tiempo2;
    SeekBar seekBar;
    MediaPlayer mp;
    int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_4_);

        play_pause = findViewById(R.id.play_pause_activity_vocabulary_4);
        tiempo = findViewById(R.id.tiempo_vocabulary_4);
        tiempo2 = findViewById(R.id.tiempo2_vocabulary_4);


        mp = MediaPlayer.create(this, R.raw.musicromeo);
        mp.setLooping(true);
        mp.seekTo(0);
        totalTime = mp.getDuration();

        seekBar = findViewById(R.id.seekbar_activity_vocabulary_4);
        seekBar.setMax(totalTime);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    //
                    mp.pause();
                    play_pause.setBackgroundResource(R.drawable.play);
                } else {
                    //
                    mp.start();
                    play_pause.setBackgroundResource(R.drawable.pause);
                }
            }
        });



    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            seekBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            tiempo.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            tiempo2.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }


    @Override
    protected void onDestroy() {
        mp.stop();
        super.onDestroy();
    }
}
