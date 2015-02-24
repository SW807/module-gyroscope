package dk.aau.cs.psylog.psylog_gyroscopemodule;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class GyroscopeListener implements SensorEventListener {

  private SensorManager sensorManager;
  private Sensor sensor;

  public GyroscopeListener(Context context) {
    sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    sensorManager.registerListener(this, sensor, 2000000);
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    float axisX = event.values[0];
    float axisY = event.values[1];
    float axisZ = event.values[2];

    Log.i("Gyro-x: ", "" + axisX);
    Log.i("Gyro-y: ", "" + axisY);
    Log.i("Gyro-z: ", "" + axisZ);
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) { }

  public void stopSensor() {
    sensorManager.unregisterListener(this);
  }
}
