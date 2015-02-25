package dk.aau.cs.psylog.psylog_gyroscopemodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class GyroscopeService extends Service {
    private GyroscopeListener gyroscopeListener;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        gyroscopeListener.startSensor();
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gyroscopeListener = new GyroscopeListener(this);
    }

    @Override
    public void onDestroy() {
        gyroscopeListener.stopSensor();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
