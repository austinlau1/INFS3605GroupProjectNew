package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Plant {

/*    String name, scientificName, id;

    public String getName() {
        return name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Plant() {
    }

}*/


    private String name;
    private String scientificName;
    private String traditionalUses;
    private String geographicDistribution;
    private Integer id;

    public Plant() {}

    public Plant(String name, String scientificName, String traditionalUses, String geographicDistribution, Integer id) {
        this.name = name;
        this.scientificName = scientificName;
        this.traditionalUses = traditionalUses;
        this.geographicDistribution = geographicDistribution;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getTraditionalUses() {
        return traditionalUses;
    }

    public void setTraditionalUses(String traditionalUses) {
        this.traditionalUses = traditionalUses;
    }

    public String getGeographicDistribution() {
        return geographicDistribution;
    }

    public void setGeographicDistribution(String geographicDistribution) {
        this.geographicDistribution = geographicDistribution;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
