package softwareacademy.superhero;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;

public class NormalService extends Service {
    private BinderImplementer binderImplementer  = new BinderImplementer();
    private ShowCounter counterShower;

    public NormalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        startHandler();
        return binderImplementer;
    }

    public int getMe(){
        return 12345;
    }

    public void setCounter(ShowCounter counterShower) {
        this.counterShower = counterShower;
    }

    public void removeCounter(){
        this.counterShower = null;
    }


    public class BinderImplementer extends Binder {
        public NormalService getService() {
            return NormalService.this;
        }
    }

    public static final String BIND_SERVICE = "BindService";
    private Handler handler;
    private int counter = 0;
    private Runnable runnable;






    private void startHandler() {
        handler = new Handler();
        runnable = getRunnable();
        handler.postDelayed(runnable, 100);
    }

    @NonNull
    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                counter++;
                if(counterShower !=null){
                    counterShower.showBind(counter);
                }
                if (counter < 100) {
                    LoggerHelper.log(BIND_SERVICE, Thread.currentThread().getName() + " " + counter);
                    handler.postDelayed(runnable, 100);
                } else {
                    handler.removeCallbacks(runnable);
                }
            }
        };
    }
}
