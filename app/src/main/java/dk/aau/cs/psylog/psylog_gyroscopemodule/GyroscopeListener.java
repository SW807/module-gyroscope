package dk.aau.cs.psylog.psylog_gyroscopemodule;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import dk.aau.cs.psylog.module_lib.ISensor;

public class GyroscopeListener implements SensorEventListener, ISensor {

    private SensorManager sensorManager;
    private Sensor sensor;

    private int sensorDelay;

    public GyroscopeListener(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            Log.i("Gyro-x: ", "" + axisX);
            Log.i("Gyro-y: ", "" + axisY);
            Log.i("Gyro-z: ", "" + axisZ);
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
        sensorDelay = intent.getIntExtra("sensorDelay",3); //default set to slowest update
    }
}
