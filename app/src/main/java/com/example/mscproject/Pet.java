package com.example.mscproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


enum PetType {
        Triceratops(0),
        Trex(1),
        Bronchiosaurus(2);

    private int petType;

    PetType (int petType) {
        this.petType = petType;
    }

    public int getValue() {
        return this.petType;
    }
}

public class Pet implements StepListener, Serializable {

    private int id;
    private int coins;
    private int target = 2;
    private int numSteps = 0;
    private int level;
    private int happiness;
    private boolean isAwake;

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

        //Checks to see if the Pet already has an items list
        if (items == null) {
            this.items = new ArrayList<>();
        }
        else {
            this.items = items;
        }
    }

    public int getId() { return id; }

    public String getName() {
        return petName;
    }

    public PetType getType() { return petType; }

    public int getLevel() { return level; }

    public void levelUp() {
        level++;
        happiness = 0;
    }

    public int getCoins() { return coins; }

    public void removeCoins(int cost) { coins = coins - cost; }

    public int getTarget() { return target; }

    public int getSteps() { return numSteps; }

    public int getHappiness() { return happiness; }


    public List<Item> getItems() {
        return this.items;
    }

    public void storeItem(Item item) {
        this.items.add(item); }


    public void wakeUp() {
        this.isAwake = true;
    }

    public boolean getIsAwake() {
        return isAwake;
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        if (numSteps == target) {
            coins = coins + 100;
            target += target;
        }
    }

    private void increaseHappiness(int points) {
        happiness += points;
    }

    public void feed() {
        Iterator<Item> itemIterator = items.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (item.getType().equals("Food")) {
                increaseHappiness(item.getPoints());
                itemIterator.remove();
            }
        }
    }

    public void storeId(int id) {
        this.id = id;
    }


}