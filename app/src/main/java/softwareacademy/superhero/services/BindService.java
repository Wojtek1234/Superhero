package softwareacademy.superhero.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import softwareacademy.superhero.BindedServiceView;
import softwareacademy.superhero.utils.Constans;
import softwareacademy.superhero.utils.SuperheroLog;

public class BindService extends Service {

    public static final String BIND_SERVICE = "BindService";
    private SuperheroBinder binder = new SuperheroBinder();
    private Handler handler;
    private int counter = 0;
    private Runnable runnable;
    private BindedServiceView bindedServiceView;

    public BindService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        startHandler();

        return binder;
    }

    private void startHandler() {
        handler = new Handler();
        runnable = () -> {
            counter++;
            if (counter < Constans.TIMES) {
                SuperheroLog.log(BIND_SERVICE, Thread.currentThread().getName() + " " + counter);
                handler.postDelayed(runnable, Constans.MILIS);
                if(bindedServiceView != null){
                    bindedServiceView.onChange(counter);
                }
            }else {
                handler.removeCallbacks(runnable);
            }
        };
        handler.postDelayed(runnable, 100);
    }

    public void setBindedServiceView(BindedServiceView bindedServiceView) {
        this.bindedServiceView = bindedServiceView;
    }

    public class SuperheroBinder extends Binder {
        public BindService getService() {
            return BindService.this;
        }
    }
}
