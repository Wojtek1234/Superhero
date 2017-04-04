package softwareacademy.superhero;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

import softwareacademy.superhero.services.BindService;
import softwareacademy.superhero.services.SuperheroIntentService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, SuperheroIntentService.class));
        bindService(new Intent(this, BindService.class), mConnection, Context.BIND_AUTO_CREATE);
//        BIND_AUTO_CREATE – binds to an existing Service, creating the Service if it does not exist. onStartCommand() is not called
//        BIND_DEBUG_UNBIND – should only be used for debugging
//        BIND_NOT_FOREGROUND – won’t allow the Service’s priority to be raised to that of foreground priority. Its priority will only be raised to that of the client’s priority. This is only important if the client’s process is in the foreground and the Service’s process is in the background
//        BIND_IMPORTANT  - this service is very important to the client, so should be brought to the foreground process level when the client i
//        BIND_WAIVE_PRIORITY -  don't impact the scheduling or memory management priority of the target service's
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private BindService bindService;
    private boolean mBound;
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BindService.SuperheroBinder binder = (BindService.SuperheroBinder) service;
            bindService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
