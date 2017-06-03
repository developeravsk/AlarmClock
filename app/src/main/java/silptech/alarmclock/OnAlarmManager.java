package silptech.alarmclock;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.app.AlarmManager.ELAPSED_REALTIME;
import static android.os.SystemClock.elapsedRealtime;

/**
 * Created by abhi on 6/3/2017.
 */

public class OnAlarmManager extends IntentService {

    public static final String ACTION_TRIGGER_ALARM = "com.example.myapplication.ACTION_TRIGGER_ALARM";

    private static final String TAG = "OnAlarmReceiver";

    private NotificationManager nm;


    public OnAlarmManager() {
        super("OnAlarmManager");
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, mainActivityIntent, 0);

        //setting up notification
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("New Notification")
                .setContentText("This is a successfull app on service")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        nm.notify(0, notification);
        Log.i(TAG, "Alarm has begun");
    }

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//         user=bundle.getString("alarm");
//          }
//        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Intent mainActivityIntent = new Intent(this, MainActivity.class);
//        PendingIntent pIntent = PendingIntent.getActivity(this, 0, mainActivityIntent, 0);
//
//        //setting up notification
//        Notification notification = new NotificationCompat.Builder(this)
//                .setContentTitle("New Notification")
//                .setContentText(user)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentIntent(pIntent)
//                .build();
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        notification.defaults |= Notification.DEFAULT_VIBRATE;
//        notification.defaults |= Notification.DEFAULT_LIGHTS;
//        nm.notify(0, notification);
//        Toast.makeText(getApplicationContext(), "Alarm has begun", Toast.LENGTH_LONG).show();
//        Log.i(TAG, "Alarm has begun");
//         return START_NOT_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//    }

}
