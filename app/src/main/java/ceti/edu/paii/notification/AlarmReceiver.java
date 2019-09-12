package ceti.edu.paii.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

import ceti.edu.paii.MainActivity;
import ceti.edu.paii.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId = intent.getIntExtra("notificationId",
                0);
        String message = intent.getStringExtra("todo");




        Log.i("aaaa", String.valueOf(notificationId));

        Log.i("aaaa", message);

        mostNot(context,message);





    }

    public void mostNot(Context context, String message){


        Intent mainIntent = new Intent(context, MainActivity.class);

        PendingIntent contentIntent = PendingIntent.getActivity(context,
                1, mainIntent, 0);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.logo_transparent)
                .setContentTitle("Es tiempo")
                .setContentText(message)
                .setSound(soundUri)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent);

        NotificationManager myNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(0,builder.build());



    }
}
