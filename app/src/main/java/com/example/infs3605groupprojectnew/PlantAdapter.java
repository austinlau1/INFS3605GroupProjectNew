package com.example.infs3605groupprojectnew;

import android.content.Context;
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
    public void searchPlantList(ArrayList<Plant> searchList){
        plant = searchList;
        notifyDataSetChanged();

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
    public interface RecyclerViewClickListener {
        void onClick(View view, String placesId);
    }

}