package ceti.edu.paii.activities.listening.speaking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import ceti.edu.paii.R;

public class Speaking_1_Activity extends AppCompatActivity {

    Button play, Playpause;
    SeekBar seekBar;
    FloatingActionButton record, recordpause;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer = new MediaPlayer();
    MediaPlayer mp;
    int totalTime;


    final int REQUEST_PERMISSION_CODE = 1000;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_1_);

        if (!checkPermissionFromDivice()) {
            requestPermission();
        }
        //Botones
        Playpause = findViewById(R.id.play_pause_activity_speaking_1);
        play = findViewById(R.id.play_activity_speaking_1);
        record = findViewById(R.id.play_pause_recorder_activity_speaking_1);
        recordpause = findViewById(R.id.pause_recorder_activity_speaking_1);

        // se crea media player del audio a escuchar
        mp = MediaPlayer.create(this, R.raw.musicromeo);
        mp.setLooping(true);
        mp.seekTo(0);
        totalTime = mp.getDuration();



        seekBar = findViewById(R.id.seekbar_activity_speaking_1);
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

        Playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    //
                    mp.pause();
                    Playpause.setBackgroundResource(R.drawable.play);
                } else {
                    //
                    mp.start();
                    Playpause.setBackgroundResource(R.drawable.pause);
                }
            }
        });

        recordpause.setVisibility(View.GONE);

        record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (checkPermissionFromDivice()) {


                        recordpause.setVisibility(View.VISIBLE);

                        pathSave = Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/"
                                + UUID.randomUUID().toString() + "_audio_record.3gp";

                        setupMediaRecorder();
                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        play.setEnabled(false);
                        Toast.makeText(Speaking_1_Activity.this, "Recording...",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        requestPermission();


                    }
                }

            });

            recordpause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 mediaRecorder.stop();
                 recordpause.setVisibility(View.GONE);
                 play.setEnabled(true);
                 record.setEnabled(true);
                }
            });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////////////////////////////////////////////////////////////////REVISAR///////////////////////////////////////////////////////////////////////////////
               if (1==3) {
                    //
                    recordpause.setEnabled(false);
                    record.setEnabled(true);

                    play.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                    mediaPlayer.release();
                    setupMediaRecorder();
                } else {

                    try {
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(Speaking_1_Activity.this, "Playing...",
                            Toast.LENGTH_SHORT).show();

        }
    }
        });
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO

        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permisos dados",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Permisos NO dados",Toast.LENGTH_SHORT).show();
                }
            }
            break;

        }
    }

    private boolean checkPermissionFromDivice() {
        int write_external_storafe_result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);
        return write_external_storafe_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;



    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            seekBar.setProgress(currentPosition);
        }
    };


    @Override
    protected void onDestroy() {
        mp.stop();
        super.onDestroy();
    }
}
