package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PlantFragment extends Fragment {
    public static String PLANT_SYMBOL_KEY= "plantSymbol";

    RecyclerView mRecyclerView;
    private PlantAdapter adapter;

    /*PlantAdapter.ClickListener listener = new PlantAdapter.ClickListener() {
        @Override
        public void onCoinClick(View view, String plantSymbol) {
            Intent intent = new Intent(PlantFragment.this, IndividualPlant.class);
            intent.putExtra(PLANT_SYMBOL_KEY, plantSymbol);
            // FirebaseDatabase database = FirebaseDatabase.getInstance();
            // DatabaseReference reference = database.getReference(FirebaseAuth.getInstance().getUid());
            // reference.setValue(coins.getCoin());
            startActivity(intent);
        }
    };*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plant, container, false);
        // Initialize Firebase
        FirebaseApp.initializeApp(getContext());
        // Get reference to the database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();



        /*mRecyclerView = findViewById(R.id.rvList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);*/

        /*adapter = new PlantAdapter(new ArrayList<Plant>(), listener);*/
        /*mRecyclerView.setAdapter(adapter);*/

        // Add a ValueEventListener to the reference
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Handle data changes
                // Use getValue() to get the data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        return rootView;


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_menu).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }*/

    /*// Sort plants
    @Override
    public boolean onOptionsItemSelected (MenuItem menuSelected){
        if (menuSelected.getItemId() == R.id.name_sort) {
            adapter.Sort(1);
        }
        else if (menuSelected.getItemId() == R.id.value_sort) {
            adapter.Sort(2);
        }
        return true;

    }*/


}