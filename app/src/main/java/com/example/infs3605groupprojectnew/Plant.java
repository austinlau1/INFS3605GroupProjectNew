package com.example.infs3605groupprojectnew;


import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.infs3605groupprojectnew.Quizzes.QuizQuestion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plant implements Serializable {

    private String name;
    private String scientificName;
    private String traditionalUses;
    private String geographicDistribution;
    private Integer id;
    private String image;
    private boolean isFavourite;

    public Plant() {}

    public Plant(String geographicDistribution, Integer id, String name, String scientificName, String traditionalUses, String image, Boolean isFavourite) {
        this.name = name;
        this.scientificName = scientificName;
        this.traditionalUses = traditionalUses;
        this.geographicDistribution = geographicDistribution;
        this.id = id;
        this.image = image;
        this.isFavourite = isFavourite;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean favourite) {
        isFavourite = favourite;
    }


    public static void getPlants(OnPlantsLoadedListener listener) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Plants Database");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Plant> plantList = new ArrayList<>();
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    String geographicDistribution = questionSnapshot.child("geographicDistribution").getValue(String.class);
                    int id = questionSnapshot.child("id").getValue(int.class);
                    String name = questionSnapshot.child("name").getValue(String.class);
                    String scientificName = questionSnapshot.child("scientificName").getValue(String.class);
                    String traditionalUses = questionSnapshot.child("traditionalUses").getValue(String.class);
                    String image = questionSnapshot.child("image").getValue(String.class);
                    Boolean isFavourite = questionSnapshot.child("isFavourite").getValue(Boolean.class);

                    Plant onlyList = new Plant(geographicDistribution, id, name, scientificName, traditionalUses, image, isFavourite);
                    plantList.add(onlyList);
                }
                listener.onPlantsLoaded(plantList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                Log.e("Plant", "Error getting plants from database", databaseError.toException());
            }
        });
    }

    public interface OnPlantsLoadedListener {
        void onPlantsLoaded(ArrayList<Plant> plantList);
    }


}
