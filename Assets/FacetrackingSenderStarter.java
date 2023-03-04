package com.rogermiranda1000.pico_facetracking;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

// @ref https://stackoverflow.com/a/38369904/9178470
public final class FacetrackingSenderStarter {
    private static Activity myActivity;

    // Called From C# to get the Activity Instance
    public static void receiveActivityInstance(Activity tempActivity) {
        myActivity = tempActivity;
    }

    public static void StartCheckerService() {
        myActivity.startService(new Intent(myActivity, FacetrackingSender.class));
    }
}