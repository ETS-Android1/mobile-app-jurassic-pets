package com.example.mscproject;

import java.io.Serializable;
import java.util.ArrayList;


enum PetType {
    Triceratops,
    Trex,
    Bronchiosaurus
}

public class Pet implements StepListener, Serializable {

    private int coins;
    private int dailyTarget = 1;
    private int numSteps = 0;

    private String petName;
    private PetType petType;
    private ArrayList<Item> items;

    // Constructor for objects of class Pet
    public Pet(int coins, String name, PetType petType, ArrayList<Item> items) {
        this.petName = name;
        this.petType = petType;
        this.coins = coins;

        if (items == null) {
            this.items = new ArrayList<>();
        }
        else {
            this.items = items;
        }
    }


    public String getName() {
        return petName;
    }

    public PetType getType() { return petType; }

    public int getCoins() { return coins; }

    public void removeCoins(int cost) {

        coins = coins - cost;
    }

    public int getSteps() {return numSteps; }


    @Override
    public void step(long timeNs) {
        numSteps++;
        if (numSteps >= dailyTarget) {
            coins = coins + 100;
            //numSteps = 0;
        }
    }

    public void storeItem(Item item) {

        this.items.add(item);

    }
}