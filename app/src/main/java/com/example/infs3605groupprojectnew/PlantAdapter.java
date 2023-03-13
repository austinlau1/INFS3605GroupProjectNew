package com.example.infs3605groupprojectnew;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder> implements Filterable {
    private ClickListener mListener;
    //private List<Plant> mPlantList, mPlantsFiltered;

    /*public PlantAdapter(List<Plant> plants, ClickListener listener) {
        this.mCoinList = plants;
        this.mCoinsFiltered = plants;
        this.mListener = listener;
    }*/

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public PlantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // Sorting plants
    /*public void Sort(int sortBy){
        Collections.sort(mPlantsFiltered, new Comparator<Plant>() {
            @Override
            public int compare(Plant p1, Plant p2) {
                if (sortBy == 1){
                    return p1.getName().compareTo(p2.getName());
                }
                else if (sortBy == 2) {
                    return Double.compare(Double.parseDouble(c1.getPriceUsd()), Double.parseDouble(c2.getPriceUsd()));
                }
                else return c1.getName().compareTo(c2.getName());
            }
        });

        notifyDataSetChanged();
    }*/


    // Interface to implement on click function
    public interface ClickListener {
        void onPlantClick(View view, String plantSymbol);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
