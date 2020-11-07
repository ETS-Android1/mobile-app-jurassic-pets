package com.example.mscproject;

import java.io.Serializable;

public class Item implements Serializable {

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


