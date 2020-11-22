package com.example.mscproject;

import java.io.Serializable;

public class Item implements Serializable {

    private final int id;
    private final String name;
    private String type;
    private final String description;
    private int cost;
    private int points;

    public Item (int id, String name, String type, String description, int cost, int points) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.points = points;
    }

    public int getCost() { return this.cost; }

    public int getPoints() { return this.points; }

    public String getType() { return this.type; }

    public String getName() { return this.name; }

    public String itemToString() {
        return "Item: " + name +
                "Description: " + description +
                "Cost: " + cost;
    }
}


