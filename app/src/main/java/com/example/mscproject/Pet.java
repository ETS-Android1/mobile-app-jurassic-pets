package com.example.mscproject;

public class Pet {

    private int level = 0;

    // Constructor for objects of class Pet
    public Pet(int lvl) {
        this.setLevel(lvl);
    }

    // Accessor - return the pets current level
    public int getLevel() { return level; }

    // Mutator - update the pets level -- Possibly need a level class for where the logic sits?
    public void setLevel(int lvl)
    {
        level = lvl;
    }

}
