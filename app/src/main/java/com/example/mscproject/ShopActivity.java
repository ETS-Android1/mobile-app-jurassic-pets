package com.example.mscproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private TextView tvCoins;
    private List<Item> items;
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
    }

    /*
    //Choose pet page
    private final View.OnClickListener buyItem = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            //Start button
            Button buyAppleBtn = findViewById(R.id.home_btn_start);
            startBtn.setOnClickListener(choosePet);


    public void buyItem(Item item){

            this.myPet.getCoins();


    }


     */

}
