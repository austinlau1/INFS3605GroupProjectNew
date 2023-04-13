package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.infs3605groupprojectnew.Quizzes.QuizOptions;
//import com.example.infs3605groupprojectnew.Quizzes.Quizzes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomeFragment extends Fragment {
    DatabaseReference databaseReference;
    int One, Two, Three;
    StorageReference mStorageReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Banner
        FrameLayout bannerLayout = rootView.findViewById(R.id.banner_layout);
        TextView bannerText = rootView.findViewById(R.id.banner_text);
        ImageButton bannerClose = rootView.findViewById(R.id.banner_close);

        bannerLayout.setVisibility(View.VISIBLE);
        bannerText.setText("This is the acknowledgement of country.");
        bannerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerLayout.setVisibility(View.GONE);
            }
        });

        // Edit profile button
        Button editProfileBtn = rootView.findViewById(R.id.editProfileBtn);
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserDetails.class);
                startActivity(intent);
            }
        });

        // Display first name
        TextView firstNameTextView = rootView.findViewById(R.id.name);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        /*if (user != null) {
            String firstName = user.getDisplayName();
            Log.d("TAG", "User first name: " + firstName);
            firstNameTextView.setText(firstName);
        }*/

        // Display user email
        if (user != null) {
            String email = user.getEmail();
            Log.d("TAG", "User email: " + email);
            firstNameTextView.setText(email);
        }



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
                Two = random.nextInt(plant.size());
                Three = random.nextInt(plant.size());

                // Make sure One, Two, and Three are different numbers
                while (One == Two || One == Three || Two == Three) {
                    One = random.nextInt(plant.size());
                    Two = random.nextInt(plant.size());
                    Three = random.nextInt(plant.size());
                }

                String plantName1 = plant.get(One).getName();
                String plantName2 = plant.get(Two).getName();
                String plantName3 = plant.get(Three).getName();

                TextView plantOne = getActivity().findViewById(R.id.plantOne);
                TextView plantTwo = getActivity().findViewById(R.id.plantTwo);
                TextView plantThree = getActivity().findViewById(R.id.plantThree);
                ImageView plantImageOne = getActivity().findViewById(R.id.plantImageOne);
                ImageView plantImageTwo = getActivity().findViewById(R.id.plantImageTwo);
                ImageView plantImageThree = getActivity().findViewById(R.id.plantImageThree);

                plantOne.setText(plantName1);
                plantTwo.setText(plantName2);
                plantThree.setText(plantName3);

                // Plant Image
                mStorageReference = FirebaseStorage.getInstance().getReference().child("Picture/" + plant.get(One).getScientificName() + ".jpeg");

                try {
                    final File localFile = File.createTempFile(""+ plant.get(One).getScientificName() + "","jpeg");
                    mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            plantImageOne.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                plantImageOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), PlantDetails.class);
                        intent.putExtra("plant_name", plant.get(One).getName());
                        Log.e("ViewMap", plant.get(One).getName());
                        startActivity(intent);
                    }
                });

                mStorageReference = FirebaseStorage.getInstance().getReference().child("Picture/" + plant.get(Two).getScientificName() + ".jpeg");

                try {
                    final File localFile = File.createTempFile(""+ plant.get(Two).getScientificName() + "","jpeg");
                    mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            plantImageTwo.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                plantImageTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), PlantDetails.class);
                        intent.putExtra("plant_name", plant.get(Two).getName());
                        Log.e("ViewMap", plant.get(Two).getName());
                        startActivity(intent);
                    }
                });

                mStorageReference = FirebaseStorage.getInstance().getReference().child("Picture/" + plant.get(Three).getScientificName() + ".jpeg");

                try {
                    final File localFile = File.createTempFile(""+ plant.get(Three).getScientificName() + "","jpeg");
                    mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            plantImageThree.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                plantImageThree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), PlantDetails.class);
                        intent.putExtra("plant_name", plant.get(Three).getName());
                        Log.e("ViewMap", plant.get(Three).getName());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });


        // Button to explore plants
        Button toPlantsBtn = (Button) rootView.findViewById(R.id.toPlantsBtn);

        toPlantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });

        //Button to Quiz
        Button toQuizzesBtn = (Button) rootView.findViewById(R.id.toQuizzesBtn);

        toQuizzesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizOptions.class);
                startActivity(intent);
            }
        });

        // Button to log out
        Button logoutButton = (Button) rootView.findViewById(R.id.logout_but);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log the user out
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}