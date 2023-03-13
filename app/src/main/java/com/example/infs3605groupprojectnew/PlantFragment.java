package com.example.infs3605groupprojectnew;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;


public class PlantFragment extends Fragment {

    RecyclerView mRecyclerView;
    private PlantAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plant, container, false);



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