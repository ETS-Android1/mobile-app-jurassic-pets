package com.example.mscproject;

public class Pet implements StepListener {

    private int level;
    private int dailyTarget = 5;
    private int numSteps = 0;

    private String petName;

    // Constructor for objects of class Pet
    public Pet(int lvl, String name) {
        level = lvl;
        petName = name;
    }

    public String getName() {
        return petName;
    }

    public int getLevel() {
        return level;
    }

    public int getSteps() {return numSteps; }

    @Override
    public void step(long timeNs) {
        numSteps++;
        if (numSteps >= dailyTarget) {
            level++;
            //numSteps = 0;
        }
    }
}