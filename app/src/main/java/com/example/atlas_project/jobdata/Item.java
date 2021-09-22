package com.example.atlas_project.jobdata;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("number")
    private String ItemNo;
    @SerializedName("description")
    private String ItemDescription;
    @SerializedName("location")
    private String Location;
    @SerializedName("quantity")
    private String Quantity;
    @SerializedName("quantity_picked")
    private String quantity_picked;

    public Item(String ItemNo, String ItemDescription, String Location , String Quantity,String quantity_picked){
        this.ItemNo = ItemNo;
        this.ItemDescription = ItemDescription;
        this.Location = Location ;
        this.Quantity = Quantity ;
        this.quantity_picked = quantity_picked ;
    }


    public String getQuantity_picked() {
        return quantity_picked;
    }

    public void setQuantity_picked(String quantity_picked) {
        this.quantity_picked = quantity_picked;
    }
    public String getItemNo() {
        return ItemNo;
    }

    public void setItemNo(String itemNo) {
        this.ItemNo = itemNo;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.ItemDescription = itemDescription;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }
}
