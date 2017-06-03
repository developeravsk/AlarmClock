package silptech.alarmclock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.ok);
        ComponentName receiver = new ComponentName(MainActivity.this, MyBroadCastReceiver.class);
        PackageManager pm = MainActivity.this.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        final EditText text = (EditText) findViewById(R.id.time);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(text.getText().toString());
                Intent intent = new Intent(getApplicationContext(),OnAlarmManager.class);
                PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(),
                        234324243, intent, 0);
//                JobScheduler jobScheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
////
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(i * 1000), pendingIntent);
                Toast.makeText(getApplicationContext(), "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(i*1000), pendingIntent);
                    Toast.makeText(getApplicationContext(), "Alarm set", Toast.LENGTH_LONG).show();
                } else {
                    // on devices below kitkat
                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(i*1000), pendingIntent);
                    Toast.makeText(getApplicationContext(), "Alarm set", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}


