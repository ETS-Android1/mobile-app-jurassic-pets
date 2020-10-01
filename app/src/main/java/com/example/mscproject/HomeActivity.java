package com.example.mscproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private View.OnClickListener choosePet = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent choosePetActivity = new Intent(HomeActivity.this, ChoosePetActivity.class);
            startActivity(choosePetActivity);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

       Button startBtn = findViewById(R.id.btn_start);
        startBtn.setOnClickListener(choosePet);

    }
}



