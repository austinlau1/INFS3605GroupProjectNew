package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

//import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlantList extends AppCompatActivity {
    public static String PLANT_SYMBOL_KEY = "plantSymbol";
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private PlantAdapter plantAdapter;
    private ArrayList<Plant> mPlantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_recyclerview);

        recyclerView = findViewById(R.id.rvList);
        database = FirebaseDatabase.getInstance().getReference("Plants Database");
        // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPlantList = new ArrayList();
        plantAdapter = new PlantAdapter(this, mPlantList, null);
        recyclerView.setAdapter(plantAdapter);

        //plantAdapter = new PlantAdapter(new ArrayList<Plant>(), listener);


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

        recyclerView.setAdapter(plantAdapter);

    }

    PlantAdapter.ClickListener listener = new PlantAdapter.ClickListener() {
        @Override
        public void onPlantClick(View view, String plantSymbol) {
            Intent intent = new Intent(PlantList.this, PlantDetails.class);
            intent.putExtra(PLANT_SYMBOL_KEY, plantSymbol);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
/*        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.actionSearch).getActionView();*/
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

    /*private void filterList(String text) {
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
    }*/



}

