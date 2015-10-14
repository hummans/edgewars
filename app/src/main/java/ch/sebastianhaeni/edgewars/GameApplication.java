package ch.sebastianhaeni.edgewars;

import android.app.Application;
import android.content.Context;

/**
 * This class is used to get the app context without passing it through every method.
 */
public class GameApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        GameApplication.mContext = getApplicationContext();
    }

    /**
     * @return gets the app context
     */
    public static Context getAppContext() {
        return GameApplication.mContext;
    }
}
