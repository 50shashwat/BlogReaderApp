package com.kalpvaig.blogreaderapp.Settings;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Razor on 10/11/2015.
 */

public class VolleySingleton {

    private RequestQueue requestQueue;
    private static VolleySingleton sInstance=null;

    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(MyApplication.getApplcationContext());

    }

    public static VolleySingleton getsInstance(){
        if(sInstance==null){
            sInstance= new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

}
