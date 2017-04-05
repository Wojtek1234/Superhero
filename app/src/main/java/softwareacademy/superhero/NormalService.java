package softwareacademy.superhero;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class NormalService extends Service {
    public NormalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class BinderImplemter extends Binder{

    }
}
