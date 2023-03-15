package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Executors;

public class PlantList extends AppCompatActivity {
    public static String PLANT_SYMBOL_KEY = "plantSymbol";
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private PlantAdapter plantAdapter;
    private ArrayList<Plant> mPlantList;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
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
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

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
            Intent intent = new Intent(PlantList.this, IndividualPlant.class);
            intent.putExtra(PLANT_SYMBOL_KEY, plantSymbol);
            startActivity(intent);
        }
    };

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.XXX).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                plantAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                plantAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }*/

}