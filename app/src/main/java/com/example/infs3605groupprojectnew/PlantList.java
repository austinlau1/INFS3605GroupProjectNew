package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
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
        SearchView searchView = (SearchView) menu.findItem(R.id.actionSearch).getActionView();
        searchView.setQueryHint("Search...");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return false;
            }
        });
        return true;

    }
    private void search (String str){

        ArrayList<Plant> myList = new ArrayList<>();
        for (Plant object : plant )
        {
            if (object.getName().toLowerCase().contains(str.toLowerCase()))
            {
                 myList.add(object);
            }
        }
        PlantAdapter plantAdapter1 = new PlantAdapter(myList);
        recyclerView.setAdapter(plantAdapter1);

    }
}
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//        MenuItem searchItem = menu.findItem(R.id.actionSearch);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setQueryHint("Search...");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // plantSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // plantSearch(newText);
//                //PlantAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//        return true;
//
//    }
//
//    private void plantSearch(String s) {
//
//    }










    /*public static String PLANT_SYMBOL_KEY = "plantSymbol";
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private PlantAdapter plantAdapter;
    List<Plant> mPlantList = new ArrayList<>(Plant.getPlants());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_recyclerview);

        recyclerView = findViewById(R.id.rvList);
        database = FirebaseDatabase.getInstance().getReference("Plants Database");
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //mPlantList = new ArrayList();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Plant plant = dataSnapshot.getValue(Plant.class);
                    mPlantList.add(plant);
                }
                plantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //plantAdapter = new PlantAdapter(new ArrayList<Plant>(), listener);

        PlantAdapter.ClickListener listener = new PlantAdapter.ClickListener() {
            @Override
            public void onPlantClick(View view, String plantSymbol) {
                Intent intent = new Intent(PlantList.this, PlantDetails.class);
                Log.d("PlantDetails", "plantSymbol: " + plantSymbol);
                intent.putExtra(PLANT_SYMBOL_KEY, plantSymbol);
                startActivity(intent);
            }
        };

        plantAdapter = new PlantAdapter(this, mPlantList, listener);
        recyclerView.setAdapter(plantAdapter);


    }

    *//*@Override
    public void onStart() {
        super.onStart();
        plantAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        plantAdapter.stopListening();
    }*//*



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
*//*        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.actionSearch).getActionView();*//*
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // plantAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // plantAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;


    }

    *//*private void filterList(String text) {
        List<Plant> filteredList = new ArrayList<>();
        for (Plant plant : mPlantList) {
            if (plant.getName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))){
                filteredList.add(plant);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            PlantAdapter.setFilteredList(filteredList);
        }
    }*//*



}*/