package dk.aau.cs.psylog.psylog_gyroscopemodule;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;


public class TestActivity extends ActionBarActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = new Intent(this, Service.class);

        ToggleButton btn = new ToggleButton(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();

                if (on)
                    startService(intent);
                else
                    stopService(intent);
            }
        });

        setContentView(btn);
    }
}
