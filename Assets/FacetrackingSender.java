package com.rogermiranda1000.pico_facetracking;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

public class FacetrackingSender extends Service {
    private static final String CHANNEL_ID = "ForegroundServiceChannel";
    private boolean isRunning = false;
    private Thread senderThread = null;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            final UDPSocket send = new UDPSocket();
            send.Client("192.168.1.101", 27000);
            isRunning = true;
            senderThread = new Thread(() -> {
                while (/*isRunning*/true) { // TODO as it's not an atomic bool this won't work
                    try {
                        send.Send("hello?");
                        Thread.sleep(5000);
                    } catch (Exception e) {}
                }
            });
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (this.senderThread == null) return START_STICKY; // onCreate failed to setup senderThread

        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("FacetrackingSender")
                .setContentText("Sending facetracking data")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build();
        startForeground(1, notification);

        if (!senderThread.isAlive()) senderThread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (senderThread == null) return;
        
        isRunning = false;
        try {
            senderThread.join();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}