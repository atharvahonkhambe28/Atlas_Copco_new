package com.example.atlas_project;


public class Card {
    private String ItemNo;
    private String ItemName;
    private String Location;
    private String Quantity;

    public Card(String itemNo, String itemName, String location, String quantity) {
        this.ItemNo = itemNo;
        this.ItemName = itemName;
        this.Location=location;
        this.Quantity=quantity;
    }



    public String getItemNo() {
        return ItemNo;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getLocation() {
        return Location;
    }

    public String getQuantity() {
        return Quantity;
    }
}