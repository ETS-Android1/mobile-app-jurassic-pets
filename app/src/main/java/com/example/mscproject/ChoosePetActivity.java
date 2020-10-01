package com.example.mscproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ChoosePetActivity extends AppCompatActivity {

    public ChoosePetActivity (){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pet);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Context = ChoosePetActivity class, to open MainActivity class
                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                launchMainActivity.putExtra("PET_TYPE", PetType.Bronchiosaurus);
                startActivity(launchMainActivity);
            }
        };

        ImageButton btn = findViewById(R.id.btn_bront);
        btn.setOnClickListener(listener);

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Context = ChoosePetActivity class, to open MainActivity class
                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                launchMainActivity.putExtra("PET_TYPE", PetType.Trex);
                startActivity(launchMainActivity);
            }
        };

        ImageButton btn2 = findViewById(R.id.btn_trex);
        btn2.setOnClickListener(listener2);


        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Context = ChoosePetActivity class, to open MainActivity class
                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                launchMainActivity.putExtra("PET_TYPE", PetType.Triceratops);
                startActivity(launchMainActivity);
            }
        };

        ImageButton btn3 = findViewById(R.id.btn_tri);
        btn3.setOnClickListener(listener3);

    }

}
