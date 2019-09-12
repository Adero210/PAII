package ceti.edu.paii.activities.listening;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import ceti.edu.paii.R;

public class Listening_2_Activity extends AppCompatActivity {

    Button play1,play2,play3,play4;
    int totalTime, totaltime2,totaltime3,totaltime4;
    MediaPlayer mp,mp2,mp3,mp4;
    SeekBar seekBar1,seekbar2,seekbar3,seekbar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening_2_);

        play1 = (Button) findViewById(R.id.play_pause_1_activity_listening_2);
        play2 = (Button) findViewById(R.id.play_pause_2_activity_listening_2);
        play3 = (Button) findViewById(R.id.play_pause_3_activity_listening_2);
        play4 = (Button) findViewById(R.id.play_pause_4_activity_listening_2);

        mp = MediaPlayer.create(this,R.raw.musica);
        mp.setLooping(true);
        mp.seekTo(0);
        totalTime = mp.getDuration();

        mp2 = MediaPlayer.create(this,R.raw.musicromeo);
        mp2.setLooping(true);
        mp2.seekTo(0);
        totaltime2 = mp2.getDuration();

        mp3 = MediaPlayer.create(this,R.raw.musica);
        mp3.setLooping(true);
        mp3.seekTo(0);
        totaltime3 = mp3.getDuration();

        mp4 = MediaPlayer.create(this,R.raw.musicromeo);
        mp4.setLooping(true);
        mp4.seekTo(0);
        totaltime4 = mp4.getDuration();



        seekBar1 = findViewById(R.id.seekbar_1_activity_listening_2);
        seekBar1.setMax(totalTime);

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        seekbar2 = findViewById(R.id.seekbar_2_activity_listening_2);
        seekbar2.setMax(totalTime);

        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp2.seekTo(progress);
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

        seekbar3 = findViewById(R.id.seekbar_3_activity_listening_2);
        seekbar3.setMax(totalTime);

        seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp3.seekTo(progress);
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

        seekbar4 = findViewById(R.id.seekbar_4_activity_listening_2);
        seekbar4.setMax(totalTime);

        seekbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp4.seekTo(progress);
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


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp2 != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp2.getCurrentPosition();
                        handler2.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp3 != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp3.getCurrentPosition();
                        handler3.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp4 != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp4.getCurrentPosition();
                        handler4.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

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
                if(mp3.isPlaying()){
                    mp3.pause();
                    play3.setBackgroundResource(R.drawable.play);
                }else{
                    mp3.start();
                    play3.setBackgroundResource(R.drawable.pause);
                }
            }
        });
        play4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp4.isPlaying()){
                    mp4.pause();
                    play4.setBackgroundResource(R.drawable.play);
                }else{
                    mp4.start();
                    play4.setBackgroundResource(R.drawable.pause);
                }
            }
        });



    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            seekBar1.setProgress(currentPosition);

        }
    };
    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            seekbar2.setProgress(currentPosition);

        }
    };
    private Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            seekbar3.setProgress(currentPosition);

        }
    };
    private Handler handler4 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            seekbar4.setProgress(currentPosition);

        }
    };

    @Override
    protected void onDestroy() {
        mp.stop();
        mp2.stop();
        mp3.stop();
        mp4.stop();
        super.onDestroy();
    }
}
