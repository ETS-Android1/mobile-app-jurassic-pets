package com.example.mscproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

    // The steps are recorded and displayed to the user
    public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
        private TextView tvView;
        private Button btnStart;
        private Button btnStop;


        private StepDetector simpleStepDetector;
        private SensorManager sensorManager;
        private Sensor accel;
        private static final String TEXT_NUM_STEPS = "Number of Steps: ";
        private int numSteps;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            // Get an instance of the SensorManager
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            simpleStepDetector = new StepDetector();
            simpleStepDetector.registerListener(this);

            tvView = findViewById(R.id.tv_steps);
            btnStart = findViewById(R.id.btn_start);
            btnStop = findViewById(R.id.btn_stop);



            btnStart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    numSteps = 0;
                    sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

                }
            });


            btnStop.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    sensorManager.unregisterListener(MainActivity.this);

                }
            });



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                simpleStepDetector.updateAccel(
                        event.timestamp, event.values[0], event.values[1], event.values[2]);
            }
        }

        @Override
        public void step(long timeNs) {
            numSteps++;
            tvView.setText(TEXT_NUM_STEPS + numSteps);
        }

    }

