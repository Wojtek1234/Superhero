package softwareacademy.superhero;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import softwareacademy.superhero.services.BindService;
import softwareacademy.superhero.services.SuperheroIntentService;
import softwareacademy.superhero.utils.ViewsUtils;

public class MainActivity extends AppCompatActivity implements BindedServiceView {

    private static final String SEND_BROADCAST = "broadcastSend";
    public static final String INT_VALUE = "int_value";
    private SimpleBroadcastReceiver receiver;

    private AppCompatTextView intentServiceView, bindServiceView;

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
        intentServiceView = ViewsUtils.findView(this, R.id.intent_value);
        bindServiceView = ViewsUtils.findView(this, R.id.bind_value);
        receiver = new SimpleBroadcastReceiver();
        this.registerReceiver(receiver, new IntentFilter(SEND_BROADCAST));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mConnection);
        unregisterReceiver(receiver);
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
            BindService.SuperheroBinder binder = (BindService.SuperheroBinder) service;
            bindService = binder.getService();
            mBound = true;
            bindService.setBindedServiceView(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };




    public static void pushMessageToActivity(Service service, int value) {
        Intent intent = new Intent();
        intent.setAction(SEND_BROADCAST);
        intent.putExtra(INT_VALUE, value);
        service.sendBroadcast(intent);
    }

    @Override
    public void onChange(int value) {
        setValueOnTextView(bindServiceView, value,"bind ");
    }


    public class SimpleBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int value = intent.getIntExtra(INT_VALUE, 0);
            setValueOnTextView(intentServiceView, value,"intent ");
        }
    }

    private void setValueOnTextView(TextView textView, int value,String text) {
        textView.setText(new StringBuilder().append(text).append(value).toString());
    }
}
