package com.example.atlas_project.customViews;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Location {
    private static Location location = null ;
    private static String destination ;  //="53 AA1";
    private static String source ;  //= "53 AA";
    private static String sudo_source ;
    private static String sudo_destination ;
    private static String type ;
    private static String shelf ;
    private ArrayList<String> locations ;

    public int getCurrent_point() {
        return current_point;
    }

    public void setCurrent_point(int current_point) {
        this.current_point = current_point;
    }

    private int current_point = 0 ;
    public static boolean done ;
    public static Location getInstance(@Nullable  String destination , @Nullable  String source){
        if(location == null){
            location = new Location(destination ,source) ;
        }
        return  location ;
    }

    private Location(String destination ,String source){
        this.source = source ;
        this.destination =destination ;
        sudo_source = InitializeMap.get_sudo_rack_name(source) ;
        sudo_destination = InitializeMap.get_sudo_rack_name(destination.substring(0,5)) ;
        type = InitializeMap.get_rack_name(sudo_destination).split("-")[1] ;
        shelf = destination.substring(5,6);
        done = false ;
    }

    public static String getDestination() {
        return destination;
    }

    public static void setDestination(String destination) {
        Location.destination = destination;
    }

    public static String getSource() {
        return source;
    }

    public static void setSource(String source) {
        Location.source = source;
    }

    public static String getSudo_source() {
        sudo_source = InitializeMap.get_sudo_rack_name(source) ;
        return sudo_source;
    }

    public static void setSudo_source(String sudo_source) {
        Location.sudo_source = sudo_source;
    }

    public static String getSudo_destination() {
        sudo_destination = InitializeMap.get_sudo_rack_name(destination.substring(0,5)) ;
        return sudo_destination;
    }

    public static void setSudo_destination(String sudo_destination) {
        Location.sudo_destination = sudo_destination;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Location.type = type;
    }

    public static String getShelf() {
        return shelf;
    }

    public static void setShelf(String shelf) {
        Location.shelf = shelf;
    }

    public void setPoints(){
        if(current_point < locations.size()) {
            source = destination.substring(0, 5); //53 AA
            destination = locations.get(current_point);
            current_point++;
        }
        Log.d("------" , source + "   " + destination) ;
    }
    public ArrayList<String> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<String> locations) {
        this.locations = locations;
    }
}
