package com.example.bimalka.smartbustracker;

/**
 * Created by Bimalka on 28/01/2018.
 */

public class SaveFavRoutes {
    private String startingLocation;
    private String destination;

    public SaveFavRoutes(String startingLocation,String destination){
       this.startingLocation=startingLocation;
       this.destination=destination;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
