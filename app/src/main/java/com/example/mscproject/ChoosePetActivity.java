package com.example.mscproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.mscproject.DatabaseHelper.PET_TABLE;

public class ChoosePetActivity extends AppCompatActivity {

    private PetType petType;
    private String petName;
    private EditText etInputName;

    public ChoosePetActivity (){
    }

    // Choosing the pet type
    public void onPetClick(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.choose_rb_bront:
                if (checked)
                    petType = PetType.Bronchiosaurus;
                    break;
            case R.id.choose_rb_trex:
                if (checked)
                    petType = PetType.Trex;
                    break;
            case R.id.choose_rb_tri:
                if (checked)
                    petType = PetType.Triceratops;
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pet);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Enter the name of the pet
        etInputName = findViewById(R.id.choose_et_inputName);

        //Sends the name and type to the next page
        View.OnClickListener submit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchMainActivity = new Intent(ChoosePetActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                petName = etInputName.getText().toString();
                Pet myPet = new Pet(100, petName, petType, 0, 0, null, false);

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.COLUMN_PET_NAME, myPet.getName());
                //values.put(DatabaseHelper.COLUMN_PET_TYPE, myPet.getType()); -- Need to change the type for enum
                values.put(DatabaseHelper.COLUMN_PET_LEVEL, myPet.getLevel());
                values.put(DatabaseHelper.COLUMN_PET_COINS, myPet.getCoins());
                values.put(DatabaseHelper.COLUMN_PET_HAPPINESS, myPet.getHappiness());
                //values.put(DatabaseHelper.COLUMN_PET_ITEMS, myPet.getItems()); -- Need to change the type for array
                values.put(DatabaseHelper.COLUMN_PET_ISAWAKE, myPet.getIsAwake());

                long newRowId = db.insert(PET_TABLE, null, values);

                bundle.putSerializable("MY_PET", myPet);
                launchMainActivity.putExtras(bundle);
                startActivity(launchMainActivity);

            }
        };

        Button submitBtn = findViewById(R.id.choose_btnSubmit);
        submitBtn.setOnClickListener(submit);

    }

}
