package com.example.mscproject;

public class Pet {

    private int level = 0;
    private int dailyTarget = 7000;
    private int numSteps = 0;

    // Constructor for objects of class Pet
    public Pet(int lvl) {level = lvl;}

    // Return the pets current level
    public int getLevel() { return level; }

    public void step() {
        numSteps++;
        if(numSteps >= dailyTarget) {
            level++;
            numSteps = 0;
        }

    }

}
