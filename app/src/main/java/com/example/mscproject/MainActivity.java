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

    //The steps are recorded and displayed to the user
    public class MainActivity extends AppCompatActivity implements SensorEventListener {
        private TextView tvView;
        private Button btnStart;
        private Button btnStop;
        private TextView levelView;

        // uses the StepDetector class and creates a variable
        private StepDetector simpleStepDetector;
        // uses the SensorManager class and creates a variable
        private SensorManager sensorManager;
        // uses the Sensor class and creates a variable
        private Sensor accel;
        // a string displaying the number of steps made
        private static final String TEXT_NUM_STEPS = "Number of Steps: ";

        // New pet level info
        private static final String TEXT_LEVEL = "Level: ";

        private Pet myPet;

        public MainActivity()
        {
            // Load the Pet from the Database
            // Pet loadedPet = database.GetPet();
            // myPet = loadedPet;

            myPet = new Pet (0,"Fido");
        }

        @Override
        // protected = can be called by any subclass within its class, but not by unrelated classes
        // Bundle = are generally used for passing data between various Android activities
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState); // super refers to the base class of savedInstanceState
            setContentView(R.layout.activity_main);

            // Get an instance of the SensorManager
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            // creates a new StepDetector object
            simpleStepDetector = new StepDetector();

            simpleStepDetector.registerListener(myPet);

            // findViewById is the reference to the id view in the layout
            tvView = findViewById(R.id.tv_steps);
            btnStart = findViewById(R.id.btn_start);
            btnStop = findViewById(R.id.btn_stop);
            levelView = findViewById(R.id.pet_level);

            // when the user presses the start button this action occurs and starts the step counter
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    tvView.setText("Ready?" + myPet.getName());
                    levelView.setText("Pets level: " + myPet.getLevel());
                    
                    sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                }
            });

            // when the user presses the stop buttion this action occurs and stops the step counter
            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    sensorManager.unregisterListener(MainActivity.this);
                }
            });
        }

        // method that contains a sensor variable from the Sensor class & a accuracy variable
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        // method that uses the event from the SensorEvent to update the number of steps
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                simpleStepDetector.updateAccel(
                        event.timestamp, event.values[0], event.values[1], event.values[2]);
            }

            tvView.setText(TEXT_NUM_STEPS + myPet.getSteps());
            levelView.setText(TEXT_LEVEL + myPet.getLevel());
        }

    }

