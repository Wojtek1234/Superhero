package softwareacademy.superhero.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import softwareacademy.superhero.MainActivity;
import softwareacademy.superhero.R;

/**
 *
 */

public class PowerChangeService extends Service {


    public static final String IS_CHARGING = "IS_CHARGING";
    public static final String IS_USB = "IS_USB";
    public static final String IS_AC = "IS_AC";

    public static void startBatteryStatus(Context context, boolean isCharging, boolean usb, boolean ac) {
        Intent service = new Intent(context, PowerChangeService.class);
        service.putExtra(IS_CHARGING, isCharging);
        service.putExtra(IS_USB, usb);
        service.putExtra(IS_AC, ac);
        context.startService(service);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        showNotification(intent);

        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification(Intent intent) {
        if(intent !=null){
            boolean isCharging = intent.getBooleanExtra(IS_CHARGING, false);
            boolean isUSB = intent.getBooleanExtra(IS_USB, false);
            boolean isAC = intent.getBooleanExtra(IS_AC, false);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_battery_charging_60_black_24dp)
                .setContentTitle(getString(R.string.powerChange))
                .setContentText(getString(R.string.battery_change))
                .setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
                .setAutoCancel(true)
                .setContentIntent(getPendingIntent());

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1234, notificationBuilder.build());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = MainActivity.createIntent(getApplicationContext());
        return PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
    }
}
