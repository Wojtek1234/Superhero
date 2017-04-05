package softwareacademy.superhero;


import android.util.Log;

public class LoggerHelper {

    public static void log(String tag, String text){
        if(BuildConfig.DEBUG){
            Log.d(tag, text);
        }
    }


}
