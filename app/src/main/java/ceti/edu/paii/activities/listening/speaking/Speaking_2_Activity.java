package ceti.edu.paii.activities.listening.speaking;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
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

public class Speaking_2_Activity extends AppCompatActivity {


    Button play;
    FloatingActionButton record, recordpause;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    final int REQUEST_PERMISSION_CODE = 1000;

    @SuppressLint("RestrictedApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaking_2_);


        if (!checkPermissionFromDivice()) {
            requestPermission();
        }
        //Botones
        play = findViewById(R.id.play_activity_speaking_2);
        record = findViewById(R.id.play_pause_recorder_activity_speaking_2);
        recordpause = findViewById(R.id.pause_recorder_activity_speaking_2);


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
                    Toast.makeText(Speaking_2_Activity.this, "Recording...",
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
                if (1==3) {
                    ////////////////////////////////////////////////////////////////////////REVISAR///////////////////////////////////////////////////////////////////////////////
                    recordpause.setEnabled(false);
                    record.setEnabled(true);

                    play.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                    mediaPlayer.release();
                    setupMediaRecorder();
                } else {
                    //
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(Speaking_2_Activity.this, "Playing...",
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
}
