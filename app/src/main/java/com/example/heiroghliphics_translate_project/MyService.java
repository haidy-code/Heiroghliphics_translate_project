package com.example.heiroghliphics_translate_project;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;

public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         intent = new Intent(getApplicationContext(), MainActivity.class);
       startActivity(intent);


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }
}
