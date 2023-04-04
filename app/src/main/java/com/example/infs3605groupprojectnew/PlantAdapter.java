package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter /*implements Filterable*/ {

    List<Plant> plant;
//    private RecyclerViewClickListener mListener;


    public PlantAdapter(List<Plant> plant) {
        this.plant = plant;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_list, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        final Plant plantsList = plant.get(position);
        viewHolderClass.plantName.setText(plantsList.getName());
        viewHolderClass.plantScientificName.setText(plantsList.getScientificName());
        viewHolderClass.plantId.setText((plantsList.getId().toString()));
        Glide.with(viewHolderClass.plantPicture.getContext()).load(plantsList.getImage()).into(viewHolderClass.plantPicture);

        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PlantDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key", plantsList);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return plant.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView plantName;
        private TextView plantScientificName;
        private TextView plantId;
        private ImageView plantPicture;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            plantName=itemView.findViewById(R.id.plantNm);
            plantScientificName=itemView.findViewById(R.id.plantScientific);
            plantId = itemView.findViewById(R.id.plantIDNum);
            plantPicture = itemView.findViewById(R.id.plantPicture);


        }
    }
//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String query = charSequence.toString();
//                if (query.isEmpty()) {
//                    mPlantsFiltered = plant;
//                } else {
//                    ArrayList<Plant> filteredList = new ArrayList<>();
//                    for (Plant plant : plant) {
//                        if (plant.getName().contains(query)) {
//                            filteredList.add(plant);
//                        }
//                    }
//                    mPlantsFiltered = filteredList;
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = mPlantsFiltered;
//                return filterResults;
//
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                mPlantsFiltered = (ArrayList<Plant>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }

    //Search Menu Filter NOT WORKING YET
    /*@Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            }
        };
    }*/
    public interface RecyclerViewClickListener {
        void onClick(View view, String placesId);
    }

}
    //Filterable
   /* private ClickListener mListener;
    private List<Plant> mPlantList, mPlantsFiltered;

    private Context context;

    // Firebase
    // private DatabaseReference mDatabase;

    public PlantAdapter(Context context, List<Plant> plants, ClickListener listener) {

        //Firebase
        // mDatabase = FirebaseDatabase.getInstance().getReference();

        this.context = context;
        this.mPlantList = plants;
        this.mPlantsFiltered = plants;
        this.mListener = listener;
    }

    *//*public void setData(ArrayList<Plant> plants) {
        mPlantList.clear();
        mPlantList.addAll(plants);
        notifyDataSetChanged();
    }*//*

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_list, parent, false);
        // return null;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_list, parent, false);
        return new MyViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Plant plant = mPlantsFiltered.get(position);
        Plant plant = mPlantsFiltered.get(position);
        holder.mPlant.setText(plant.getName());
        holder.mPlantScientific.setText(plant.getScientificName());
        holder.mPlantId.setText((plant.getId().toString()));


        *//*holder.itemView.setTag(plant.getId());

        String url = "https://www.coinlore.com/img/" + plant.getName().toLowerCase(Locale.ROOT) + ".png";
        Glide.with(holder.mImage)
                .load(url)
                .centerCrop()
                .override(140, 140)
                .into(holder.mImage);*//*
    }

    @Override
    public int getItemCount() {
        return mPlantsFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString();
                if (query.isEmpty()) {
                    mPlantsFiltered = mPlantList;
                } else {
                    ArrayList<Plant> filteredList = new ArrayList<>();
                    for (Plant plant : mPlantList) {
                        if (plant.getName().contains(query)) {
                            filteredList.add(plant);
                        }
                    }
                    mPlantsFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mPlantsFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mPlantsFiltered = (ArrayList<Plant>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    // Sorting plants
    *//*public void Sort(int sortBy){
        Collections.sort(mPlantsFiltered, new Comparator<Plant>() {
            @Override
            public int compare(Plant p1, Plant p2) {
                if (sortBy == 1){
                    return p1.getName().compareTo(p2.getName());
                }
                else if (sortBy == 2) {
                    return Double.compare(Double.parseDouble(p1.getPriceUsd()), Double.parseDouble(p2.getPriceUsd()));
                }
                else return p1.getName().compareTo(p2.getName());
            }
        });

        notifyDataSetChanged();
    }*//*

*//*    public void setFilteredList (List<Plant> filteredList){
        this.mPlantsFiltered = filteredList;
        notifyDataSetChanged();
    }*//*


    // Interface to implement on click function
    public interface ClickListener {
        void onPlantClick(View v, String plantSymbol);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mPlant;
        private TextView mPlantScientific;
        private TextView mPlantId;
        private ImageView mImage;
        private ClickListener listener;

        public MyViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            this.listener = listener;
            mImage = itemView.findViewById(R.id.plantPicture);
            mPlant = itemView.findViewById(R.id.cPlant);
            mPlantScientific = itemView.findViewById(R.id.cPlant2);
            mPlantId = itemView.findViewById(R.id.cPlant3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onPlantClick(v, (String) v.getTag());

        }
    }
}*/