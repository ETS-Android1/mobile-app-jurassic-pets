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

    public class MainActivity extends AppCompatActivity implements SensorEventListener {
        private TextView tvSteps;
        private TextView tvName;
        private TextView tvCoins;
        private ImageView ivPet;

        private StepDetector simpleStepDetector;
        private SensorManager sensorManager;
        private Sensor accel;

        private static final String TEXT_NAME = "Dino name: ";
        private static final String TEXT_NUM_STEPS = "Number of Steps: ";
        private static final String TEXT_COINS = "Coins: ";

        private Pet myPet;
        private int coins;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Intent intent = getIntent();
            String petName = (String) intent.getSerializableExtra("PET_NAME");
            PetType petType = (PetType) intent.getSerializableExtra("PET_TYPE");

            myPet = new Pet(coins, petName, petType, null);

            ivPet = findViewById(R.id.main_iv_pet);

            switch (myPet.getType()) {
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

            tvName = findViewById(R.id.main_tv_name);
            tvSteps = findViewById(R.id.main_tv_steps);
            tvCoins = findViewById(R.id.main_tv_coins);

            Button btnStart = findViewById(R.id.main_btn_start);
            Button btnStop = findViewById(R.id.main_btn_stop);
            Button shopBtn = findViewById(R.id.main_btn_shop);
            shopBtn.setOnClickListener(submit);

            tvName.setText(TEXT_NAME + myPet.getName());

            // When the 'Wake up' button is pressed
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    tvSteps.setText("Ready?" + myPet.getName()); //may be able to remove this
                    tvCoins.setText("Pets coins: " + myPet.getCoins()); //may be able to remove this

                    switch (myPet.getType()) {
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

            //When the 'Stop' button is pressed
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

        View.OnClickListener submit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchShopActivity = new Intent(MainActivity.this, ShopActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MY_PET", myPet);
                launchShopActivity.putExtras(bundle);
                startActivity(launchShopActivity);
            }
        };

    }



