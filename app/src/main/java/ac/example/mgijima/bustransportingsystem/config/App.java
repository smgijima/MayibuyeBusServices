package ac.example.mgijima.bustransportingsystem.config;

import android.app.Application;
import android.content.Context;

/**
 * Created by Cornelious on 4/20/2016.
 */
public class App extends Application{
    private static Context context;
    private static App singleton;
    public void onCreate(){
        super.onCreate();
        App.context= getApplicationContext();
        singleton=this;
    }

    public static Context getContext() {
        return App.context;
    }
    public static  synchronized App getInstance(){
        return  singleton;
    }
}
