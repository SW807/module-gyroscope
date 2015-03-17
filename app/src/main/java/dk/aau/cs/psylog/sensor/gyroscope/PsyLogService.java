package dk.aau.cs.psylog.sensor.gyroscope;

import dk.aau.cs.psylog.module_lib.SensorService;

public class PsyLogService extends SensorService {
    @Override
    public void setSensor() {
        sensor = new GyroscopeListener(this);
    }
}
