package com.example.mscproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    //Choose pet page
    private final View.OnClickListener choosePet = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent choosePetActivity = new Intent(HomeActivity.this, ChoosePetActivity.class);
            startActivity(choosePetActivity);
        }
    };
    //About page
    private final View.OnClickListener aboutPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent aboutActivity = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(aboutActivity);
        }
    };
    //Help page
    private final View.OnClickListener helpPage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent helpActivity = new Intent(HomeActivity.this, HelpActivity.class);
            startActivity(helpActivity);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        DatabaseHelper dbHelper = new DatabaseHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //Start button
        Button startBtn = findViewById(R.id.home_btn_start);
        startBtn.setOnClickListener(choosePet);

        //About button
        Button aboutBtn = findViewById(R.id.home_btn_about);
        aboutBtn.setOnClickListener(aboutPage);

        //Help button
        Button helpBtn = findViewById(R.id.home_btn_help);
        helpBtn.setOnClickListener(helpPage);



    }

}



