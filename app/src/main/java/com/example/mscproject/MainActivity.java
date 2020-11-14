package com.example.mscproject;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements SensorEventListener, ItemListRecyclerAdaptor.ItemClickListener {
        private TextView tvSteps;
        private TextView tvName;
        private TextView tvCoins;
        private TextView tvLevel;
        private ImageView ivPet;
        private ProgressBar pbHappiness;

        private StepDetector simpleStepDetector;
        private SensorManager sensorManager;
        private Sensor accel;

        private static final String TEXT_NAME = "";
        private static final String TEXT_NUM_STEPS = "Number of Steps: ";
        private static final String TEXT_COINS = "Coins: ";
        private static final String TEXT_LEVEL = "Level: ";

        private Pet myPet;
        private ItemListRecyclerAdaptor adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            myPet = (Pet) bundle.getSerializable("MY_PET");

            ivPet = findViewById(R.id.main_iv_pet);

            changePicture(R.drawable.char_sleep_01, R.drawable.char_sleep_02, R.drawable.char_sleep_03);


            // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.main_rv_itemList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ItemListRecyclerAdaptor(this, myPet.getItems());
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);


            // Get an instance of the SensorManager
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            // creates a new StepDetector object
            simpleStepDetector = new StepDetector();
            simpleStepDetector.registerListener(myPet);

            tvName = findViewById(R.id.main_tv_name);
            tvSteps = findViewById(R.id.main_tv_steps);
            tvCoins = findViewById(R.id.main_tv_coins);
            tvLevel = findViewById(R.id.main_tv_level);
            pbHappiness = findViewById(R.id.main_pb_hapiness);
            pbHappiness.setProgress(myPet.getHappiness());

            final Button btnWakeUp = findViewById(R.id.main_btn_start);
            //Button btnStop = findViewById(R.id.main_btn_stop);
            Button btnShop = findViewById(R.id.main_btn_shop);
            final Button btnFeed = findViewById(R.id.main_btn_feed);
            btnShop.setOnClickListener(shop);

            tvName.setText(TEXT_NAME + myPet.getName());

            if (myPet.getIsAwake()) {
                changePicture(R.drawable.char_main_01, R.drawable.char_main_02, R.drawable.char_main_03);
                btnWakeUp.setVisibility(View.INVISIBLE);
            }
            else {
                btnFeed.setVisibility(View.INVISIBLE);
            }

            // When the 'Wake up' button is pressed
            btnWakeUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    tvSteps.setText("Ready?" + myPet.getName()); //may be able to remove this
                    tvCoins.setText("Pets coins: " + myPet.getCoins()); //may be able to remove this
                    btnFeed.setVisibility(arg0.VISIBLE);
                    myPet.wakeUp();
                    btnWakeUp.setVisibility(View.INVISIBLE);

                    changePicture(R.drawable.char_main_01, R.drawable.char_main_02, R.drawable.char_main_03);
                    sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                }
            });

            /*When the 'Stop' button is pressed - ** Add the reset task into here instead ** If removing effects - updateAccel
            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    sensorManager.unregisterListener(MainActivity.this);
                }
            });

             */

            //When the 'Feed' button is pressed
            btnFeed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    myPet.feed();
                    int petHappiness = myPet.getHappiness();
                    pbHappiness.setProgress(petHappiness);

                    if (petHappiness == 100) {
                        myPet.levelUp();
                        pbHappiness.setProgress(0);
                    }
                }
            });
        }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


    private void changePicture(int bronchiosaurusPic, int trexPic, int triceratopsPic) {
        switch (myPet.getType()) {
            case Bronchiosaurus:
                ivPet.setImageResource(bronchiosaurusPic);
                break;
            case Trex:
                ivPet.setImageResource(trexPic);
                break;
            case Triceratops:
                ivPet.setImageResource(triceratopsPic);
                break;
        }
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
            tvLevel.setText(TEXT_LEVEL + myPet.getLevel());
        }


        View.OnClickListener shop = new View.OnClickListener() {
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



