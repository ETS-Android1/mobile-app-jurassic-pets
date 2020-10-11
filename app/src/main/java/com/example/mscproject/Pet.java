package com.example.mscproject;

enum PetType {
    Triceratops,
    Trex,
    Bronchiosaurus
}

public class Pet implements StepListener {

    private int level;
    private int coins;
    private int dailyTarget = 5;
    private int numSteps = 0;

    private String petName;
    private PetType petType;
    private String petDescription;

    // Constructor for objects of class Pet
    public Pet(int coins, String name, PetType petType) {
        this.petName = name;
        this.petType = petType;
    }

    public String getName() {
        return petName;
    }

    public int getLevel() { return level; }

    public PetType getPetType() { return petType; }

    public int getCoins() { return coins; }

    public int getSteps() {return numSteps; }


    @Override
    public void step(long timeNs) {
        numSteps++;
        if (numSteps >= dailyTarget) {
            coins = coins + 100;
            //numSteps = 0;
        }
    }
}