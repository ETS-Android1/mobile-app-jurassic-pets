package com.example.mscproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private TextView tvCoins;
    private List<Item> item;
    private Pet myPet;
    private static final String TEXT_COINS = "Coins: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myPet = (Pet) bundle.getSerializable("MY_PET");
        tvCoins = findViewById(R.id.shop_tv_coins);
        tvCoins.setText(TEXT_COINS + myPet.getCoins());

        Button buyBtn = findViewById(R.id.shop_btn_buy);
        buyBtn.setOnClickListener(buy);

        Button backBtn = findViewById(R.id.shop_btn_back);
        backBtn.setOnClickListener(returnClicked);


    }

    public void buyItem(Item item) {

        int myCoins = myPet.getCoins();
        int itemCost = item.getCost();
        if (myCoins >= itemCost) {
            myPet.storeItem(item);
            myPet.removeCoins(itemCost);
            tvCoins.setText(TEXT_COINS + myPet.getCoins());
        }

    }

    private final View.OnClickListener buy = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RadioGroup itemList = findViewById(R.id.shop_rg);
            int itemId = itemList.getCheckedRadioButtonId();

            switch (itemId) {
                case R.id.shop_rb_item1:

                    Item item1 = new Item("Apple", "This is an apple", 100);
                    buyItem(item1);

                    break;

                case R.id.shop_rb_item2:

                    Item item2 = new Item("Banana", "This is a banana", 200);
                    buyItem(item2);

                    break;
            }

        }

    };

    View.OnClickListener returnClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent launchMainActivity = new Intent(ShopActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("MY_PET", myPet);
            launchMainActivity.putExtras(bundle);
            startActivity(launchMainActivity);
        }
    };


}
