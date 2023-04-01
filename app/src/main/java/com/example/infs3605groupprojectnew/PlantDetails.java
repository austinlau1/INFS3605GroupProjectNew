package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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


public class PlantDetails extends AppCompatActivity {

    StorageReference mStorageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_plant_page);
        Intent intent = this.getIntent();
        Intent mapIntent = getIntent();
        Bundle bundle = intent.getExtras();
        Plant plant = (Plant) bundle.getSerializable("key");

        TextView id = findViewById(R.id.plant_id);
        TextView plant_name = findViewById(R.id.plantNm2);
        TextView scientific_name = findViewById(R.id.scientific_name);
        TextView geographic_distribution = findViewById(R.id.geographic_distribution);
        TextView plant_description = findViewById(R.id.plant_description);
        Button learn_more_btn = findViewById(R.id.learn_more_btn);
        Button audio_btn = findViewById(R.id.audio_btn);

        if (plant != null) {

            id.setText(plant.getId().toString());
            scientific_name.setText(plant.getScientificName());
            plant_name.setText(plant.getName());
            geographic_distribution.setText(plant.getGeographicDistribution());
            plant_description.setText(plant.getTraditionalUses());

            // Plant Image
            mStorageReference = FirebaseStorage.getInstance().getReference().child("Picture/" + plant.getName() + ".jpeg");

            try {
                final File localFile = File.createTempFile(""+ plant.getName() + "","jpeg");
                mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        ((ImageView) findViewById(R.id.plant_image)).setImageBitmap(bitmap);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PlantDetails.this, "Error occured", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Plant Map
            mStorageReference = FirebaseStorage.getInstance().getReference().child("Map/" + plant.getName() + ".jpeg");

            try {
                final File localFile = File.createTempFile(""+ plant.getName() + "","jpeg");
                mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        ((ImageView) findViewById(R.id.plant_map)).setImageBitmap(bitmap);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PlantDetails.this, "Error occured", Toast.LENGTH_SHORT).show();


                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Learn more button with google search
            learn_more_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + plant.getName()));
                    startActivity(google_search);
                }
            });

            // Audio button with youtube search
            audio_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent youtube_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + plant.getName()));
                    startActivity(youtube_search);
                }
            });
        } else {
            // Retrieve the value of the extra
            String plantName = intent.getStringExtra("plant_name");

            // Search the list for a Plant with a matching name
            Plant matchingPlant = new Plant("Geographic Distribution", 0, "Plant Name", "Scientific Name", "Description");

            List<Plant> plantList = new ArrayList<>(Plant.getPlants());
            for (Plant mapPlant : plantList) {
                if (mapPlant.getName().equals(plantName)) {
                    matchingPlant = mapPlant;
                    break;
                }
            }

            // Check if a matching plant was found

            id.setText(matchingPlant.getId().toString());
            scientific_name.setText(matchingPlant.getScientificName());
            plant_name.setText(matchingPlant.getName());
            geographic_distribution.setText(matchingPlant.getGeographicDistribution());
            plant_description.setText(matchingPlant.getTraditionalUses());

            // Plant Image
            mStorageReference = FirebaseStorage.getInstance().getReference().child("Picture/" + matchingPlant.getName() + ".jpeg");

            try {
                final File localFile = File.createTempFile(""+ matchingPlant.getName() + "","jpeg");
                mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        ((ImageView) findViewById(R.id.plant_image)).setImageBitmap(bitmap);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PlantDetails.this, "Error occured", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Plant Map
            mStorageReference = FirebaseStorage.getInstance().getReference().child("Map/" + matchingPlant.getName() + ".jpeg");

            try {
                final File localFile = File.createTempFile(""+ matchingPlant.getName() + "","jpeg");
                mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Toast.makeText(PlantDetails.this, "Picture Loaded", Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        ((ImageView) findViewById(R.id.plant_map)).setImageBitmap(bitmap);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PlantDetails.this, "Error occured", Toast.LENGTH_SHORT).show();


                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Learn more button with google search
            final Plant finalMatchingPlant = matchingPlant;
            learn_more_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + finalMatchingPlant.getName()));
                    startActivity(google_search);
                }
            });

            // Audio button with youtube search
            audio_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent youtube_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=" + finalMatchingPlant.getName()));
                    startActivity(youtube_search);
                }
            });

        }
    }
}



    /*DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_plant_page);

        TextView id = findViewById(R.id.plant_id);
        TextView plant_name = findViewById(R.id.plantName);
        TextView scientific_name = findViewById(R.id.scientific_name);
        TextView geographic_distribution = findViewById(R.id.geographic_distribution);
        TextView plant_description = findViewById(R.id.plant_description);
        ImageView plant_image = findViewById(R.id.plant_image);
        ImageView plant_map = findViewById(R.id.plant_map);
        Button learn_more_btn = findViewById(R.id.learn_more_btn);

        database = FirebaseDatabase.getInstance().getReference("Plants Database");

        Intent intent = getIntent();
        String plantSymbol = intent.getStringExtra(PlantList.PLANT_SYMBOL_KEY);
        Log.d("PlantDetails", "plantSymbol: " + plantSymbol);

        if (plantSymbol != null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference("Plants Database").child(plantSymbol);
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Plant plant = snapshot.getValue(Plant.class);
                    if (plant != null) {
                        // Display the data for the selected plant in the activity layout
                        if (plant.getName() != null) {
                            plant_name.setText(plant.getName());
                        } else {
                            plant_name.setText("Name not available");
                        }
                        if (plant.getScientificName() != null) {
                            scientific_name.setText(plant.getScientificName());
                        } else {
                            scientific_name.setText("Scientific name not available");
                        }
                    } else {
                        // Display an error message if the plant data is missing or invalid
                        plant_name.setText("Plant not found");
                        scientific_name.setText("");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle any errors that occur while querying the database
                    Log.e("PlantDetails", "Error getting questions from database", error.toException());
                }
            });
        }*/










        // Old Code
        /*// Get reference to the database
        database = FirebaseDatabase.getInstance().getReference("Plants Database");

        TextView id = findViewById(R.id.plant_id);
        TextView plant_name = findViewById(R.id.plantName);
        TextView scientific_name = findViewById(R.id.scientific_name);
        TextView geographic_distribution = findViewById(R.id.geographic_distribution);
        TextView plant_description = findViewById(R.id.plant_description);
        ImageView plant_image = findViewById(R.id.plant_image);
        ImageView plant_map = findViewById(R.id.plant_map);
        Button learn_more_but = findViewById(R.id.learn_more_but);

        //Firebase storage for image
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("images/plant 3.jpeg");

        // Select picture by plant name
        // StorageReference storageRef = storage.getReference().child(""images/"" + plant.getName() + ".jpg")

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Load the image into an ImageView
                ImageView imageView = findViewById(R.id.plant_image);
                Glide.with(getApplicationContext())
                        .load(uri.toString())
                        .into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        *//*learn_more_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + plant.getName()));
                // Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=eth&rlz=1C5CHFA_enHK1019HK1019&oq=eth+&aqs=chrome..69i57j0i67j0i67i131i433j0i67i433j0i67j69i65j69i61l2.1874j1j7&sourceid=chrome&ie=UTF-8"));
                startActivity(google_search);
            }
        });*//*

        Intent intent = getIntent();
        String plantId = intent.getStringExtra(PlantList.PLANT_SYMBOL_KEY);

        *//*Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Plant plant = database.
                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      if (plant != null) {
                                          id.setText(plant.getId());
                                          plant_name.setText(plant.getName());
                                          scientific_name.setText(plant.getScientificName());
                                          geographic_distribution.setText(plant.getGeographicDistribution());
                                          plant_description.setText(plant.getGeographicDistribution());

                                          database.addValueEventListener(new ValueEventListener() {
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

                                          learn_more_but.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + plant.getName()));
                                                  // Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=eth&rlz=1C5CHFA_enHK1019HK1019&oq=eth+&aqs=chrome..69i57j0i67j0i67i131i433j0i67i433j0i67j69i65j69i61l2.1874j1j7&sourceid=chrome&ie=UTF-8"));
                                                  startActivity(google_search);
                                              }
                                          });

                                      }
                                  }
                              }*//*


        // mDb =

       *//* Intent intent = getIntent();
        String plantId = intent.getStringExtra(PlantFragment.PLANT_SYMBOL_KEY);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Plant plant = databaseReference.getPlant(plantId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (plant != null) {
                            id.setText(plant.getId());
                            coin_symbol_text.setText(plant.getSymbol());
                            value_text.setText(coin.getPriceUsd());
                            change1h_text.setText(coin.getPercentChange1h());
                            change24h_text.setText(coin.getPercentChange24h());
                            change7d_text.setText(coin.getPercentChange7d());
                            marketCap_text.setText(coin.getMarketCapUsd());
                            volume_text.setText(String.format("%.2f", coin.getVolume24()));

                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.
                                    String value = dataSnapshot.getValue(String.class);
                                    if(value != null){
                                        if (value.equals(coin.getSymbol())){
                                            favorite_checkbox.setChecked(true);
                                        }
                                        Log.d("CoinDetailActivity", "Value is: " + value);
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                    // Failed to read value
                                    Log.w("CoinDetailActivity", "Failed to read value.", error.toException());
                                }
                            });

                            learn_more_but.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + plant.getName()));
                                    startActivity(google_search);
                                }
                            });
                        }

                        *//**//*String url = "https://www.coinlore.com/img/" + coin.getNameid().toLowerCase(Locale.ROOT) + ".png";

                        Glide.with(DetailActivity.this)
                                .load(url)
                                .centerCrop()
                                .override(300, 300)
                                //.placeholder(R.drawable.loading)
                                .into(coin_image);

                        // Read from the database

                    }

                });*//**//*

            }
        });

    }
}*//*

    }*/
 /*   }
}*/