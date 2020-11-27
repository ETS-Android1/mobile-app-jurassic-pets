package com.example.mscproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.mscproject.DatabaseHelper.PET_TABLE;

public class ChoosePetActivity extends AppCompatActivity {

    private PetType petType;
    private String petName;
    private EditText etInputName;
    private boolean hasName;
    private boolean hasType;

    private Button submitBtn;


    public ChoosePetActivity (){
    }

    public void setButtonVisibility() {
        if (hasName == true && hasType == true) {
            submitBtn.setVisibility(View.VISIBLE);
        }
        else {
            submitBtn.setVisibility(View.INVISIBLE);
        }
    }


    // Choosing the pet type
    public void onPetClick(View view) {

        hasType = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.choose_rb_bront:
                if (hasType)
                    petType = PetType.Bronchiosaurus;
                    break;
            case R.id.choose_rb_trex:
                if (hasType)
                    petType = PetType.Trex;
                    break;
            case R.id.choose_rb_tri:
                if (hasType)
                    petType = PetType.Triceratops;
                    break;
        }
        setButtonVisibility();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pet);
        submitBtn = findViewById(R.id.choose_btnSubmit);
        setButtonVisibility();


        Toast.makeText(this, "Welcome to Jurassic Pets. Please enter a name for your pet above", Toast.LENGTH_LONG).show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(), "Then click on the pet that you would like to choose", Toast.LENGTH_LONG).show();
            }
        }, 4000);




        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    hasName = true;
                }
                else {
                    hasName = false;
                }
                setButtonVisibility();
            }
        };

        //Enter the name of the pet
        etInputName = findViewById(R.id.choose_et_inputName);
        etInputName.addTextChangedListener(textWatcher);


        //Sends the pet data to the next page
        View.OnClickListener submit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                petName = etInputName.getText().toString();
                Pet myPet = new Pet(0, petName, petType, 0, 0, null, false);
                dbHelper.addPet(myPet);
                bundle.putSerializable("MY_PET", myPet);
                launchMainActivity.putExtras(bundle);
                startActivity(launchMainActivity);

            }

            DatabaseHelper dbHelper = new DatabaseHelper(ChoosePetActivity.this);
        };

        submitBtn.setOnClickListener(submit);

    }

}
