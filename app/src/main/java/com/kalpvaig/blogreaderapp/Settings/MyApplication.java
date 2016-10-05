package com.kalpvaig.blogreaderapp.Settings;

import android.app.Application;
import android.content.Context;


/**
 * Created by Razor on 10/11/2015.
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;


    @Override
    public void onCreate(){
        super.onCreate();
        sInstance=this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getApplcationContext(){
        return  sInstance.getApplicationContext();
    }
}
