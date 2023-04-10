package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlantList extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Plant> plant;
    PlantAdapter plantAdapter;
    DatabaseReference databaseReference;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_recyclerview);
        recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

//      served as a part of the search bar in plant fragment
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PlantList.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        plant = new ArrayList<>();




        databaseReference = FirebaseDatabase.getInstance().getReference("Plants Database");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Plant plantList = ds.getValue(Plant.class);
                    plant.add(plantList);
                }
                plantAdapter = new PlantAdapter(plant);
                recyclerView.setAdapter(plantAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    // Search Menu NOT WORKING YET
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return true;
            }
        });
        return false;

//      search function
    }
    public void search (String str){

        ArrayList<Plant> myList = new ArrayList<>();
        for (Plant object : plant )
        {
            if (object.getName().toLowerCase().contains(str.toLowerCase()))
            {
                 myList.add(object);
            }
        }
//        PlantAdapter plantAdapter1 = new PlantAdapter(myList);
//        recyclerView.setAdapter(plantAdapter1);
         plantAdapter.searchPlantList(myList);

    }
}