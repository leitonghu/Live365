package com.tigger.live365;

import android.app.Application;

import com.qiniu.pili.droid.streaming.StreamingEnv;

/**
 *
 * Created by leitonghu on 17/2/3.
 */
public class StreamingApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        StreamingEnv.init(getApplicationContext());
    }
}
