package com.example.mscproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class ChoosePetActivity extends AppCompatActivity {

    String petName;
    EditText inputName;
    Button submitButton;

    private PetType petType;

    public ChoosePetActivity (){
    }

    public void onDinoClick(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdo_bront:
                if (checked)
                    petType = PetType.Bronchiosaurus;
                    break;
            case R.id.rdo_trex:
                if (checked)
                    petType = PetType.Trex;
                    break;
            case R.id.rdo_tri:
                if (checked)
                    petType = PetType.Triceratops;
                    break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pet);

        inputName = (EditText) findViewById(R.id.et_inputName);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                //TextView textView = ()
                launchMainActivity.putExtra("PET_TYPE", PetType.Bronchiosaurus);
                startActivity(launchMainActivity);
            }
        };


        ImageButton btn = findViewById(R.id.btn_bront);
        btn.setOnClickListener(listener);


        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                launchMainActivity.putExtra("PET_TYPE", PetType.Triceratops);
                startActivity(launchMainActivity);
            }
        };

        ImageButton btn3 = findViewById(R.id.btn_tri);
        btn3.setOnClickListener(listener3);

    }

}
