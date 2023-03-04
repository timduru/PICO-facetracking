package com.rogermiranda1000.pico_facetracking;

import android.app.IntentService;
//import android.app.Service;
import android.content.Intent;
//import android.os.IBinder;

public class FacetrackingSender extends /*Service*/IntentService {
    public FacetrackingSender() {
        super("FacetrackingSender");
    }
    
    @Override
    protected void onHandleIntent(Intent intent) {
    /*@Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {*/
        try {
            final UDPSocket send = new UDPSocket();
            send.Client("192.168.1.101", 27000);

            new Thread(() -> {
                while (true) {
                    try {
                        send.Send("hello?");
                        Thread.sleep(5000);
                    } catch (Exception e) {}
                }
            }).start();
        } catch (Exception ex) {
            System.err.printf(ex.toString());
        }

        //return super.onStartCommand(intent, flags, startId);
    }
}