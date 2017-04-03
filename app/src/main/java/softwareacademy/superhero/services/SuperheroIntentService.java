package softwareacademy.superhero.services;

import android.app.IntentService;
import android.content.Intent;

import softwareacademy.superhero.utils.SuperheroLog;

public class SuperheroIntentService extends IntentService {


    public static final String TAG = "INTENT SERVICE";

    public SuperheroIntentService() {
        super(TAG + "thread");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 100; i++) {
            sleep();
            SuperheroLog.log(TAG, Thread.currentThread().getName() + " " + i);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }


}
