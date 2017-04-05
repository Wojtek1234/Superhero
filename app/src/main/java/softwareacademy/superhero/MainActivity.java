package softwareacademy.superhero;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent bindServiceIntent = new Intent(this,NormalService.class);
        bindService(bindServiceIntent,mConnection, Context.BIND_AUTO_CREATE);
        Intent intentServiceIntent = new Intent(this,UnnormalIntentService.class);
        startService(intentServiceIntent);
    }


    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            NormalService normalService=((NormalService.BinderImplementer)service).getService();
            LoggerHelper.log("ACTIVITY",String.valueOf(normalService.getMe()));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
