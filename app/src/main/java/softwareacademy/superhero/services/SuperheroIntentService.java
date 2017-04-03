package softwareacademy.superhero.services;

import android.app.IntentService;
import android.content.Intent;

import softwareacademy.superhero.MainActivity;
import softwareacademy.superhero.utils.Constans;
import softwareacademy.superhero.utils.SuperheroLog;

public class SuperheroIntentService extends IntentService {


    public static final String TAG = "INTENT SERVICE";

    public SuperheroIntentService() {
        super(TAG + "thread");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < Constans.TIMES; i++) {
            sleep();
            SuperheroLog.log(TAG, Thread.currentThread().getName() + " " + i);
            MainActivity.pushMessageToActivity(this,i);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(Constans.MILIS);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }


}
