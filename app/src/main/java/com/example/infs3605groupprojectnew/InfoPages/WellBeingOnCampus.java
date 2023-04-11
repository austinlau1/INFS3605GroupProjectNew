package com.example.infs3605groupprojectnew.InfoPages;

public class WellBeingOnCampus {
    private String GreenSpaces;
    private String UrbanHeat;

    public WellBeingOnCampus(){

    }

    public WellBeingOnCampus (String GreenSpaces, String UrbanHeat){
        this.GreenSpaces = GreenSpaces;
        this.UrbanHeat = UrbanHeat;

    }

    public String getGreenSpaces() {
        return GreenSpaces;
    }

    public void setGreenSpaces(String greenSpaces) {
        GreenSpaces = greenSpaces;
    }

    public String getUrbanHeat() {
        return UrbanHeat;
    }

    public void setUrbanHeat(String urbanHeat) {
        UrbanHeat = urbanHeat;
    }
}
