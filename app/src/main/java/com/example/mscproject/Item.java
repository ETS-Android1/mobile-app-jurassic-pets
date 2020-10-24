package com.example.mscproject;

public class Item {

    private final String name;
    private final String description;
    private int cost;

    public Item (String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public int getCost() { return this.cost; }

}


