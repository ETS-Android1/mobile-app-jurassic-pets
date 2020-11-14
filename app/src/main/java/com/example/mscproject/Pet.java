package com.example.mscproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


enum PetType {
    Triceratops,
    Trex,
    Bronchiosaurus
}

public class Pet implements StepListener, Serializable {

    private int coins;
    private int dailyTarget = 1;
    private int numSteps = 0;
    private int level;
    private int happiness;
    private boolean isAwake = false;

    private String petName;
    private PetType petType;
    private List<Item> items;


    public Pet(int coins, String name, PetType petType, int level, int happiness, ArrayList<Item> items, boolean isAwake) {
        this.petName = name;
        this.petType = petType;
        this.level = level;
        this.happiness = happiness;
        this.coins = coins;
        this.isAwake = isAwake;

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

    public int getLevel() { return level; }

    public int getCoins() { return coins; }

    public void removeCoins(int cost) { coins = coins - cost; }

    public int getSteps() {return numSteps; }

    public int getHappiness() { return happiness; }

    public void levelUp() {
        level++;
        happiness = 0;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void storeItem(Item item) { this.items.add(item); }

    private void removeAllItems() { this.items.clear(); }


    @Override
    public void step(long timeNs) {
        numSteps++;
        if (numSteps >= dailyTarget) {
            coins = coins + 100;
        }
    }

    private void increaseHappiness(int points) {
        happiness += points;
    }

    public void feed() {
        for (Item i : items) {
            increaseHappiness(i.getPoints());
        }
        removeAllItems();
    }

    public void wakeUp() {
        this.isAwake = true;
    }

    public boolean getIsAwake() {
        return isAwake;
    }
}