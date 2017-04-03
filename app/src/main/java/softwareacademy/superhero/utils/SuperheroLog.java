package softwareacademy.superhero.utils;


import android.util.Log;

import softwareacademy.superhero.BuildConfig;

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
