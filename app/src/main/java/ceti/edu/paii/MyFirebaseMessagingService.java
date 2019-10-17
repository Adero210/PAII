package ceti.edu.paii;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ceti.edu.paii.view.ContainerActivity;
import ceti.edu.paii.view.ProfileActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FIrebase";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived (remoteMessage);

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener( new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.e(TAG, "getInstanceId failed", task.getException());
                    return;
                }
                String token = task.getResult().getToken();
                // Log and toast
                Log.e(TAG, token);
            }
        });

            String click_action = remoteMessage.getNotification().getClickAction();
            String fromUserId = remoteMessage.getData().get("user");
            Log.i(TAG,"click action : " + click_action);
            Log.i(TAG,"User from  : " + fromUserId);



        if(click_action.equals("activityProfile")){

                Log.e(TAG, remoteMessage.getNotification().getBody());
                Log.e(TAG, String.valueOf(remoteMessage.getData()));
                mostrarNotificacionP(remoteMessage.getNotification().getTitle(),
                        remoteMessage.getNotification().getBody(),click_action,fromUserId);
            Log.i(TAG,"entre aqui  : " + fromUserId);


            }else {
            Log.e(TAG, remoteMessage.getNotification().getBody());
            Log.e(TAG, String.valueOf(remoteMessage.getData()));
            mostrarNotificacion(remoteMessage.getNotification().getTitle(),
                    remoteMessage.getNotification().getBody());
        }

    }

    private void mostrarNotificacion(String title, String body) {

        Intent intent = new Intent(this, ContainerActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);



        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo).setContentTitle(title)
                .setContentText(body).setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        int mNotificationId = (int) System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(mNotificationId,notificacion.build());
    }

    private void mostrarNotificacionP(String title, String body, String click, String fromId) {

        Intent profileIntent = new Intent(click);
        profileIntent.putExtra("userId",fromId);
        profileIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                profileIntent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);



        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo).setContentTitle(title)
                .setContentText(body).setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        int mNotificationId = (int) System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(mNotificationId,notificacion.build());
    }



}
