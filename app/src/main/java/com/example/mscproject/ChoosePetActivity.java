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
                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                launchMainActivity.putExtra("petType", PetType.Bronchiosaurus);
                startActivity(launchMainActivity);
            }
        };

        ImageButton btn = findViewById(R.id.btn_bront);
        btn.setOnClickListener(listener);

    }

}
