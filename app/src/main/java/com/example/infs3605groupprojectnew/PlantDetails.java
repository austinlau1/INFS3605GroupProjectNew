package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class PlantDetails extends AppCompatActivity {
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_plant_page);

        TextView id = findViewById(R.id.plant_id);
        TextView plant_name = findViewById(R.id.plantName);
        TextView scientific_name = findViewById(R.id.scientific_name);
        TextView geographic_distribution = findViewById(R.id.geographic_distribution);
        TextView plant_description = findViewById(R.id.plant_description);
        ImageView plant_image = findViewById(R.id.plant_image);
        ImageView plant_map = findViewById(R.id.plant_map);
        Button learn_more_bt = findViewById(R.id.learn_more_bt);

        database = FirebaseDatabase.getInstance().getReference("Plants Database");

        Intent intent = getIntent();
        String plantSymbol = intent.getStringExtra(PlantList.PLANT_SYMBOL_KEY);

        database.child(plantSymbol).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Plant plant = snapshot.getValue(Plant.class);
                // Use the plant data to populate the UI elements in the layout XML file
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors that occur while querying the database
            }
        });
    }
}