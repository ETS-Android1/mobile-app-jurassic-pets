package com.example.mscproject;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView tvSteps;
    private TextView tvTarget;
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
    private static final String TEXT_TARGET = "Step target: ";
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

        Toast wakeUpToast;
        wakeUpToast = Toast.makeText(getApplicationContext(), "Press the wake up button to start playing with " + myPet.getName(), Toast.LENGTH_SHORT);
        wakeUpToast.show();


        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main_rv_itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemListRecyclerAdaptor(this, myPet.getItems());
        recyclerView.setAdapter(adapter);


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Creates a new StepDetector object
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(myPet);

        tvName = findViewById(R.id.main_tv_name);
        tvSteps = findViewById(R.id.main_tv_steps);
        tvTarget = findViewById(R.id.main_tv_target);
        tvCoins = findViewById(R.id.main_tv_coins);
        tvLevel = findViewById(R.id.main_tv_level);
        pbHappiness = findViewById(R.id.main_pb_happiness);
        pbHappiness.setProgress(myPet.getHappiness());

        final Button btnWakeUp = findViewById(R.id.main_btn_start);
        final Button btnShop = findViewById(R.id.main_btn_shop);
        final Button btnFeed = findViewById(R.id.main_btn_feed);
        btnShop.setOnClickListener(shop);

        tvName.setText(TEXT_NAME + myPet.getName());
        tvSteps.setText(TEXT_NUM_STEPS + myPet.getSteps());
        tvTarget.setText(TEXT_TARGET + myPet.getTarget());
        tvCoins.setText(TEXT_COINS + myPet.getCoins());
        tvLevel.setText(TEXT_LEVEL + myPet.getLevel());

        if (myPet.getIsAwake()) {
            changePicture(R.drawable.char_main_01, R.drawable.char_main_02, R.drawable.char_main_03);
            btnWakeUp.setVisibility(View.INVISIBLE);
            wakeUpToast.cancel();
        }
        else {
            btnFeed.setVisibility(View.INVISIBLE);
            btnShop.setVisibility(View.INVISIBLE);
        }

        // When the 'Wake up' button is pressed
        btnWakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                btnFeed.setVisibility(View.VISIBLE);
                btnShop.setVisibility(View.VISIBLE);
                myPet.wakeUp();
                btnWakeUp.setVisibility(View.INVISIBLE);

                Toast.makeText(MainActivity.this, "Start moving to hit your step target and earn coins!", Toast.LENGTH_LONG).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Toast.makeText(getApplicationContext(), "You can spend your coins at the shop", Toast.LENGTH_LONG).show();
                    }
                }, 4000);

                changePicture(R.drawable.char_main_01, R.drawable.char_main_02, R.drawable.char_main_03);
                sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
            }
        });

        //When the 'Feed' button is pressed
        btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myPet.feed();
                adapter.notifyDataSetChanged();
                int petHappiness = myPet.getHappiness();
                pbHappiness.setProgress(petHappiness);

                if (petHappiness == 100) {
                    myPet.levelUp();
                    Toast levelUpToast;
                    levelUpToast = Toast.makeText(getApplicationContext(), "Your pet is now level " + myPet.getLevel(), Toast.LENGTH_SHORT);
                    levelUpToast.show();
                    pbHappiness.setProgress(0);
                }
                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                dbHelper.updatePet(myPet);
            }
        });
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