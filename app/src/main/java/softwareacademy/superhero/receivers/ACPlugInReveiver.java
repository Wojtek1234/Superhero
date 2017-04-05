package softwareacademy.superhero.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import softwareacademy.superhero.services.PowerChangeService;
import softwareacademy.superhero.utils.SuperheroLog;

/**
 *
 */

public class ACPlugInReveiver extends BroadcastReceiver {

    private static final String TAG = "AC_RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        SuperheroLog.log(TAG, "power change");
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        PowerChangeService.startBatteryStatus(context, isCharging, usbCharge, acCharge);
    }
}
