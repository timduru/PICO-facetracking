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
    private Thread senderThread = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        createNotificationChannel();

        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .build();

        startForeground(1, notification);

        this.senderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UDPSocket send = new UDPSocket();
                    send.Client("192.168.1.101", 27000);

                    while (true) {
                        send.Send("hello?");
                        Thread.sleep(2000);
                    }
                } catch (Exception ex) {
                    System.err.printf(ex.toString()); // TODO ignore interrrupted sleep
                }
            }
        });
        this.senderThread.start();

        return START_NOT_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (this.senderThread != null) {
            this.senderThread.interrupt();
            this.senderThread = null; // it's closed now
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}