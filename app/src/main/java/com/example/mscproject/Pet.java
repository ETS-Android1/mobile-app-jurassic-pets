package com.example.mscproject;

public class Pet implements StepListener {

    private int level;
    private int coins;
    private int dailyTarget = 5;
    private int numSteps = 0;

    private String petName;
    private String petType;
    private String petDescription;

    // Constructor for objects of class Pet
    public Pet(int coins, String name) {
        petName = name;
    }

    public String getName() {
        return petName;
    }

    public String setName() { return petName; }

    public String getType() { return petType; }

    public String setType() { return petType; }

    public int getLevel() { return level; }

    public int getCoins() { return coins; }

    public int getSteps() {return numSteps; }


    @Override
    public void step(long timeNs) {
        numSteps++;
        if (numSteps >= dailyTarget) {
            coins = coins + 100;
            numSteps = 0;
        }
    }
}