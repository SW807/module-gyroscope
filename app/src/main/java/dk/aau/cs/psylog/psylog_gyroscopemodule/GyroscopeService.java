package dk.aau.cs.psylog.psylog_gyroscopemodule;

import dk.aau.cs.psylog.module_lib.SuperService;

public class GyroscopeService extends SuperService {
    @Override
    public void setSensor() {
        sensor = new GyroscopeListener(this);
    }
}
