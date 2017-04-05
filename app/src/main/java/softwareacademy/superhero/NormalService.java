package softwareacademy.superhero;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class NormalService extends Service {
    private BinderImplementer binderImplementer;
    public NormalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return binderImplementer;
    }

    public int getMe(){
        return 12345;
    }

    public class BinderImplementer extends Binder {
        public NormalService getService() {
            return NormalService.this;
        }
    }
}
