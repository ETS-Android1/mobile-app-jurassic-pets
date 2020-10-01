package com.example.mscproject;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

//The steps are recorded and displayed to the user
    public class MainActivity extends AppCompatActivity implements SensorEventListener {
        private TextView tvSteps;
        private Button btnStart;
        private Button btnStop;
        private TextView tvCoins;
        private ImageView ivPet;
        //private TextView tvLevel;

        // uses the StepDetector class and creates a variable
        private StepDetector simpleStepDetector;
        // uses the SensorManager class and creates a variable
        private SensorManager sensorManager;
        // uses the Sensor class and creates a variable
        private Sensor accel;
        // a string displaying the number of steps made
        private static final String TEXT_NUM_STEPS = "Number of Steps: ";

        // New pet level info
        private static final String TEXT_COINS = "Coins: ";
        //private static final String TEXT_LEVEL = "Level: ";

        private Pet myPet;

        @Override
        // protected = can be called by any subclass within its class, but not by unrelated classes
        // Bundle = are generally used for passing data between various Android activities
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState); // super refers to the base class of savedInstanceState
            setContentView(R.layout.activity_main);


            Intent intent = getIntent();
            PetType petType = (PetType)intent.getSerializableExtra("PET_TYPE");

            myPet = new Pet (0,"Fido", petType);


            //Add logic to this once intent is added (if specific type then change image...)
            ivPet = findViewById(R.id.petImageView);
            switch (myPet.getPetType()) {
                case Bronchiosaurus:
                    ivPet.setImageResource(R.drawable.char_sleep_01);
                    break;
                case Trex:
                    ivPet.setImageResource(R.drawable.char_sleep_02);
                    break;
                case Triceratops:
                    ivPet.setImageResource(R.drawable.char_sleep_03);
                    break;
            }

            // Get an instance of the SensorManager
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            // creates a new StepDetector object
            simpleStepDetector = new StepDetector();

            simpleStepDetector.registerListener(myPet);


            // findViewById is the reference to the id view in the layout
            tvSteps = findViewById(R.id.tv_steps);
            btnStart = findViewById(R.id.btn_start);
            btnStop = findViewById(R.id.btn_stop);
            tvCoins = findViewById(R.id.pet_coins);

            // when the user presses the start button this action occurs and starts the step counter
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    tvSteps.setText("Ready?" + myPet.getName());
                    tvCoins.setText("Pets coins: " + myPet.getCoins());

                    switch (myPet.getPetType()) {
                        case Bronchiosaurus:
                            ivPet.setImageResource(R.drawable.char_main_01);
                            break;
                        case Trex:
                            ivPet.setImageResource(R.drawable.char_main_02);
                            break;
                        case Triceratops:
                            ivPet.setImageResource(R.drawable.char_main_03);
                            break;
                    }

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

            tvSteps.setText(TEXT_NUM_STEPS + myPet.getSteps());
            tvCoins.setText(TEXT_COINS + myPet.getCoins());
        }

    }

