package softwareacademy.superhero.utils;

import android.support.compat.BuildConfig;
import android.util.Log;

/**
 *
 */

public class SuperheroLog {
    public static void log(String tag, String text) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, text);
        }
    }
}
