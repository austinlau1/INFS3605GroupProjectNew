package com.example.infs3605groupprojectnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


//import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.MyViewHolder> implements Filterable{
    //Filterable
    private ClickListener mListener;
    private List<Plant> mPlantList, mPlantsFiltered;

    private Context context;

    // Firebase
    // private DatabaseReference mDatabase;

    public PlantAdapter(PlantList plantList, List<Plant> plants, ClickListener listener) {

        //Firebase
        // mDatabase = FirebaseDatabase.getInstance().getReference();

        this.context = context;
        this.mPlantList = plants;
        this.mPlantsFiltered = plants;
        this.mListener = listener;
    }

    /*public void setData(ArrayList<Plant> plants) {
        mPlantList.clear();
        mPlantList.addAll(plants);
        notifyDataSetChanged();
    }*/

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
        holder.mPlantName.setText(plant.getName());
        holder.mPlantScientific.setText(plant.getScientificName());
        holder.mPlantId.setText((plant.getId().toString()));

//        holder.mPlant.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatActivity activity = (AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.plant_id, new PlantFragment(Plant.class.getName()).addToBackStack(null).commit();
//            }
//        });

    }

        /*holder.itemView.setTag(plant.getId());

        String url = "https://www.coinlore.com/img/" + plant.getName().toLowerCase(Locale.ROOT) + ".png";
        Glide.with(holder.mImage)
                .load(url)
                .centerCrop()
                .override(140, 140)
                .into(holder.mImage);*/


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
    /*public void Sort(int sortBy){
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
    }*/

/*    public void setFilteredList (List<Plant> filteredList){
        this.mPlantsFiltered = filteredList;
        notifyDataSetChanged();
    }*/


    // Interface to implement on click function
    public interface ClickListener {
        void onPlantClick(View v, String plantSymbol);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mPlantName;
        private TextView mPlantScientific;
        private TextView mPlantId;
        private ImageView mImage;
        private ClickListener listener;



        public MyViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            this.listener = listener;
            mImage = itemView.findViewById(R.id.plantPicture);
            mPlantName = itemView.findViewById(R.id.plantName);
            mPlantScientific = itemView.findViewById(R.id.plantSciName);
            mPlantId = itemView.findViewById(R.id.plantID);
            itemView.setOnClickListener(this);

            mPlantId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.rvList, new PlantFragment(Plant.class.getName())).addToBackStack(null).commit();
                }
            });
        }

        @Override
        public void onClick(View v) {
//            / Do something when the button is clicked
            listener.onPlantClick(v, (String) v.getTag());
            if (mListener != null) {
                listener.onPlantClick(v, mPlantId.getTransitionName());
            }

        }
    }
}
