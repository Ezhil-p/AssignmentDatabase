package com.example.assignmentdatabase;

import androidx.annotation.NonNull;

public class Fruit {
    String fruitname;
    int quantity;
    int price;

    public Fruit(String fruitname, int quantity, int price) {
        this.fruitname = fruitname;
        this.quantity = quantity;
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return this.fruitname+" "+this.quantity+" "+this.price;
    }
}
