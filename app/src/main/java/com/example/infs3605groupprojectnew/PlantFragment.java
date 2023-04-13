package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
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
import java.util.Random;


public class PlantFragment extends Fragment implements View.OnClickListener {
    //public static String PLANT_SYMBOL_KEY = "plantSymbol";

    // RecyclerView mRecyclerView;
    // private PlantAdapter adapter;
    Button navigateToList;
    DatabaseReference databaseReference;
    int One;
    StorageReference mStorageReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_plant, container, false);

        navigateToList = (Button) rootView.findViewById(R.id.view_plants_btn);
        navigateToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Plants Database");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Plant> plant = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Plant plantList = ds.getValue(Plant.class);
                    plant.add(plantList);
                }
                Random random = new Random();
                One = random.nextInt(plant.size());

                String plantName1 = plant.get(One).getName();

                TextView plantFragmentName = getActivity().findViewById(R.id.plantFragmentName);
                ImageView plantImage = getActivity().findViewById(R.id.plantImage);
                ProgressBar plantProgress = getActivity().findViewById(R.id.plantFragmentProgress);

                plantFragmentName.setText(plantName1);

                // show progress bar
                plantProgress.setVisibility(View.VISIBLE);

                // Plant Image
                mStorageReference = FirebaseStorage.getInstance().getReference().child("Picture/" + plant.get(One).getScientificName() + ".jpeg");

                try {
                    final File localFile = File.createTempFile(""+ plant.get(One).getScientificName() + "","jpeg");
                    mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            plantImage.setImageBitmap(bitmap);
                            plantProgress.setVisibility(View.GONE); // hide progress bar when image is loaded

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();
                            plantProgress.setVisibility(View.GONE); // hide progress bar when image loading fails
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    // hide progress bar when an exception occurs
                    plantProgress.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootView;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        intent = new Intent(getActivity(), PlantList.class);
        startActivity(intent);
    }

}