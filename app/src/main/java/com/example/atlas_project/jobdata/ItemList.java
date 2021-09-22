package com.example.atlas_project.jobdata;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemList {
    @SerializedName("KitNo")
    private String kitnumber ;

    @SerializedName("PickListNo")
    private String picklistnumber ;

    @SerializedName("UniqueNo")
    private String uniquenumber ;

    @SerializedName("Station")
    private String station ;
    @SerializedName("items")
    private List<Item> items;
    @SerializedName("Kitter")
    private String Kitter;




    public ItemList(String kitnumber ,String picklistnumber ,String uniquenumber , String station , List<Item> items ,String Kitter ){
        this.kitnumber = kitnumber ;
        this.picklistnumber  = picklistnumber;
        this.uniquenumber = uniquenumber ;
        this.station = station;
        this.Kitter = Kitter ;
        this.items = items ;

    }

    public String getKitnumber() {
        return kitnumber;
    }

    public void setKitnumber(String kitnumber) {
        this.kitnumber = kitnumber;
    }

    public String getPicklistnumber() {
        return picklistnumber;
    }

    public void setPicklistnumber(String picklistnumber) {
        this.picklistnumber = picklistnumber;
    }

    public String getUniquenumber() {
        return uniquenumber;
    }

    public void setUniquenumber(String uniquenumber) {
        this.uniquenumber = uniquenumber;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items ;
    }

    public String getKitter() {
        return Kitter;
    }

    public void setKitter(String kitter) {
        Kitter = kitter;
    }
}
