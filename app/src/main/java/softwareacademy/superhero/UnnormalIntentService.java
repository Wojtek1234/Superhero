package softwareacademy.superhero;

import android.app.IntentService;
import android.content.Intent;

public class UnnormalIntentService extends IntentService {


    public static final String SERVICE_THREAD = "SERVICE_THREAD";

    public UnnormalIntentService() {
        super(SERVICE_THREAD);
    }



    @Override
    protected void onHandleIntent(Intent intent) {

        for(int i=0;i<100;i++){
            LoggerHelper.log("INTENT_SERVICE",
                    Thread.currentThread().getName() + " " + String.valueOf(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
