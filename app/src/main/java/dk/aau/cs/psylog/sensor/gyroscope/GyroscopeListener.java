package dk.aau.cs.psylog.sensor.gyroscope;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import dk.aau.cs.psylog.module_lib.DBAccessContract;
import dk.aau.cs.psylog.module_lib.ISensor;

public class GyroscopeListener implements SensorEventListener, ISensor {

    private SensorManager sensorManager;
    private Sensor sensor;

    private int sensorDelay;

    private ContentResolver resolver;

    public GyroscopeListener(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        resolver = context.getContentResolver();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float yaw = event.values[0];
            float pitch = event.values[1];
            float roll = event.values[2];

            Uri uri = Uri.parse(DBAccessContract.DBACCESS_CONTENTPROVIDER + "gyroscope_gyroscope");
            ContentValues values = new ContentValues();
            values.put("roll", roll);
            values.put("pitch", pitch);
            values.put("yaw", yaw);
            resolver.insert(uri, values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void startSensor() {
        sensorManager.unregisterListener(this);
        sensorManager.registerListener(this, sensor, sensorDelay);
    }

    public void stopSensor() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void sensorParameters(Intent intent) {
        sensorDelay = intent.getIntExtra("sensorDelay", SensorManager.SENSOR_DELAY_NORMAL); //default set to slowest update
    }
}
