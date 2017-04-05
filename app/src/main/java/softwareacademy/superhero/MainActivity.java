package softwareacademy.superhero;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent bindServiceIntent = new Intent(this,NormalService.class);
        bindService(bindServiceIntent,mConnection,Intent.AUTO)
    }


    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            NormalService normalService=((NormalService.BinderImplementer)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
