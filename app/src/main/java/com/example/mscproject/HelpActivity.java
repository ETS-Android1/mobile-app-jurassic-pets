package com.example.mscproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {
    private final View.OnClickListener home = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent HomeActivity = new Intent(HelpActivity.this, HomeActivity.class);
            startActivity(HomeActivity);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Button homeBtn = findViewById(R.id.btn_home);
        homeBtn.setOnClickListener(home);

    }
}