package softwareacademy.superhero;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ShowCounter{


    private TextView bindTextView, intentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindTextView = (TextView) findViewById(R.id.bind_service_text_view);
        intentTextView = (TextView) findViewById(R.id.intent_service_text_view);
        Intent bindServiceIntent = new Intent(this, NormalService.class);
        bindService(bindServiceIntent, mConnection, Context.BIND_AUTO_CREATE);
        Intent intentServiceIntent = new Intent(this, UnnormalIntentService.class);
        startService(intentServiceIntent);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            NormalService normalService = ((NormalService.BinderImplementer) service).getService();
            normalService.setCounter(MainActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

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
}
