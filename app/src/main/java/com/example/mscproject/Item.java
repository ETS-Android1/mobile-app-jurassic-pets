package com.example.mscproject;

public class Item {

    private final String name;
    private final String description;
    private int cost;

    public Item (String name, String description, int cost, int quantity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {return name;}

    public String getDescription() {return description;}

    public int getCost() {return cost;}

}


