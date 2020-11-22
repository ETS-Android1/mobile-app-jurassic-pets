package com.example.mscproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private TextView tvCoins;
    private ImageView costume;

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

        Button btnBuy = findViewById(R.id.shop_btn_buy);
        btnBuy.setOnClickListener(buy);

        Button btnBack = findViewById(R.id.shop_btn_back);
        btnBack.setOnClickListener(returnPetItems);

        costume = findViewById(R.id.shop_iv_hero);
        changePicture(R.drawable.char_hero_01, R.drawable.char_hero_02, R.drawable.char_hero_03);

        Toast buyingToast;
        buyingToast = Toast.makeText(getApplicationContext(), "Buy food and costumes for your pet here", Toast.LENGTH_SHORT);
        buyingToast.show();
    }


    public void buyItem(Item item) {

        int myCoins = myPet.getCoins();
        int itemCost = item.getCost();
        int level = myPet.getLevel();
        String itemType = item.getType();

        Toast itemAddedToast;
        itemAddedToast = Toast.makeText(getApplicationContext(), "You've bought a " + item.getName() + " for your pet", Toast.LENGTH_SHORT);

        if (myCoins >= itemCost && itemType == "Food") {
            myPet.storeItem(item);
            myPet.removeCoins(itemCost);
            tvCoins.setText(TEXT_COINS + myPet.getCoins());
            itemAddedToast.show();

        }
        else if (myCoins >= itemCost && itemType == "Costume" && level >= 10) {
            myPet.storeItem(item);
            myPet.removeCoins(itemCost);
            tvCoins.setText(TEXT_COINS + myPet.getCoins());
            itemAddedToast.show();
        }
        else if (myCoins >= itemCost && itemType == "Costume" && level < 10) {
            Toast tooLowLevelToast;
            tooLowLevelToast = Toast.makeText(getApplicationContext(), "Sorry. Your pet needs to be level 10 for you to buy this item!", Toast.LENGTH_SHORT);
            tooLowLevelToast.show();
        }
        else {
            Toast notEnoughCoinsToast;
            notEnoughCoinsToast = Toast.makeText(getApplicationContext(), "Sorry. You don't have enough coins to buy this item!", Toast.LENGTH_SHORT);
            notEnoughCoinsToast.show();
        }
    }


    private void changePicture(int bronchiosaurusPic, int trexPic, int triceratopsPic) {
        switch (myPet.getType()) {
            case Bronchiosaurus:
                costume.setImageResource(bronchiosaurusPic);
                break;
            case Trex:
                costume.setImageResource(trexPic);
                break;
            case Triceratops:
                costume.setImageResource(triceratopsPic);
                break;
        }
    }


    private final View.OnClickListener buy = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RadioGroup itemList = findViewById(R.id.shop_rg);
            int itemId = itemList.getCheckedRadioButtonId();

            switch (itemId) {
                case R.id.shop_rb_item1:

                    Item item1 = new Item(1, "Apple", "Food","This is an apple", 100, 10);
                    buyItem(item1);

                    break;

                case R.id.shop_rb_item2:

                    Item item2 = new Item(2, "Banana", "Food","This is a banana", 200, 20);
                    buyItem(item2);

                    break;

                case R.id.shop_rb_item3:

                    Item item3 = new Item(2, "Hero outfit", "Costume","This is a super hero outfit for your pet", 800, 0);
                    buyItem(item3);

                    break;
            }

        }

    };

    View.OnClickListener returnPetItems = new View.OnClickListener() {
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
