package com.rogermiranda1000.pico_facetracking;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FacetrackingSender extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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

        return super.onStartCommand(intent, flags, startId);
    }
}