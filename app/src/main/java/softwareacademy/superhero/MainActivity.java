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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ShowCounter {


    private TextView bindTextView, intentTextView;
    private NormalService normalService;
    private CounterBroadcastReceiver counterBroadcastReceiver;
    private boolean isRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindTextView = (TextView) findViewById(R.id.bind_service_text_view);
        intentTextView = (TextView) findViewById(R.id.intent_service_text_view);
//        Intent bindServiceIntent = new Intent(this, NormalService.class);
//        bindService(bindServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
        Intent intentServiceIntent = new Intent(this, UnnormalIntentService.class);
        startService(intentServiceIntent);

        findViewById(R.id.unbind_button).setOnClickListener(v -> unbindMainActivity());

        counterBroadcastReceiver = new CounterBroadcastReceiver();
        findViewById(R.id.register_button).setOnClickListener(v -> {
            if (!isRegister) {
                registerCounterReceiver();

            } else {
                unregisterReceiver(counterBroadcastReceiver);
            }
            isRegister = !isRegister;
        });


    }

    private void registerCounterReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WYSYLAM_COUNTERA_Z_INTENT_SERVICA);
        registerReceiver(counterBroadcastReceiver, intentFilter);
    }

    private void unbindMainActivity() {
        if (bindToService) {
            bindToService = false;
            normalService.removeCounter();
            unbindService(mConnection);
        }
    }


    private boolean bindToService = false;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindMainActivity();
        unregisterReceiver(counterBroadcastReceiver);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            normalService = ((NormalService.BinderImplementer) service).getService();
            normalService.setCounter(MainActivity.this);
            bindToService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bindToService = false;
        }
    };

    @Override
    public void showIntent(int counter) {
        intentTextView.setText(new StringBuilder().append("intent service ")
                .append(counter).toString());
    }

    @Override
    public void showBind(int counter) {
        bindTextView.setText(new StringBuilder().append("bind service ")
                .append(counter).toString());
    }

    private static final String WYSYLAM_COUNTERA_Z_INTENT_SERVICA = "Wysylam_countera_z_intent_servica";
    private static String COUNTER_KEY = "counter_key";


    public static void sendBroadcast(Service service, int counter) {
        Intent intent = new Intent(WYSYLAM_COUNTERA_Z_INTENT_SERVICA);
        intent.putExtra(COUNTER_KEY, counter);
        service.sendBroadcast(intent);

    }

    public class CounterBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getIntExtra(COUNTER_KEY, -100);
            MainActivity.this.showIntent(counter);
        }
    }
}
